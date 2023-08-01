package com.mainproject;
//����� �����ͺ��̽� Ȥ�� �޸� ����� ����:
//����� ������ ���� ����� ������ �����ϴ� �����ͺ��̽��� �޸� ����Ҹ� ����. 
//���⼭�� ������ ���ø� ���� �޸𸮿� ����� ������ �����ϴ� ����� ���
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	private Map<String, User> users = new HashMap<>();

    public UserRepository() {
        // ���ø� ���� �̸� ����ڸ� �ϳ� ���.
        User user = new User();
        user.setID("id");
        user.setPWD("password");
        users.put(user.getID(), user);
    }

    public User findByid(String id) {
        return users.get(id);
    }
}
