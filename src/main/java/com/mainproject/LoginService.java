package com.mainproject;
//인증 처리를 위한 서비스 생성:
//인증 처리를 위한 서비스를 생성. 여기서는 입력된 사용자명과 비밀번호를 검증하여 인증 결과를 반환.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	 @Autowired
	    private UserRepository userRepository;

	    public boolean authenticate(String id, String password) {
	        User user = userRepository.findByid(id);
	        if (user != null && user.getPWD().equals(password)) {
	            return true;
	        }
	        return false;
	    }

}
