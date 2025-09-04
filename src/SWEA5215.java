import java.io.*;
import java.util.*;

public class SWEA5215 {
	static int T;
	static int N; // 재료의 수
	static int L; // 제한 칼로리
	static int[] taste;
	static int[] kcal;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			taste = new int[N+1];
			kcal = new int[N+1];
			dp = new int[N+1][L+1];
			
			for (int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				taste[i] = Integer.parseInt(st.nextToken());
				kcal[i] = Integer.parseInt(st.nextToken());
			}
			
			// 재료 선택 고려 시작하지 않은 경우 초기화
			for (int i=0; i<=L; i++) {
				dp[0][i] = 0;
			}
			
			for (int i=1; i<=N; i++) { // 선택을 고려한 재료의 수
				for (int k=0; k<=L; k++) { // 칼로리 제한
					dp[i][k] = dp[i-1][k];
					if (k > kcal[i]) {
						dp[i][k] = Math.max(dp[i][k], dp[i-1][k-kcal[i]] + taste[i]);
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(dp[N][L]).append('\n');
		}
		System.out.println(sb);
	}
}