package dev.jes;

import java.util.HashMap;
import java.util.Map;

public class TwoSumHashMap {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complemento = target - nums[i];

            if (map.containsKey(complemento)) {
                return new int[]{map.get(complemento), i};
            }

            map.put(nums[i], i);
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
