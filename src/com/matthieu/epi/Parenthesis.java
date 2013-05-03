package com.matthieu.epi;

public class Parenthesis implements Solution {
    public static void combinationParenthesis(String str, int count, int n) {
        if ((n==0) && (count ==0)) {
            System.out.println(str);
            return;
        }
        if (count > 0) {
            combinationParenthesis(str+")", count-1, n);
        }
        if (n>0) {
            combinationParenthesis(str+"(", count+1, n-1);
        }
    }


    @Override
    public void solveProblem() {
        combinationParenthesis("", 0, 3);
    }
}
