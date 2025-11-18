package com.korit.ex;

public class Ex9 {
    public static void main(String[] args) {
        int[] nums = {10, -3, 0, 25, 7};

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];

            // 양수일 때만 합산
            if (n > 0) {
                sum += n;
            }
        }

        System.out.println("양수 합계: " + sum);
    }
}
