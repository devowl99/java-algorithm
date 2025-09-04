import java.io.*;
import java.util.*;

public class SWEA1952 {
	static int T;
	static int day, month, month3, year;
	static int[] usePlan;
	static int[] dp; // 가격
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			month3 = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			
			usePlan = new int[13];
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=12; i++) {
				usePlan[i] = Integer.parseInt(st.nextToken());
			}
			
			dp = new int[13];
			dp[0] = 0;
			for (int i=1; i<=12; i++) {
				// 1일권, 1달권
				dp[i] = Math.min(dp[i-1] + usePlan[i]*day, dp[i-1] + month);
				
				// 3달권
				if (i>=3) {
					dp[i] = Math.min(dp[i], dp[i-3] + month3);
				}
						
				// 1년권
				if (i==12) {
					dp[i] = Math.min(dp[i], dp[i-12] + year);
				}
			}
			sb.append('#').append(tc).append(' ').append(dp[12]).append('\n');
		}
		System.out.println(sb);
	}
}
