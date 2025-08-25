import java.io.*;
import java.util.*;

public class SWEA1868 {
	static int T;
	static int N;
	static char[][] board;
	static int[][] intBoard;
	static boolean[][] visited;
	static int[] dx = {1, -1, 1, -1, 0, 0, 1, -1};
	static int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new char[N][N];
			
			for (int i=0; i<N; i++) {
				String str = br.readLine();
				for (int j=0; j<N; j++) {
					board[i][j] = str.charAt(j);
				}
			}
			
			intBoard = new int[N][N];
			bombCount();
			
			int click = 0;
			visited = new boolean[N][N];
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					if (!visited[y][x] && intBoard[y][x] == 0) {
						bfs(x, y);
						click++;
					}
				}
			}
			
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					if (!visited[y][x] && intBoard[y][x] != -1) {
						visited[y][x] = true;
						click++;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(click).append("\n");
		}
		System.out.println(sb);
	}
	
	static void bombCount() {
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (board[y][x] == '*') {
					intBoard[y][x] = -1;
					continue;
				}
				int count = 0;
				for (int d=0; d<8; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if (0<=nx&&nx<N&&0<=ny&&ny<N) {
						if (board[ny][nx] == '*') count++;
					}
				}
				intBoard[y][x] = count;
			}
		}
	}
	
	static void bfs(int startX, int startY) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[startY][startX] = true;
		q.offer(new int[] {startX, startY});
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int d=0; d<8; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (0<=nx&&nx<N&&0<=ny&&ny<N) {
					if (!visited[ny][nx] && intBoard[ny][nx] == 0) {
						visited[ny][nx] = true;
						q.offer(new int[] {nx, ny});
					}
					else if (!visited[ny][nx] && intBoard[ny][nx] != 0) {
						visited[ny][nx] = true;
					}
				}
			}
		}
	}
}
