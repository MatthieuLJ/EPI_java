package com.matthieu.epi;

import java.util.Arrays;

public class AnonymousLetter implements Solution {
    private static boolean checkIfArrayIsEmpty(int []array) {
        for (int item : array) {
            if (item > 0)
                return false;
        }
        return true;
    }

    public static boolean checkAnonymousCanBeWritten(String magazine, String letter) {
        int alphabet[] = new int[256];
        Arrays.fill(alphabet, 0);
        for (byte b : letter.getBytes()) {
            alphabet[b+128]++;
        }

        for (byte b : magazine.getBytes()) {
            if (alphabet[b+128] > 0) {
                alphabet[b+128]--;
                if ((alphabet[b+128] == 0) && (checkIfArrayIsEmpty(alphabet)))
                    return true;
            }
        }
        return false;
    }

    @Override
    public void solveProblem() {
        String magazine = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam nec nisi orci. Proin nec quam ante. Ut at sapien quis nisi vulputate mattis. Quisque congue, nisi ut ultricies hendrerit, ipsum massa viverra ipsum, gravida placerat magna ante et ligula. Nulla suscipit arcu quis nulla egestas tincidunt. Nullam eget magna sit amet nibh venenatis scelerisque. Integer sagittis, felis at eleifend vulputate, elit sem viverra orci, in fermentum erat mi ac est. Ut eu felis non magna egestas pretium at at purus. Cras id orci id nisl suscipit pharetra.\n" +
                "Suspendisse malesuada auctor ornare. Integer lacus lectus, porttitor ornare fringilla ut, varius eu magna. In hendrerit dapibus nisi, quis auctor purus facilisis nec. Aliquam in volutpat metus. Praesent accumsan pellentesque euismod. Mauris blandit feugiat urna, sed accumsan justo sodales et. Morbi congue bibendum vulputate. In venenatis sapien et dui feugiat eleifend. Vestibulum vitae leo felis, volutpat aliquam neque.\n" +
                "Donec lacinia orci mauris. Nam tincidunt malesuada lorem, sit amet aliquet nisi convallis quis. Vivamus porta felis at ligula eleifend auctor. Curabitur interdum turpis sit amet ligula semper viverra. Praesent sed interdum libero. Cras venenatis viverra tincidunt. Vivamus in mi eget lorem fermentum malesuada at non dolor. Pellentesque nec commodo felis. Nulla consectetur diam pharetra purus aliquam sit amet bibendum massa egestas. Suspendisse eu magna ante. Pellentesque a nisi justo. Nam ac nulla non nibh faucibus condimentum et a justo. Donec nisi risus, tincidunt non laoreet a, luctus sed sapien. Praesent pharetra faucibus felis. Quisque ut eros vitae nunc volutpat lacinia a at est.\n" +
                "Donec gravida suscipit ante at dictum. Morbi vitae quam magna. Aenean facilisis tristique nibh, non bibendum est ornare sed. Maecenas et lectus et purus gravida rhoncus sagittis sit amet est. Nam adipiscing sapien ac nisl sagittis vel aliquet massa vestibulum. Pellentesque hendrerit nisl a nisl suscipit sit amet auctor massa facilisis. Ut sodales erat dignissim lorem porttitor fringilla.\n" +
                "Proin congue lobortis mauris a dapibus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nulla sit amet justo sit amet tortor mollis congue. Suspendisse orci ante, hendrerit id convallis eu, hendrerit eu nibh. Pellentesque id augue ante. Morbi lorem enim, suscipit id luctus in, condimentum ac velit. Maecenas ut orci vel mi semper tincidunt eget nec massa. Praesent et rutrum orci. Sed nec faucibus metus. Integer quis odio sit amet justo fermentum commodo sed non tellus. Quisque ac auctor nulla. Morbi non est sit amet magna ultrices suscipit eleifend id eros.";

        String anonymousLetter = "I do not believe anything you tell me anymore. Everything you do is an attempt to manipulate my emotions. I was stupid enough to fall for it for years, but I think that I am onto you. We are going to start doing things my way. NO more BS. If you want to me to stay you know what you need to do. WE have done it your way for years and look at us now. This is what failure looks like.";

        System.out.println("If letter can be written : "+checkAnonymousCanBeWritten(magazine, anonymousLetter));

        anonymousLetter = "Nam tincidunt malesuada lorem, sit amet aliquet nisi convallis quis. Vivamus porta felis at ligula eleifend auctor. Curabitur interdum turpis sit amet ligula semper viverra. Praesent sed interdum libero. Cras venenatis viverra tincidunt. Vivamus in mi eget lorem fermentum malesuada at non dolor. Pellentesque nec commodo felis. Nulla consectetur diam pharetra purus aliquam sit amet bibendum massa egestas. Suspendisse eu magna ante. Pellentesque a nisi justo. Nam ac nulla non nibh faucibus condimentum et a justo. Donec nisi risus, tincidunt non laoreet a, luctus sed sapien. Praesent pharetra faucibus felis. Quisque ut eros vitae nunc volutpat lacinia a at est." +
                "Donec gravida suscipit ante at dictum. Morbi vitae quam magna. Aenean facilisis tristique nibh";

        System.out.println("If extract can be written : "+checkAnonymousCanBeWritten(magazine, anonymousLetter));

    }
}
