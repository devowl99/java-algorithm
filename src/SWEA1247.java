import java.io.*;
import java.util.*;

public class SWEA1247 {
	static int T;
	static int N;
	static Point company;
	static Point home;
	static Point[] customer;
	static boolean[] visited;
	static int shortest;
	
	static class Point{
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			customer = new Point[N];
			for (int i=0; i<N; i++) {
				customer[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			shortest = Integer.MAX_VALUE;
			
			visited = new boolean[N];
			permutation(company.x, company.y, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(shortest).append("\n");
		}
		System.out.println(sb);
	}
	
	static void permutation(int currentX, int currentY, int depth, int totalD) {
		if (totalD > shortest) return;

		if (depth == N) {
			totalD += distance(currentX, home.x, currentY, home.y);
			if (totalD < shortest) shortest = totalD;
			return;
		}
		
		for (int i=0; i<N; i++) {
			if (visited[i]) continue;
			int d = distance(currentX, customer[i].x, currentY, customer[i].y);
			visited[i] = true;
			permutation(customer[i].x, customer[i].y, depth+1, totalD+d);
			visited[i] = false;
		}
	}
	
	static int distance(int x1, int x2, int y1, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
}