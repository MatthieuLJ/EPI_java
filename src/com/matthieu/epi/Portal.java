package com.matthieu.epi;

import java.io.Console;
import java.util.HashMap;

public class Portal {
    static HashMap<String, Class<? extends Solution>> problems;
    static {
        problems = new HashMap<String, Class<? extends Solution>>();
        problems.put("2.1", com.matthieu.epi.ComputingParity.class );
    }

    private static void solve_problem(String id) {
        try {
            Solution solution = problems.get(id).newInstance();
            solution.solve_problem();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        Console console = System.console();

        if (console != null) {
            String input = console.readLine("Enter problem to solve:");
            if (problems.containsKey(input)) {
                solve_problem(input);
            } else {
                System.out.println("Cannot find that problem.");
                System.out.println("The problems I know of are:");
                System.out.println(problems.keySet().toString());
            }
        }
        else {
            for (String problem : problems.keySet()) {
                System.out.println("### Problem "+problem);
                solve_problem(problem);
            }
        }
    }
}
