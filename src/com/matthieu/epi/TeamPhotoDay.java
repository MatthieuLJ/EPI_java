package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamPhotoDay implements Solution {
    public static boolean canTakePhoto(List<Integer> team1, List<Integer> team2) {
        if (team1.size() != team2.size())
            return false;
        if (team1.size() == 0)
            return true;

        List<Integer> smaller, larger;
        Collections.sort(team1);
        Collections.sort(team2);
        if (team1.get(0) < team2.get(0)) {
            smaller = team1;
            larger  = team2;
        } else {
            smaller = team2;
            larger  = team1;
        }

        for (int i=0; i<team1.size(); i++) {
            if (smaller.get(i) > larger.get(i))
                return false;
        }
        return true;
    }

    @Override
    public void solveProblem() {
        List<Integer> team1 = new ArrayList<Integer>();
        List<Integer> team2 = new ArrayList<Integer>();
        int num=10;
        for (int i=0; i<num; i++) {
            team1.add((int)(Math.random()*20));
            team2.add((int)(Math.random()*20));
        }
        System.out.println("Teams are "+team1+" and "+team2);
        System.out.println((canTakePhoto(team1, team2)?"Can ":"Cannot ")+"take a picture together");

        team1.clear(); team2.clear();
        for (int i=0; i<num; i++) {
            int new_height = (int)(Math.random()*20);
            team1.add(new_height);
            team2.add(new_height+(int)(Math.random()*2));
        }
        Collections.shuffle(team2);
        System.out.println("Teams are "+team1+" and "+team2);
        System.out.println((canTakePhoto(team1, team2)?"Can ":"Cannot ")+"take a picture together");
    }
}
