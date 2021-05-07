package com.company;

public class Main {

    // Ref: https://gist.github.com/soundsmitten/4272320
    public static int[] Prim(int n, int[][] W, int[] nearest) {
        int[] distance = new int[]{};


        for (int i = 1; i <= n; i++) {
            nearest[i] = 1;
            distance[i] = W[1][i];
        }

        for (int j = 1; j <= n - 1; j++) {
            int min = distance[1]; // or min = infinity
            int vnear = n - 1;       // or min = infinity
            for (int i = 1; i <= n; i++) {
                if (distance[i] < min && distance[i] > 0) {
                    min = distance[i];
                    vnear = i;
                }
            }
            distance[vnear] = 0;
            for (int i = 1; i <= n; i++) {
                if (W[vnear][i] < distance[i]) {
                    distance[i] = W[vnear][i];
                    nearest[i] = vnear;
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        // write your code here



        /* Let us create the following graph
        2 3
        (0)--(1)--(2)
        | / \ |
        6| 8/ \5 |7
        | /     \ |
        (3)-------(4)
            9         */
        SequentialMST t = new SequentialMST();
        int graph[][] = new int[][]{
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        // Print the solution
        t.primMST(graph);
    }
}
