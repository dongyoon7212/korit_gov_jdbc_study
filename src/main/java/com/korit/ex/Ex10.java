package com.korit.ex;

public class Ex10 {
    public static int sumArray(int[] arr) {
        int sum = 0;

        // 합계를 구하는 코드 작성
        for (int num : arr) {
            sum += num;
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] data = {3, 6, 9, 12};
        int result = sumArray(data);
        System.out.println("합계: " + result);
    }
}
