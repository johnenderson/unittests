package dev.jes;

import java.util.HashMap;
import java.util.Map;

public class TwoSumBruteForce {

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("Nenhuma solução encontrada");
    }

    public static void main(String[] args) {
        int[] nums = {11, 15, 2, 7};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("Índices: [" + result[0] + ", " + result[1] + "]");
    }
}

