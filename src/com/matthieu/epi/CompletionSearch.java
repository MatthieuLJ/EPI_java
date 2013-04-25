package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompletionSearch implements Solution {
    public static float trimSalaries(List<Integer> a, int target) {
        // we search v such as sum(i=0...n-1) min(a[i],v) = target
        // when a is sorted, this is equal to (sum(i=0...k) a[i])+(n-1-k)*v
        // let's first find k

        if (a.size() == 0)
            return 0;

        Collections.sort(a);

        List<Integer> sums = new ArrayList<Integer>();
        int currentSum=0;
        for (int ai : a) {
            currentSum+=ai;
            sums.add(currentSum);
        }

        // check the case where we need to reduce all the salaries
        if (sums.get(0) * a.size() > target) {
            return (float)target / a.size();
        }
        // make sure the sum is larger
        if (sums.get(sums.size()-1) < target) {
            return -1;
        }

        int start=0, end=a.size()-1;
        int mid=0;
        while(start<end) {
            mid = start + (end-start)/2;
            int midMinValue = sums.get(mid)+(a.size()-1-mid)*a.get(mid);
            int midMaxValue = sums.get(mid)+(a.size()-1-mid)*a.get(mid+1);
            if (midMinValue > target) {
                end = mid-1;
            } else if (midMaxValue < target) {
                start=mid+1;
            } else {
                break;
            }
        }
        return (float)(target-sums.get(mid)) / (a.size()-1-mid);
    }
    @Override
    public void solveProblem() {
        System.out.println("Solution for book example is "+trimSalaries(Arrays.asList(30,90,40,100,20), 210));
        System.out.println("Case where all salaries need to be trimmed "+trimSalaries(Arrays.asList(100,150,120,200), 300));
    }
}
