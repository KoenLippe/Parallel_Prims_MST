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

    private static final String dataSet20_dens09 = "./graph_gen/test20_0.9_2021-05-25 19:15:36.398335.txt";
    private static final String dataSet20_dens07 = "./graph_gen/test20_0.7_2021-05-25 19:20:05.487032.txt";
    private static final String dataSet20_dens05 = "./graph_gen/test20_0.5_2021-05-25 19:23:36.562059.txt";

    private static final String dataSet40_dens09 = "./graph_gen/test40_0.9_2021-05-25 19:19:26.730093.txt";
    private static final String dataSet40_dens07 = "./graph_gen/test40_0.7_2021-05-25 19:21:01.114692.txt";
    private static final String dataSet40_dens05 = "./graph_gen/test40_0.5_2021-05-25 19:22:21.618305.txt";

    private static final String dataSet80_dens09 = "./graph_gen/test80_0.9_2021-05-25 19:17:05.034943.txt";
    private static final String dataSet80_dens07 = "./graph_gen/test80_0.7_2021-05-25 19:20:50.607229.txt";
    private static final String dataSet80_dens05 = "./graph_gen/test80_0.5_2021-05-25 19:22:27.601878.txt";

    private static final String dataSet160_dens05 = "./graph_gen/test160_0.5_2021-05-25 19:23:42.887359.txt";


    private static final String bigTest = "./graph_gen/test10000_0.5_2021-05-25 20:59:52.056670.txt";

    private static int[][] copyTwoDimenArray(int[][] matrix) {
        return Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
    }

    public static void main(String[] args) {
        int[][] graph = null;
        System.out.println("Reading file");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(bigTest));
            ArrayList<List<Integer>> twoDimen = new ArrayList<>();

            // Read from file & store in arraylist
            String charset;
            while ((charset = reader.readLine()) != null) {
                // Read lines and clean from ,
                List<Integer> row = new ArrayList<>();
                String[] charsetClean = charset.split(",");
                for (String value : charsetClean) {
                    // Strip values
                    String stripped = value.replace("{", "").replace("}", "");
                    row.add(Integer.parseInt(stripped));
                }

                // Assert correct read
                assert charsetClean.length == row.size();
                twoDimen.add(row);
            }

            // Convert arraylist to two dimensional int[][]
            int[][] convertedArray = new int[twoDimen.size()][];
            for (int i = 0; i < twoDimen.size(); i++) {
                List<Integer> row = twoDimen.get(i);
                convertedArray[i] = row.stream().mapToInt(j -> j).toArray();
            }
            graph = convertedArray;

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("Something went wrong wile parsing file.");
        }
        assert graph != null;
        int N = graph[0].length;

        System.out.println("Performing MST");

//        // Setup
//        int[][] seqMatrix = copyTwoDimenArray(graph);
//        int[][] tnlMatrix = copyTwoDimenArray(graph);
//
//        // Sequential
//        SequentialMST seqMST = new SequentialMST(seqMatrix);
//        long start = System.nanoTime();
//        int[] seqMSTOutput = seqMST.primMST();
//        long end = System.nanoTime();
//        long sequentialTime = end - start;
//
//        // Sequential - Validate
//        int[] seqMSTOutputWithoutRoot = Arrays.stream(seqMSTOutput).filter(x -> x != -1).toArray();
//        assert seqMSTOutputWithoutRoot.length == N - 1;
//
//        // Threads & Lock
//        ThreadsNLocksMST tnlMST = new ThreadsNLocksMST(tnlMatrix, 10);
//        start = System.nanoTime();
//        int[] tnlMSTOutput = tnlMST.primMST();
//        end = System.nanoTime();
//        long threadsNLockTime = end - start;
//
//        // Threads & Lock - Validate
//        int[] tnlMSTOutputWithoutRoot = Arrays.stream(tnlMSTOutput).filter(x -> x != -1).toArray();
//        assert tnlMSTOutputWithoutRoot.length == N - 1;
//
//        // Final validation
//        for (int i = 0; i < N - 1; i++) assert seqMSTOutputWithoutRoot[i] == tnlMSTOutputWithoutRoot[i];

//         Benchmarking setup for ThreadsNLock solution
        int[] cores = new int[]{1, 2, 4, 6, 8, 10};
        for (int core : cores) {
            int[][] arrayCopy = Main.copyTwoDimenArray(graph);
            // Threads & Lock
            ThreadsNLocksMST mst = new ThreadsNLocksMST(arrayCopy, core);
            long benchStart = System.nanoTime();
            mst.primMST();
            long benchEnd = System.nanoTime();
            long time = benchEnd - benchStart;
            System.out.printf("N(%d), Cores: %-3d Time: %f\n", N, core, (time / 1e9));
        }

    }
}
// TODO Uitlegen waarom ROOT niet meedoet
// TODO: validate number of edges

