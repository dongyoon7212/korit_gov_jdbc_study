package com.korit.study2.service;

import com.korit.study2.dao.UserDao;
import com.korit.study2.entity.User;

import java.util.Optional;

public class UserService {
    private static UserService instance;
    private UserDao userDao;

    private UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService(UserDao.getInstance());
        }
        return instance;
    }

    // username 중복확인
    public boolean isDuplicatedUsername(String username) {
        Optional<User> foundUser = userDao.findUserByUsername(username);
        return foundUser.isPresent();
    }
    // email 중복확인
    // 회원가입
    // 로그인
    // 회원 전체 조회
    // 회원 검색
}
