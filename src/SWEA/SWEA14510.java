import java.io.*;
import java.util.*;

public class SWEA14510 {
	static int T;
	static int N;
	static int[] tree;
	static int maxTree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			tree = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			maxTree = 0;
			for (int n=0; n<N; n++) {
				int nextTree = Integer.parseInt(st.nextToken());
				tree[n] = nextTree;
				maxTree = Math.max(maxTree, nextTree);
			}
			
			// 최대 높이에서 각 나무의 높이를 빼서 필요한 높이를 구하자
			// 필요한 높이를 구성할 수 있는 1, 2의 수를 세자 (two 최대, one 최소)
			long two = 0;
			long one = 0;
			for (int n=0; n<N; n++) {
				int needMore = maxTree - tree[n];
				two += needMore / 2;
				one += needMore % 2;
			}
			
			// two 최대, one 최소에서부터 시작하자.
			// 하나의 two를 두 개의 one으로 쪼개서 two와 one의 차이를 점점 줄여나간다.
			// 둘의 차이를 최소한으로 줄이자!
			while(two > one+1) {
				two -= 1;
				one += 2;
			}
			
			long minDay = 0;
			if (one > two) minDay = one*2-1;
			else minDay = two*2;
			
			sb.append('#').append(tc).append(' ').append(minDay).append('\n');
		}
		System.out.println(sb);
	}
}
