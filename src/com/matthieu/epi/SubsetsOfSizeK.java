package com.matthieu.epi;

import java.util.ArrayList;
import java.util.List;

public class SubsetsOfSizeK implements Solution {

    private int weight(long input) {
        int res=0;

        while(input != 0) {
            input = input & (input-1);
            res ++;
        }

        return res;
    }

    private void printSetOfSetSize(List list, int k) {
        long size = list.size();
        if (size > 64)
            System.out.println("A bit too many elements to handle");
        for (long i=1L; i< (1L << (size)); i++) {
            if (weight(i) != k)
                continue;
            long t=i;
            while(t!=0) {
                int item = (int) (Math.log(t & ~(t-1))/Math.log(2));
                System.out.print(list.get(item).toString());
                t = t & (t-1);
            }

            if (i<(1L << (size))-1)
                System.out.print(", ");
        }
    }

    private void printSetOfSetSize2(List list, int k) {
        int size = list.size();
        int indices[] = new int[k+2];
        for (int i=0; i<k; i++)
            indices[i]=i;
        indices[k]=size;
        indices[k+1]=0;
        do {
            for (int i=0; i<k; i++) {
                System.out.print(list.get(indices[i]).toString());
            }
            int j=0;
            while (indices[j]+1 == indices[j+1]) {
                indices[j]=j;
                j++;
            }
            if (j>=k)
                break;
            System.out.print(", ");
            indices[j]=indices[j]+1;
        } while(true);
    }

    @Override
    public void solveProblem() {
        List<String> test = new ArrayList<String>();
        test.add("A");
        test.add("B");
        test.add("C");
        test.add("D");
        test.add("E");
        System.out.println("Sets of size 3 for list "+test.toString()+" are ");
        printSetOfSetSize(test, 3);
        System.out.println();
        System.out.println("Or also: ");
        printSetOfSetSize2(test, 3);
        System.out.println();
    }
}
