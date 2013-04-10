package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Arrays;

public class TwoArraysIntersection implements Solution {
    public static <T extends Comparable<T>> T[] intersectionSimilarSize(T a[], T b[]) {
        int indexA=0, indexB=0;
        ArrayList<T> res = new ArrayList<T>();

        while ((indexA<a.length) && (indexB<b.length)) {
            if (a[indexA].compareTo(b[indexB])==0) {
                res.add(a[indexA]);
                indexA++;
                indexB++;
            } else if (a[indexA].compareTo(b[indexB])<0) {
                indexA++;
            } else {
                indexB++;
            }
        }
        return res.toArray(Arrays.copyOfRange(a, 0, res.size()));

    }

    @Override
    public void solveProblem() {
        Integer a[] = new Integer[50];
        Integer b[] = new Integer[50];
        for (int i=0; i<a.length; i++) {
            a[i] = (int) (Math.random()*100);
            b[i] = (int) (Math.random()*100);
        }
        Arrays.sort(a);
        Arrays.sort(b);
        System.out.println("Intersection of "+Arrays.toString(a)+"\n and "+Arrays.toString(b)+"\nis "+Arrays.toString(intersectionSimilarSize(a, b)));
    }
}
