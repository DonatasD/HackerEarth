package com.donatasd.julyeasy16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Solution to "Gudi Trapped in the Room" problem. Full problem description can be found:
 * <a href="https://www.hackerearth.com/july-easy-16/algorithm/gudi-trapped-in-the-room-july-easy">Gudi Trapped in the Room</a>
 *
 * @author Donatas Daubaras
 */
public class GTITR {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String cur = br.readLine();
            int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(solve(cur, data[0], data[1]));
        }
    }

    /**
     * Brute force approach to find all possible permutations white using functions rotateString and addToString
     *
     * @param numb Given numeric String
     * @param a number that is allowed to be added to every second element of numb
     * @param h number of positions that can be used to rotate numb
     * @return minimum solution
     */
    public static String solve(String numb, int a, int h) {
        LinkedList<String> queue = new LinkedList<>();
        Map<String, Boolean> map = new HashMap<>();
        queue.add(numb);
        while(!queue.isEmpty()) {
            String cur = queue.poll();
            if (!map.containsKey(cur)) {
                map.put(cur, Boolean.TRUE);
                queue.push(rotateString(cur, h));
                queue.push(addToString(cur, a));
            }
        }
        return Collections.min(map.keySet());
    }

    /**
     * Rotate String clockwise by nPos.
     *
     * @param str String to rotate
     * @param nPos number of positions to rotate (positive values allowed only)
     * @return rotated String
     */
    public static String rotateString(String str, int nPos) {
        int lenStr = str.length();
        char[] charStr = str.toCharArray();
        char[] result = new char[lenStr];
        nPos = nPos % lenStr;
        for (int i = 0; i < lenStr; i++) {
            int pos = (i + nPos) % lenStr;
            result[pos] = charStr[i];
        }
        return new String(result);
    }

    /**
     * Add provided value to every second String element
     *
     * @param str provided String that can be transformed to numeric value
     * @param add number to be added
     * @return modified String
     */
    public static String addToString(String str, int add) {
        char[] charStr = str.toCharArray();
        int lenStr = str.length();
        for (int i = 1; i < lenStr; i+=2) {
            charStr[i] =  Character.forDigit((Character.getNumericValue(charStr[i]) + add) % 10, 10);
        }
        return new String(charStr);
    }


}
