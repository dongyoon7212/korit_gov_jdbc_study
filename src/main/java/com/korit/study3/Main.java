package com.korit.study3;

import com.korit.study3.dto.AddPostReqDto;
import com.korit.study3.entity.Post;
import com.korit.study3.service.PostService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PostService postService = PostService.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[ 게시물 관리 ]");
            System.out.println("1. 게시물 추가");
            System.out.println("2. 게시물 단건 조회");
            System.out.println("3. 게시물 username 조회");
            System.out.println("4. 게시물 키워드 조회");
            System.out.println("q. 종료");
            System.out.print(">> ");
            String cmd = scanner.nextLine();

            if ("q".equalsIgnoreCase(cmd)) {
                System.out.println("프로그램 종료");
                break;
            } else if ("1".equals(cmd)) {
                System.out.println("[ 게시물 추가 ]");
                AddPostReqDto addPostReqDto = new AddPostReqDto();

                //중복검사
                while (true) {
                    System.out.print("제목: ");
                    addPostReqDto.setTitle(scanner.nextLine());
                    if (!postService.isDuplicatedTitle(addPostReqDto.getTitle())) {
                        break;
                    }
                    System.out.println("이미 존재하는 게시물 제목입니다.");
                }
                System.out.print("내용: ");
                addPostReqDto.setContent(scanner.nextLine());
                System.out.print("작성자: ");
                addPostReqDto.setUsername(scanner.nextLine());

                int result = postService.addPost(addPostReqDto);
                if (result == 0) {
                    System.out.println("게시물 추가에 실패했습니다.");
                }

                System.out.println("게시물이 등록되었습니다.");

            } else if ("2".equals(cmd)) {
                System.out.println("[ 게시물 단건 조회 ]");
                System.out.print("게시물 id: ");
                Integer postId = scanner.nextInt();
                scanner.nextLine();
                Post post = postService.getPostByPostId(postId);
                if (post == null) {
                    System.out.println("조회된 게시물이 없습니다.");
                } else {
                    System.out.println("조회 결과: " + post);
                }
            } else if ("3".equals(cmd)) {
                System.out.println("[ 게시물 username 조회 ]");
                System.out.print("작성자: ");
                String username = scanner.nextLine();
                List<Post> postList = postService.getPostListByUsername(username);
                if (postList.isEmpty()) {
                    System.out.println("조회된 게시물이 없습니다.");
                } else {
                    postList.forEach(System.out::println);
                }
            } else if ("4".equals(cmd)) {
                System.out.println("[ 게시물 키워드 조회 ]");
                System.out.print("검색 키워드: ");
                String keyword = scanner.nextLine();
                List<Post> postList = postService.getPostListByKeyword(keyword);
                if (postList.isEmpty()) {
                    System.out.println("조회된 게시물이 없습니다.");
                } else {
                    postList.forEach(System.out::println);
                }
            } else {
                System.out.println("메뉴를 다시 입력해주세요.");
            }

        }
    }
}










