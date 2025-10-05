import java.io.*;
import java.util.*;

public class SWEA7465 {
	static int T;
	static int N, M;
	static List<Integer>[] adjList;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			visited = new boolean[N+1];
			for (int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<>();
			}
			for (int c=0; c<M; c++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st2.nextToken());
				int v = Integer.parseInt(st2.nextToken());
				
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			int count = 0;
			for (int i=1; i<=N; i++) {
				if (!visited[i]) {
					dfs(i);
					count++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int n) {
		visited[n] = true;
		for (int x: adjList[n]) {
			if (!visited[x]) dfs(x);
		}
	}
}