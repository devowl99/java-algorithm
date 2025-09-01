import java.io.*;
import java.util.*;

public class BJ11582 {
	static int N;
	static List<Integer> score;
	static int k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		score = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			score.add(Integer.parseInt(st.nextToken()));
		}
		
		solve(score, score.length, 0);
	}
	
	static void solve(int[] arr, int l, int count) {
		if (count == k) {
			return;
		}
		int len = l/2;
		
		int arrL[] = new int[len];
		for (int i=0; i<len; i++) {
			arrL[i] = score[i];
		}
		int arrR[] = new int[len];
		for (int i=len; i<l; i++) {
			arrR[i-len] = score[i];
		}
		
		solve(arrL, arrL.length, count+1);
		solve(arrR, arrR.length, count+1);
	}
}
