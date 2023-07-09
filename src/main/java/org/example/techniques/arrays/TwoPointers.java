package org.example.techniques.arrays;

public class TwoPointers {

    public static int [] nums = {1, 3,2,45,64,4,3,68,86};
    public static int[] twoSum(int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        // If no pair is found, return an empty array or handle it as per the problem's requirements.
        return new int[]{};
    }
}
