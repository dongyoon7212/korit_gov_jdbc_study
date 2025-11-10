package com.korit.study2.dao;

public class UserDao {
    private static UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    //username 조회
    //email 조회
    //user 추가
    //user 전체 조회
    //user username으로 조회
}
