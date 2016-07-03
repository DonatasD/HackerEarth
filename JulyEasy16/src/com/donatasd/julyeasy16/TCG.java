package com.donatasd.julyeasy16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solution to "The Castle Gate" problem. Full problem description can be found:
 * <a href="https://www.hackerearth.com/july-easy-16/algorithm/the-castle-gate-july-easy">The Castle Gate</a>
 *
 * @author Donatas Daubaras
 */
public class TCG {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(br.readLine());
            System.out.println(xorNumber(cur));
        }
    }

    /**
     * Checks all possible distinct pairs for 1 to n and check if their xor does not exceed n (a1 xor a2 <= n).
     * If that is the case counter is incremented.
     *
     * @param n - integer indicating limit
     * @return number of unordered pairs of distinct integers from 1 to n whose bit-wise XOR does not exceed n.
     */
    private static int xorNumber(int n) {
        int count = 0;
        for (int i = 1; i <= n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                if ((i ^ j) <= n) {
                    count++;
                }
            }
        }
        return count;
    }
}
