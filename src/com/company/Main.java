package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int[][] copyTwoDimenArray(int[][] matrix) {
        return Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
    }


    public static void main(String[] args) {
        FileReader input1;
        int[][] nodeArray;
        List<Integer> chars = new ArrayList<>();

        System.out.println(System.getProperty("user.dir"));
        try {
            // Read input from file
            // Convert to two dimens array int[][]
            input1 = new FileReader("./graph_gen/test20_0.5_2021-05-25 19:23:36.562059.txt");

            BufferedReader abc = new BufferedReader(input1);

//            int ch;
//            while ((ch = abc.read()) != -1) {
//                System.out.println(ch);
//            }
            while((String line = abc.readLine()) != null) {
                lines.add(line);
                System.out.println(data);
            }


        } catch (FileNotFoundException e) {
            System.out.println("error1");
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (IOException e) {
            System.out.println("error1");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }


        int graph[][] = new int[][]{
                {0, 5, 7, 3, 4, 6, 8, 5, 3, 3, 10, 1, 9, 4, 3, 2, 2, 2, 4, 4},
                {3, 0, 3, 5, 8, 6, 6, 4, 10, 6, 1, 5, 1, 10, 5, 10, 2, 10, 1, 7},
                {1, 8, 0, 10, 5, 10, 6, 1, 10, 3, 9, 1, 4, 8, 7, 7, 5, 8, 3, 2},
                {3, 3, 1, 0, 7, 2, 2, 4, 7, 3, 5, 3, 10, 4, 8, 2, 1, 10, 10, 9},
                {5, 3, 2, 9, 0, 1, 6, 2, 1, 5, 9, 2, 9, 1, 4, 9, 2, 9, 5, 10},
                {1, 1, 4, 8, 10, 0, 9, 8, 10, 4, 7, 7, 4, 7, 2, 10, 4, 3, 7, 8},
                {5, 1, 3, 3, 10, 10, 0, 6, 10, 7, 2, 10, 6, 10, 7, 5, 9, 10, 6, 7},
                {8, 6, 4, 4, 3, 1, 8, 0, 3, 2, 5, 8, 8, 3, 6, 6, 9, 6, 8, 10},
                {2, 4, 1, 9, 8, 2, 9, 4, 0, 8, 4, 6, 7, 2, 10, 8, 7, 2, 5, 1},
                {7, 3, 7, 5, 9, 7, 2, 8, 3, 0, 5, 5, 4, 10, 3, 9, 10, 1, 6, 6},
                {8, 6, 3, 10, 10, 6, 7, 4, 9, 2, 0, 5, 3, 2, 8, 6, 6, 9, 6, 9},
                {6, 7, 2, 10, 3, 10, 5, 3, 1, 5, 8, 0, 5, 9, 4, 7, 9, 4, 2, 8},
                {2, 9, 1, 9, 3, 4, 3, 8, 5, 4, 9, 6, 0, 7, 2, 4, 4, 4, 4, 10},
                {6, 1, 7, 2, 3, 9, 1, 9, 7, 4, 7, 9, 4, 0, 8, 4, 6, 2, 6, 8},
                {8, 3, 2, 2, 6, 9, 10, 1, 7, 7, 10, 5, 4, 9, 0, 10, 5, 8, 1, 8},
                {1, 6, 3, 2, 7, 6, 1, 8, 7, 6, 3, 4, 9, 8, 2, 0, 10, 1, 6, 4},
                {10, 10, 10, 8, 7, 7, 6, 7, 4, 10, 1, 3, 2, 3, 3, 7, 0, 1, 9, 4},
                {8, 6, 10, 6, 8, 9, 7, 5, 3, 2, 5, 9, 7, 2, 4, 10, 6, 0, 10, 7},
                {4, 8, 8, 7, 10, 10, 8, 8, 2, 9, 6, 8, 2, 3, 3, 2, 2, 2, 0, 8},
                {10, 4, 3, 10, 8, 3, 3, 2, 9, 7, 9, 7, 9, 8, 10, 10, 1, 10, 7, 0},
        };
        int N = graph[0].length;

        // Setup
        int[][] seqMatrix = copyTwoDimenArray(graph);
        int[][] tnlMatrix = copyTwoDimenArray(graph);

        // Sequential
        SequentialMST seqMST = new SequentialMST(seqMatrix);
        long start = System.nanoTime();
        int[] seqMSTOutput = seqMST.primMST();
        long end = System.nanoTime();
        long sequentialTime = end - start;

        // Sequential - Validate
        int[] seqMSTOutputWithoutRoot = Arrays.stream(seqMSTOutput).filter(x -> x != -1).toArray();
        assert seqMSTOutputWithoutRoot.length == N - 1;

        // Threads & Lock
        ThreadsNLocksMST tnlMST = new ThreadsNLocksMST(tnlMatrix, 10);
        start = System.nanoTime();
        int[] tnlMSTOutput = tnlMST.primMST();
        end = System.nanoTime();
        long threadsNLockTime = end - start;

        // Threads & Lock - Validate
        int[] tnlMSTOutputWithoutRoot = Arrays.stream(tnlMSTOutput).filter(x -> x != -1).toArray();
        assert tnlMSTOutputWithoutRoot.length == N - 1;

        // Final validation
        for (int i = 0; i < N - 1; i++) assert seqMSTOutputWithoutRoot[i] == tnlMSTOutputWithoutRoot[i];


    }
    // TODO Uitlegen waarom ROOT niet meedoet
    // TODO: validate number of edges
}

