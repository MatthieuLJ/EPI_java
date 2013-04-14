package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KthElement2Lists implements Solution {
    public static int findKthElementHelper(List<Integer> listA, int startA, int endA,
                                           List<Integer> listB, int startB, int endB,
                                           int k) {

        int midA = Math.min(k-1, (startA+endA)/2);
        int midB = k-midA-1;

        System.out.println("Looking from "+startA+" to "+endA+" in A and from "+startB+" to "+endB+" in B, check A at "+midA+" and B at "+midB);

        if (midA==0) {
            if (listB.get(midB)<listA.get(0))
                return listB.get(midB);
            else {
                midA++;
                midB--;
            }
        }
        if (midB==0) {
            if (listA.get(midA) < listB.get(0))
                return listA.get(midA);
            else {
                midB++;
                midA--;
            }
        }

        if ((listA.get(midA)>=listB.get(midB-1)) && (listA.get(midA)<=listB.get(midB))) {
            return listA.get(midA);
        }
        if ((listB.get(midB)>=listA.get(midA-1) && (listB.get(midB)<=listA.get(midA)))) {
            return listB.get(midB);
        }
        if (listA.get(midA) < listB.get(midB-1)) {
            return findKthElementHelper(listA, midA, endA, listB, startB, midB,k);
        }
        if (listB.get(midB) < listA.get(midA-1)) {
            return findKthElementHelper(listA, startA, midA, listB, midB, endB, k);
        }

        System.out.println("Don't know what I'm doing...");
        return -1;
    }

    public static int findKthElement(List<Integer> listA, List<Integer> listB, int k) {
        if (k > listA.size() + listB.size())
            return -1;
        if (listA.size() == 0)
            return listA.get(k);
        if (listB.size() == 0)
            return listB.get(k);

        return findKthElementHelper(listA, 0, listA.size(), listB, 0, listB.size(), k);
    }

    @Override
    public void solveProblem() {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        int num=20;
        for (int i=0; i<num; i++) {
            list1.add((int) (Math.random() * 20));
            list2.add((int) (Math.random() * 20));
        }
        Collections.sort(list1);
        Collections.sort(list2);

        System.out.println("Lists are "+list1+" and "+list2);
        System.out.println("7th element is "+findKthElement(list1, list2, 17));

    }
}
