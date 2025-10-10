import java.io.*;
import java.util.*;

public class BJ2624 {
	static int T; // 지폐의 금액
	static int K; // 동전의 가지 수
	static int[] p, n; // 동전 하나의 금액, 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		p = new int[K];
		n = new int[K];
		for (int k=0; k<K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			p[k] = Integer.parseInt(st.nextToken());
			n[k] = Integer.parseInt(st.nextToken());
		}
	}
}