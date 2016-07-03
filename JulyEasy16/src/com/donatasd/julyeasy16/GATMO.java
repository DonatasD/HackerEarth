package com.donatasd.julyeasy16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Solution to "Gudi and the Magical Orbs" problem. Full problem description can be found:
 * <a href="https://www.hackerearth.com/july-easy-16/algorithm/gudi-and-the-magical-orbs-july-easy">Gudi and the Magical Orbs</a>
 *
 * @author Donatas Daubaras
 */
public class GATMO {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[] params = br.readLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int m = Integer.parseInt(params[1]);
            int k = Integer.parseInt(params[2]);
            int[][] dataGrid = new int[n][m];
            for (int j = 0; j < n; j++) {
                int[] dataLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int l = 0; l < m; l++) {
                    dataGrid[j][l] = dataLine[l];
                }
            }
            System.out.println(solve(dataGrid, k));
        }
    }

    /**
     * Brute force approach to find a maximum value that is lower than provided limit by traveling from 0,0 to (n-1,m-1)
     * coordinates. Provided grid contains the values that are picked by stepping on certain coordinate. Horizontal, vertical and
     * diagonal movements are allowed. However, same coordinate cannot be visited twice.
     *
     * @param grid Provided int matrix containing values that are gained when coordinate is visited.
     * @param limit Value that cannot be exceeded while travelling from 0,0 to (n-1, m-1)
     * @return Maximum possible value that can be picked or -1 if path is not possible without exceeding limit.
     */
    public static int solve(int[][] grid, int limit) {
        if (grid[0][0] > limit) {
            return -1;
        }
        int n = grid.length;
        int m = grid[0].length;
        boolean[][][] moves = new boolean[n][m][limit+1];
        moves[0][0][grid[0][0]] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < limit+1; k++) {
                    // Check if position with such value can be reached
                    if (moves[i][j][k]) {
                        // If possible to move X direction
                        if ((i+1) < n && (k + grid[i+1][j]) <= limit) {
                            moves[i+1][j][k + grid[i+1][j]] = true;
                        }
                        // If possible to move Y direction
                        if ((j+1) < m && (k + grid[i][j+1]) <= limit) {
                            moves[i][j+1][k + grid[i][j+1]] = true;
                        }
                        // If possible to move X and Y direction
                        if ((i+1) < n && (j+1) < m && (k + grid[i+1][j+1]) <= limit) {
                            moves[i+1][j+1][k + grid[i+1][j+1]] = true;
                        }
                    }
                }
            }
        }
        for (int i = limit; i >= 0; i--) {
            if (moves[n-1][m-1][i]) {
                return i;
            }
        }
        return -1;
    }
}
