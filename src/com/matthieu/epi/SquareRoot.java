package com.matthieu.epi;

public class SquareRoot implements Solution {
    public static float squareRoot(float x) {
        float l=0, r=x+1;
        float res=0;
        
        while (r-l > 0.0001) {
            res = l+(r-l)/2;
            if (res*res > x) r=res;
            else if (res*res < x) l=res;
            else return res;
        }
        return res;
    }

    @Override
    public void solveProblem() {
        System.out.println("Square root of 2 is around "+squareRoot(2));
    }
}
