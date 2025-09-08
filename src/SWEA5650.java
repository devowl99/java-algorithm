import java.io.*;
import java.util.*;

public class SWEA5650 {
	static int T;
	static int N;
	static int[][] board;
	// 상, 하, 좌, 우
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, -1, 1, 0, 0};
	static int score;
	static int maxScore;
	static Point[][] warp;
		
	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Node {
		int x, y, d;
		Node(int x, int y, int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine().trim());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N+2][N+2];
			warp = new Point[11][2]; // n번 웜홀 => Point 1, 2
			
			Arrays.fill(board[0], 5);
			for (int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				board[i][0] = 5;
				for (int j=1; j<=N; j++) {
					int cur = Integer.parseInt(st.nextToken());
					board[i][j] = cur;
					
					if (cur >= 6) {
						if (warp[cur][0] == null) warp[cur][0] = new Point(j, i);
						else warp[cur][1] = new Point(j, i);
					}
				}
				board[i][N+1] = 5;
			}
			Arrays.fill(board[N+1], 5);
			
			maxScore=0;
			for(int y=1; y<=N; y++) {
				for (int x=1; x<=N; x++) {
					if (board[y][x] != 0) continue;
					
					for (int d=1; d<=4; d++) {
						maxScore = Math.max(maxScore, simulate(new Node(x, y, d), new Point(x, y)));
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(maxScore).append('\n');
		}
		System.out.println(sb);
	}
	
	static int simulate(Node node, Point sp) {
		score = 0;
		while (true) {
			int nx = node.x + dx[node.d];
			int ny = node.y + dy[node.d];
			int d = node.d;
			
			// 탈출 조건 2개
			if (board[ny][nx] == -1) return score;
			if (nx == sp.x && ny == sp.y) return score;
			
			// 블록 0 : 빈 공간
			if (board[ny][nx] == 0) {
				node.x = nx;
				node.y = ny;
			}
			
			// 블록 1
			// 1 : 2->4 / 3->1 / 1->2 / 4->3
			else if (board[ny][nx] == 1) {
				score++;
				node.x = nx;
				node.y = ny;
				
				if (d == 1) node.d = 2;
				else if (d == 2) node.d = 4;
				else if (d == 3) node.d = 1;
				else if (d == 4) node.d = 3;
			}
			
			// 블록 2
			// 2 : 1->4 / 3->2 / 2->1 / 4->3
			else if (board[ny][nx] == 2) {
				score++;
				node.x = nx;
				node.y = ny;
				
				if (d == 1) node.d = 4;
				else if (d == 2) node.d = 1;
				else if (d == 3) node.d = 2;
				else if (d == 4) node.d = 3;
			}
			
			// 블록 3
			// 3 : 1->3 / 4->2 / 2->1 / 3->4
			else if (board[ny][nx] == 3) {
				score++;
				node.x = nx;
				node.y = ny;
				
				if (d == 1) node.d = 3;
				else if (d == 2) node.d = 1;
				else if (d == 3) node.d = 4;
				else if (d == 4) node.d = 2;
			}
			
			// 블록 4
			// 4 : 2->3 / 4->1 / 1->2 / 3->4
			else if (board[ny][nx] == 4) {
				score++;
				node.x = nx;
				node.y = ny;
				
				if (d == 1) node.d = 2;
				else if (d == 2) node.d = 3;
				else if (d == 3) node.d = 4;
				else if (d == 4) node.d = 1;
			}
			
			// 블록 5
			// 5 : 1->2 / 2->1 / 3->4 / 4->3
			else if (board[ny][nx] == 5) {
				score++;
				node.x = nx;
				node.y = ny;
				
				if (d == 1) node.d = 2;
				else if (d == 2) node.d = 1;
				else if (d == 3) node.d = 4;
				else if (d == 4) node.d = 3;
			}
			
			// 웜홀
			else {
				node.x = nx;
				node.y = ny;
				wormhole(node, board[ny][nx]);
			}
		}
	}
	
	static void wormhole(Node node, int holeNum) {
		for (int i=0; i<2; i++) {
			if (node.x == warp[holeNum][i].x && node.y == warp[holeNum][i].y) continue;
			node.x = warp[holeNum][i].x;
			node.y = warp[holeNum][i].y;
			return;
		}
	}
}