package Baekjoon;

import java.io.*;
import java.util.*;

public class BJ11000 {
    static int N;
    static int[][] time;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N][2];

        for (int n=0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[n][0] = Integer.parseInt(st.nextToken()); // 시작
            time[n][1] = Integer.parseInt(st.nextToken()); // 끝
        }
        pq = new PriorityQueue<>();


    }
}
