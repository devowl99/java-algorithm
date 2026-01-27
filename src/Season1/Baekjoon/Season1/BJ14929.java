package Season1;

import java.io.*;
import java.util.*;

public class BJ14929 {
	static int N;
	static int[] arr;
	static int[] P;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		P = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = 0;
		while (st.hasMoreTokens()) {
			arr[cnt] = Integer.parseInt(st.nextToken());
			cnt++;
		}
		
		for (int i=1; i<=N; i++) {
			P[i] = P[i-1] + arr[i-1];
		}
		
		
	}
}
