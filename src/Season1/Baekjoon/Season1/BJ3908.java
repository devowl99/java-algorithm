package Season1;

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
			// c�� ���� �Ҽ��� �̵��� �� �ִ°�?
			
			// (A - �Ҽ� = �Ҽ�) => dp[A] = 2
			// (B - �Ҽ� = A) => dp[B] = 3
			// ...
			for (int i=2; i<=n; i++) {
				// �Ҽ��� �ش��ϴ� ���� 1
				for (int x: prime) {
					if (i == x) {
						dp[i] = 1;
						break;
					}
				}
				if (dp[i] == 1) continue; // �Ҽ� 1ó�������� continue
				
				for (int x: prime) {
					// �˻��� �� i�� �Ҽ� x���� ũ��, ������ �Ҽ� ���� �� 1�� �ƴ� ��
					if (i>x && i-x != 1) {
						dp[i] = dp[i-x] + 1;
					}
				}
			}
		}		
	}
	
	// n ������ �Ҽ� ���ϱ�
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
