package com.mainproject;
//���� ó���� ���� ���� ����:
//���� ó���� ���� ���񽺸� ����. ���⼭�� �Էµ� ����ڸ�� ��й�ȣ�� �����Ͽ� ���� ����� ��ȯ.
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
