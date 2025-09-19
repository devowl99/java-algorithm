import java.io.*;
import java.util.*;

public class BJ3908 {
	static int T;
	static int n, c;
	static List<Integer> prime;
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			sieve();
			
			dp = new int[n+1];
			Arrays.fill(dp, 0);
			// c번 만에 소수로 이동할 수 있는가?
			
			// (A - 소수 = 소수) => dp[A] = 2
			// (B - 소수 = A) => dp[B] = 3
			// ...
			for (int i=2; i<=n; i++) {
				// 소수에 해당하는 수면 1
				for (int x: prime) {
					if (i == x) {
						dp[i] = 1;
						break;
					}
				}
				if (dp[i] == 1) continue; // 소수 1처리했으면 continue
				
				for (int x: prime) {
					// 검사할 수 i가 소수 x보다 크고, 수에서 소수 뺐을 때 1이 아닐 때
					if (i>x && i-x != 1) {
						dp[i] = dp[i-x] + 1;
					}
				}
			}
		}		
	}
	
	// n 까지의 소수 구하기
	static void sieve() {
		boolean[] isPrime = new boolean[T+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		
		for (int i=2; i<=n; i++) {
			if (isPrime[i]) {
				for (int j=i*i; j<=n; j+=i) {
					isPrime[j] = false;
				}
			}
		}
		
		prime = new ArrayList<>();
		for (int i=2; i<=n; i++) {
			if (isPrime[i]) prime.add(i);
		}
	}
}
