package org.example.problems.arrays;

import java.util.HashSet;
import java.util.Set;

//Self solved
public class ContainsDuplicate_217 {


    public static final int[] nums = {1, 2, 3, 4};

    public static boolean hasDuplicates() {

        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (numSet.contains(nums[i]))
                return true;
            numSet.add(nums[i]);
        }

        return false;
    }
}
