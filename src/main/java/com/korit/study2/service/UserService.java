package com.korit.study2.service;

import com.korit.study2.dao.UserDao;
import com.korit.study2.dto.SigninReqDto;
import com.korit.study2.dto.SignupReqDto;
import com.korit.study2.entity.User;
import com.korit.study2.util.PasswordEncoder;

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
    public boolean isDuplicatedEmail(String email) {
        Optional<User> foundUser = userDao.findUserByEmail(email);
        return foundUser.isPresent();
    }
    // 회원가입
    public int signup(SignupReqDto signupReqDto) {
        return userDao.addUser(signupReqDto.toEntity());
    }
    // 로그인
    public User signin(SigninReqDto signinReqDto) {
        Optional<User> foundUser = userDao.findUserByUsername(signinReqDto.getUsername());
        if (foundUser.isEmpty()) {
            System.out.println("회원 정보를 다시 확인해주세요.");
            return null;
        }
        User user = foundUser.get();
        if (!PasswordEncoder.match(signinReqDto.getPassword(), user.getPassword())) {
            System.out.println("회원 정보를 다시 확인해주세요.");
            return null;
        }
        return user;
    }
    // 회원 전체 조회
    // 회원 검색
}
