package com.matthieu.epi;

import java.util.ArrayList;

public class ReachSum implements Solution {
    public static int sums(int target, int num, int maxDigit, ArrayList<Integer> list) {
        // base case
        if (num==0) {
            if (target==0) {
                System.out.println("One possibility: "+list );
                return 1;
            }
            else
                return 0;
        }
        if (target <= 0)
            return 0;

        int res=0;
        for (int i=1; i<=maxDigit; i++) {
            ArrayList<Integer> tmpList = new ArrayList<Integer>(list);
            tmpList.add(i);
            res += sums(target-i, num-1, i, tmpList);
            //res += sums(target-i, num-1, maxDigit, tmpList); // would list all possibilities if order in sum matters (difference dice)
        }

        return res;
    }


    @Override
    public void solveProblem() {
        System.out.println("Number of ways to reach 14 with 5 dice :"+sums(14, 5, 6, new ArrayList<Integer>()));
    }
}
