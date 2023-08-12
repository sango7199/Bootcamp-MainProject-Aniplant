package com.mainproject.user.service.captcha;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// 네이버 캡차 API - 키발급
public class CaptchaKey {

 public static void main(String[] args) {
     String clientId = "xhb9tw1ld7"; // 애플리케이션 클라이언트 아이디값";
     String clientSecret = "gDAmFlZz2q4SaHnoD4Jrc1wGyi4zd1oqu87AfgBx"; // 애플리케이션 클라이언트 시크릿값";
     try {
         String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
         String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code;
         URL url = new URL(apiURL);
         HttpURLConnection con = (HttpURLConnection)url.openConnection();
         con.setRequestMethod("GET");
         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
         int responseCode = con.getResponseCode();
         BufferedReader br;
         if(responseCode==200) { // 정상 호출
             br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         } else {  // 오류 발생
             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
         }
         String inputLine;
         StringBuffer response = new StringBuffer();
         while ((inputLine = br.readLine()) != null) {
             response.append(inputLine);
         }
         br.close();
         System.out.println(response.toString());
     } catch (Exception e) {
         System.out.println(e);
     }
  }
 
}