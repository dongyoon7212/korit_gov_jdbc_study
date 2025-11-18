package com.korit.ex;

public class Ex8 {
    public static void main(String[] args) {
        String raw = "10,20,30,40";

        // 1. 콤마 기준 분리
        String[] parts = raw.split(",");

        int sum = 0;

        // 2. 각 요소를 정수로 변환하여 합계 구하기
        for (int i = 0; i < parts.length; i++) {
            String p = parts[i];
            int num = Integer.parseInt(p);
            sum += num;
        }

        System.out.println("합계: " + sum);
    }
}
