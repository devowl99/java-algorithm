import java.io.*;
import java.util.*;

public class SWEA7465S {
	static int T;
	static int N, M;
	static int[] p;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			p = new int[N+1];
			make();
			
			for (int i=0; i<M; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st2.nextToken());
				int b = Integer.parseInt(st2.nextToken());
				union(a,b);
			}
			
			Set<Integer> set = new HashSet<>();
			for (int i=1; i<=N; i++) {
				set.add(find(i));
			}
			sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void make() {
		for (int i=1; i<=N; i++) p[i] = i;
	}
	
	static int find(int x) {
		if (p[x] == x) return x;
		return p[x] = find(p[x]);
	}
	
	static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX != rootY) p[rootX] = rootY;
	}
}