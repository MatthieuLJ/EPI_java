package com.matthieu.epi;

public class DifficultMultiplication implements Solution {
    public int add(int a, int b) {
        int carry=0;
        int i=1;
        int res = 0;

        if (a==0)
            return b;
        if (b==0)
            return a;

        while((a!=0) || (b!=0)) {
            res = res | ((a&i) ^ (b&i));
            carry = carry | ((a&i) & (b&i));

            a = a & (~i);
            b = b & (~i);
            i = i<<1;
        }

        return add(carry<<1, res);
    }

    public int multiply(int a, int b) {
        int i=1;
        int res=0;

        while (b!=0) {
            int mask = 0x1 << i;
            if ((b&mask) != 0) {
                res = add(res, a<<i);
                b = b & (~mask);
            }

            i = add(i,1);
        }
        return res;
    }

    @Override
    public void solveProblem() {
        System.out.println("34 x 75 = "+multiply(34,75));
        System.out.println("340 x 5875 = "+multiply(340,5875));
    }
}
