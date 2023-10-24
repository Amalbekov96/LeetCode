package org.example.problems.arrays;

public class SlidingWindowMaximum_239 {

    public static final int [] nums = {7,2,4};
    public static final int k = 3;


    public static int[] maxSlidingWindow(int[] nums, int k) {

    int [] ans = new int[(nums.length + 1) - k];


    for (int i = 0; i < (nums.length + 1) - k; i++) {


        if(i == 0 || (ans[i - 1] == nums[i - 1])) {
            int max = Integer.MIN_VALUE;

            for(int j = i; j < i + k; j++) {
                if(nums[j] > max) {
                    max = nums[j];
                }
            }
            ans[i] = max;

        } else {
            if(k > 1 ) {
                if(nums[i + k -1] > ans[i-1]) {
                    ans[i] = nums[i + k - 1];
                } else {
                    ans[i] = ans[i - 1];
                }
            } else {
                ans[i] = nums[i + k -1];
            }

        }

    }

    return ans;
    }
}
