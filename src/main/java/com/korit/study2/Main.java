package com.korit.study2;

import com.korit.study2.entity.User;
import com.korit.study2.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = UserService.getInstance();

        while (true) {
            System.out.println("[ 회원관리 ]");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 전체회원 조회");
            System.out.println("4. 회원 검색");
            System.out.println("q. 종료");
            String selectMenu = scanner.nextLine();

            if ("q".equalsIgnoreCase(selectMenu)) {
                System.out.println("프로그램 종료");
                break;
            } else if ("1".equals(selectMenu)) {
                System.out.println("[ 회원가입 ]");

                //todo: 회원가입 메소드 호출
            } else if ("2".equals(selectMenu)) {
                System.out.println("[ 로그인 ]");

                //todo: 로그인 메소드 호출
            } else if ("3".equals(selectMenu)) {
                System.out.println("[ 전체회원 조회 ]");

                //todo: 전체회원 조회 메소드 호출
            } else if ("4".equals(selectMenu)) {
                System.out.println("[ 회원 검색 ]");

                //todo: 회원 검색 메소드 호출
            }
        }
    }
}
