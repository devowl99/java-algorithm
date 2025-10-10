import java.io.*;
import java.util.*;

public class BJ12865 {
	static int N, K;
	static int[][] dp;
	static int[] W;
	static int[] V;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		W = new int[N+1];
		V = new int[N+1];
		dp = new int[N+1][K+1]; // 물건 수, 최대 무게
		
		// 물건 수만큼 for문 돌리기
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int w=0; w<=K; w++) {
			dp[0][w] = 0;
		}
		
		// dp[i][w]
		for (int i=1; i<=N; i++) {
			for (int w=0; w<=K; w++) {
				// 1. 선택 안하는 경우
				dp[i][w] = dp[i-1][w]; // 물건 1개 덜 고려했던 이전과 동일 (무게 역시 동일)
				
				// 2. 선택 하는 경우 (가방이 담을 수 있는 무게가 선택하는 물건의 무게보다 클 경우)
				if (w >= W[i]) {
					dp[i][w] = Math.max(dp[i][w], dp[i-1][w-W[i]] + V[i]);
				}
			}
		}
		System.out.println(dp[N][K]);	
	}
}
