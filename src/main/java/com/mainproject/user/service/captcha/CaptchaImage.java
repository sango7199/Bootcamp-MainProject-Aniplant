package com.mainproject.user.service.captcha;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

// 네이버 캡차 API - 캡차 이미지 수신
public class CaptchaImage {
	// 파일 Base64로 인코딩 후 페이지로 전송하는 방식
	private static final String clientId = "xhb9tw1ld7"; // "애플리케이션 클라이언트 아이디값";

    public static String getCaptchaImageAsBase64(String key) {
        try {
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key + "&X-NCP-APIGW-API-KEY-ID=" + clientId;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if(responseCode == 200) {
                InputStream is = con.getInputStream();
                byte[] imageBytes = is.readAllBytes();
                return Base64.getEncoder().encodeToString(imageBytes);
            } else {
                // 오류 처리
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();
                System.err.println(response.toString());
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return null; // 실패하면 null 반환
    }

    public static void main(String[] args) {
        String key = "m8KdoxMoI3VB3Grt"; // 여기에 캡차 키를 적용해야 합니다.
        String encodedImage = getCaptchaImageAsBase64(key);
        System.out.println(encodedImage);
    }
		
//	// 파일 생성하는 방식
//    public static void main(String[] args) {
//        String clientId = "xhb9tw1ld7"; // "애플리케이션 클라이언트 아이디값";
//        try {
//            String key = "m8KdoxMoI3VB3Grt"; // https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey 호출로 받은 키값
//            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key + "&X-NCP-APIGW-API-KEY-ID" + clientId;
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.setRequestMethod("GET");
//            int responseCode = con.getResponseCode();
//            BufferedReader br;
//            if(responseCode==200) { // 정상 호출
//                InputStream is = con.getInputStream();
//                int read = 0;
//                byte[] bytes = new byte[1024];
//                // 랜덤한 이름으로 파일 생성
//                String tempname = Long.valueOf(new Date().getTime()).toString();
//                File f = new File(tempname + ".jpg");
//                f.createNewFile();
//                OutputStream outputStream = new FileOutputStream(f);
//                while ((read =is.read(bytes)) != -1) {
//                    outputStream.write(bytes, 0, read);
//                }
//                is.close();
//            } else {  // 오류 발생
//                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//                while ((inputLine = br.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                br.close();
//                System.out.println(response.toString());
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
}