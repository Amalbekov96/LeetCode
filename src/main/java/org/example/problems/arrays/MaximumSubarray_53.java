package org.example.problems.arrays;

public class MaximumSubarray_53 {
    public static int [] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

    public static int maxSumArray() {

        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for(int i=0;i<n;i++){
            sum += nums[i];
            max = Math.max(sum,max);

            if(sum<0) sum = 0;
        }

        return max;

    }
}
