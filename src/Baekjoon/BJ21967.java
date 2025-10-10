import java.io.*;
import java.util.*;

public class BJ21967 {
	static int N;
	static int[] nums;
	static Queue<Integer> conNums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		conNums = new ArrayDeque<>();
		int maxLen = 0;
		for (int end=0; end<N; end++) {
			conNums.add(nums[end]);
			
			while(Collections.max(conNums) - Collections.min(conNums) > 2) {
					conNums.poll();
			}
			maxLen = Math.max(maxLen, conNums.size());
		}
		System.out.println(maxLen);
	}
}
