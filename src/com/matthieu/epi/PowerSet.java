package com.matthieu.epi;

import java.util.ArrayList;
import java.util.List;

public class PowerSet implements Solution {

    private void printPowerSet(List list) {
        long size = list.size();
        if (size > 64)
            System.out.println("A bit too many elements to handle");
        System.out.print("nil, ");
        for (long i=1L; i< (1L << (size)); i++) {
            long t=i;
            while(t!=0) {
                int item = (int) (Math.log(t & ~(t-1))/Math.log(2));
                System.out.print(list.get(item).toString());
                t = t & (t-1);
            }

            if (i<(1L << (size))-1)
                System.out.print(", ");
        }
        System.out.println();
    }

    @Override
    public void solveProblem() {
        List<String> test = new ArrayList<String>();
        test.add("A");
        test.add("B");
        test.add("C");
        System.out.println("Power set for list "+test.toString()+" is ");
        printPowerSet(test);
    }
}
