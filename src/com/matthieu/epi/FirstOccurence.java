package com.matthieu.epi;

import java.util.Arrays;

public class FirstOccurence implements Solution {
    public static int firstOccurenceOfK(int []array, int k) {
        int l=0, r=array.length-1;
        int mid=0;
        while (l<r) {
            mid = l+ (r-l)/2;
            if (array[mid]<k) l=mid+1;
            else if (array[mid]>k) r=mid-1;
            else break;
        }
        if ((l==r) && (array[l] != k)) return -1;

        while ((mid > 0) && (array[mid]==k)) mid--;
        if ((mid == 0) && (array[mid] == k)) return 0;
        return mid+1;

    }
    @Override
    public void solveProblem() {
        int h[] = new int[50];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        Arrays.sort(h);
        int random_index = (int) (Math.random()*50);
        System.out.println("Find first occurence of "+h[random_index]+" at "+firstOccurenceOfK(h, h[random_index])+" in array "+Arrays.toString(h));
    }
}
