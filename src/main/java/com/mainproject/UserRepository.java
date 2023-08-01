package com.mainproject;
//사용자 데이터베이스 혹은 메모리 저장소 생성:
//사용자 인증을 위해 사용자 정보를 저장하는 데이터베이스나 메모리 저장소를 생성. 
//여기서는 간단한 예시를 위해 메모리에 사용자 정보를 저장하는 방법을 사용
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	private Map<String, User> users = new HashMap<>();

    public UserRepository() {
        // 예시를 위해 미리 사용자를 하나 등록.
        User user = new User();
        user.setID("id");
        user.setPWD("password");
        users.put(user.getID(), user);
    }

    public User findByid(String id) {
        return users.get(id);
    }
}
