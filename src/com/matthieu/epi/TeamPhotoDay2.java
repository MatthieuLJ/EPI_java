package com.matthieu.epi;

import java.util.*;

public class TeamPhotoDay2 implements Solution {
    public static int generalPhotoProblem(List<List<Integer>> teams) {
        int [][] descendant_matrix = new int[teams.size()][teams.size()];
         // descendant_matrix[a][b] is non zero iff team a is smaller than team b
        for (int i=0; i<teams.size(); i++) {
            Arrays.fill(descendant_matrix[i], 0);
            Collections.sort(teams.get(i));
        }

        for (int i=0; i<teams.size(); i++) {
            for (int j=0; j<teams.size(); j++) {
                descendant_matrix[i][j] = TeamPhotoDay.firstIsSmallerTeam(teams.get(i), teams.get(j))?1:0;
            }
        }

        Deque<Integer> teamsInOrder = new ArrayDeque<Integer>();
        while (teamsInOrder.size() < teams.size()) {
            going_through_teams:
            for (int i=0; i<teams.size(); i++) {
                if (teamsInOrder.contains(i))
                    continue;
                int j;
                for (j=0; j<teams.size(); j++) {
                    if (descendant_matrix[i][j]>0) {
                        continue going_through_teams;
                    }
                }
                // cannot find anything taller than team i
                teamsInOrder.addFirst(i);
                for (j=0; j<teams.size(); j++) {
                    if (descendant_matrix[j][i]>0)
                        descendant_matrix[j][i] = -descendant_matrix[j][i];
                }
            }
        }

        int max_path[] = new int[teams.size()];
        int res=0;
        Arrays.fill(max_path, 1); // can always take the picture alone...
        while (teamsInOrder.size() > 0) {
            int smaller = teamsInOrder.removeFirst();
            for (int i=0; i<teams.size(); i++) {
                if (descendant_matrix[i][smaller] != 0) {
                    max_path[smaller] = Math.max(max_path[smaller], max_path[i]+1);
                    if (max_path[smaller] > res)
                        res = max_path[smaller];
                }
            }
        }

        return res;
    }

    @Override
    public void solveProblem() {
        List<List<Integer>> teams = new ArrayList<List<Integer>>();

        int num=20;
        int numPlayers=4;

        for (int i=0; i<num; i++) {
            List<Integer> team = new ArrayList<Integer>();
            for (int j=0; j<numPlayers; j++) {
                team.add((int)(Math.random()*20));
            }
            teams.add(team);
        }

        System.out.println("Got "+generalPhotoProblem(teams)+" teams can take pictures together");
    }
}
