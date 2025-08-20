import java.io.*;
import java.util.*;

public class SWEA1238 {
	static final int T = 10;
	static int N, start;
	static int[] datas;
	static List<Integer>[] adjList;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[101];
			for (int i=1; i<=100; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			while (st2.hasMoreTokens()) {
				int from = Integer.parseInt(st2.nextToken());
				int to = Integer.parseInt(st2.nextToken());
				adjList[from].add(to);
			}
			
			bfs(start);
			
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		
		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int sz = q.size();
			
			max = Integer.MIN_VALUE;
			for (int i=0; i<sz; i++) {
				int cur = q.poll();
				
				for (int x: adjList[cur]) {
					if (!visited[x]) {
						visited[x] = true;
						q.add(x);
					}
				}
				max = Math.max(max, cur);
			}
		}
	}
}
