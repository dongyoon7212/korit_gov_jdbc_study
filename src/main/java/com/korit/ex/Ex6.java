package com.korit.ex;

public class Ex6 {
    public static void main(String[] args) {
        int[] nums = {30, 10, 50, 7, 20};

        int min = nums[0];
        int minIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) { //현재 최소값보다 작은 값을 찾기 위한 조건식을 작성하시오.
                min = nums[i];
                minIndex = 1; //최소값의 인덱스를 갱신하는 코드를 작성하시오.
            }
        }

        System.out.println("최소값: " + min);
        System.out.println("인덱스: " + minIndex);
    }
}
