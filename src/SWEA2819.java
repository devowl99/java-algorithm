import java.io.*;
import java.util.*;

public class SWEA2819 {
	static int T;
	static int[][] board;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static Set<String> numSet;
	
	static class Node {
		int x, y;
		String s;
		Node(int x, int y, String s){
			this.x = x;
			this.y = y;
			this.s= s;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			board = new int[4][4];
			
			for (int i=0; i<4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<4; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			numSet = new HashSet<>();
			for (int y=0; y<4; y++) {
				for (int x=0; x<4; x++) {
					dfs(new Node(x, y, Integer.toString(board[y][x])), 1);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(numSet.size()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(Node cur, int depth) {
		if (depth == 7) {
			numSet.add(cur.s);
			return;
		}
		
		int x = cur.x;
		int y = cur.y;
		String s = cur.s;
		
		for (int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (0<=nx && nx<4 && 0<=ny && ny<4) {
				StringBuilder sb = new StringBuilder();
				sb.append(s).append(board[ny][nx]);
				dfs(new Node(nx, ny, sb.toString()), depth+1);
			}
		}
	}
}
