package Season1;

import java.io.*;
import java.util.*;

public class SWEA3282 {
	static int T;
	static int N; // 물건의 개수
	static int K; // 가방의 부피
	static int[] V;
	static int[] C;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 물건 수
			K = Integer.parseInt(st.nextToken()); // 최대 부피
			
			V = new int[N+1]; // 물건의 부피
			C = new int[N+1]; // 물건의 가치
			for(int n=1; n<=N; n++) {
				st = new StringTokenizer(br.readLine());
				V[n] = Integer.parseInt(st.nextToken());
				C[n] = Integer.parseInt(st.nextToken());
			}
			
			dp = new int[N+1][K+1]; // [선택 고려한 수][남은 부피] = 현재 가치(최대)
			Arrays.fill(dp[0], 0); // 선택 고려한 수 0일 때 초기화
			
			for (int i=1; i<=N; i++) {
				for (int w=0; w<=K; w++) {
					// 선택하지 않는 경우
					dp[i][w] = dp[i-1][w];
					
					// 선택하는 경우 (남은 공간이 i번째 물건의 부피보다 크거나 같은 경우)
					if (w >= V[i]) {
						dp[i][w] = Math.max(dp[i][w], dp[i-1][w-V[i]] + C[i]);
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(dp[N][K]).append('\n');
		}
		System.out.println(sb);
	}
}
