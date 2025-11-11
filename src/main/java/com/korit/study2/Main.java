package com.korit.study2;

import com.korit.study2.dto.GetUserListRespDto;
import com.korit.study2.dto.SigninReqDto;
import com.korit.study2.dto.SignupReqDto;
import com.korit.study2.entity.User;
import com.korit.study2.service.UserService;

import java.util.List;
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
            System.out.print(">> ");
            String selectMenu = scanner.nextLine();

            if ("q".equalsIgnoreCase(selectMenu)) {
                System.out.println("프로그램 종료");
                break;
            } else if ("1".equals(selectMenu)) {
                System.out.println("[ 회원가입 ]");
                //signupReqDto객체 생성
                SignupReqDto signupReqDto = new SignupReqDto();
                //while문으로 username 입력받기
                while (true) {
                    //중복확인 후 중복이면 다시 입력받는거 반복
                    System.out.print("username: ");
                    signupReqDto.setUsername(scanner.nextLine());
                    //중복 아니면 break로 while 빠져나가서 비번 입력받기
                    if (!userService.isDuplicatedUsername(signupReqDto.getUsername())) {
                        break;
                    }
                    System.out.println("이미 사용중인 username입니다.");
                }
                System.out.print("password: ");
                signupReqDto.setPassword(scanner.nextLine());

                //이멜도 username처럼 입력받기
                while (true) {
                    System.out.print("email: ");
                    signupReqDto.setEmail(scanner.nextLine());
                    if (!userService.isDuplicatedEmail(signupReqDto.getEmail())) {
                        break;
                    }
                    System.out.println("이미 가입된 이메일입니다.");
                }
                //마지막으로 signup메소드 호출해서 회원가입
                int result = userService.signup(signupReqDto);
                if (result == 0) {
                    System.out.println("회원가입에 실패하였습니다.");
                }
                System.out.println("회원가입이 완료되었습니다.");
            } else if ("2".equals(selectMenu)) {
                System.out.println("[ 로그인 ]");

                SigninReqDto signinReqDto = new SigninReqDto();
                System.out.print("username: ");
                signinReqDto.setUsername(scanner.nextLine());
                System.out.print("password: ");
                signinReqDto.setPassword(scanner.nextLine());

                User user = userService.signin(signinReqDto);
                if (user != null) {
                    System.out.println("로그인 되었습니다.");
                    System.out.println("로그인된 유저: " + user);
                }
            } else if ("3".equals(selectMenu)) {
                System.out.println("[ 전체회원 조회 ]");

                List<GetUserListRespDto> userListRespDtoList = userService.getUserListAll();
                userListRespDtoList.forEach(System.out::println);
            } else if ("4".equals(selectMenu)) {
                System.out.println("[ 회원 검색 ]");
                System.out.print("username: ");
                String keyword = scanner.nextLine();
                List<GetUserListRespDto> userListRespDtos = userService.getUserListByKeyword(keyword);
                userListRespDtos.forEach(System.out::println);
            }
        }
    }
}
