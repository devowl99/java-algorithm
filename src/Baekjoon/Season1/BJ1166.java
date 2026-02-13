package Season1;

import java.io.*;
import java.util.*;

public class BJ1166 {
	static int N; // ���� ũ���� �ڽ� ��
	static int L, W, H;
	static double minA;
	static double maxA;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// �� ���� ���� A�� �ִ밡 �� �� �ֵ���
		minA = 0.0;
		maxA = Math.min(L, Math.min(W, H));
		for (int i=0; i<100; i++) {
			double mid = (minA + maxA) / 2;
			
			double count = (long)(L/mid) * (long)(W/mid) * (long)(H/mid);
			
			if (count < N) {
				maxA = mid;
			}
			else {
				minA = mid;
			}
		}
		
		System.out.println(maxA);
	}
}
