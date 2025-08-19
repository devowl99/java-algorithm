import java.io.*;
import java.util.*;

public class SWEA1873 {
	static int T;
	static int H,W;
	static char[][] map;
	static int[] tank = {0,0};
	static int inputNum;
	static char[] input;
	// 전 후 좌 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			for (int i=0; i<H; i++) {
				String str = br.readLine();
				for (int j=0; j<str.length(); j++) {
					char cur = str.charAt(j);
					if (cur == '^' || cur == 'v' || cur == '<' || cur == '>') {
						tank[0] = i;
						tank[1] = j;
					}
					map[i][j] = cur;
				}
			}
			
			inputNum = Integer.parseInt(br.readLine());
			input = new char[inputNum];
			String str = br.readLine();
			for (int i=0; i<inputNum; i++) {
				input[i] = str.charAt(i);
			}
			
			for (int i=0; i<inputNum; i++) {
				char order = input[i];
				orderReceived(order);
			}
			
			sb.append("#").append(tc).append(" ");
			for (int i=0; i<H; i++) {
				sb.append(map[i]).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static void orderReceived(char order) {
		int nr = 0;
		int nc = 0;
		
		// 전 0
		if (order == 'U') {
			nr = tank[0] + dr[0];
			nc = tank[1] + dc[0];
			map[tank[0]][tank[1]] = '^';
			
			if (0<=nr && nr<H && 0<=nc && nc<W) {
				if (map[nr][nc] == '.') {
					map[nr][nc] = '^';
					map[tank[0]][tank[1]] = '.';
					tank[0] = nr;
					tank[1] = nc;
				}
			}
		}
		
		// 후 1
		if (order == 'D') {
			nr = tank[0] + dr[1];
			nc = tank[1] + dc[1];
			map[tank[0]][tank[1]] = 'v';
			
			if (0<=nr && nr<H && 0<=nc && nc<W) {
				if (map[nr][nc] == '.') {
					map[nr][nc] = 'v';
					map[tank[0]][tank[1]] = '.';
					tank[0] = nr;
					tank[1] = nc;
				}
			}
		}
		
		// 좌 2
		if (order == 'L') {
			nr = tank[0] + dr[2];
			nc = tank[1] + dc[2];
			map[tank[0]][tank[1]] = '<';
			
			if (0<=nr && nr<H && 0<=nc && nc<W) {
				if (map[nr][nc] == '.') {
					map[nr][nc] = '<';
					map[tank[0]][tank[1]] = '.';
					tank[0] = nr;
					tank[1] = nc;
				}
			}
		}
		
		// 우 3
		if (order == 'R') {
			nr = tank[0] + dr[3];
			nc = tank[1] + dc[3];
			map[tank[0]][tank[1]] = '>';
			
			if (0<=nr && nr<H && 0<=nc && nc<W) {
				if (map[nr][nc] == '.') {
					map[nr][nc] = '>';
					map[tank[0]][tank[1]] = '.';
					tank[0] = nr;
					tank[1] = nc;
				}
			}
		}
		
		// 발포
		if (order == 'S') {
			if (map[tank[0]][tank[1]] == '^') {
				nr = tank[0];
				nc = tank[1];
				
				while (true) {
					nr = nr + dr[0];
					nc = nc + dc[0];
					if (0<=nr && nr<H && 0<=nc && nc<W) {
						if (map[nr][nc] == '*') {
							map[nr][nc] = '.';
							break;
						}
						else if (map[nr][nc] == '#') {
							break;
						}
					}
					else break;
				}
			}
			else if (map[tank[0]][tank[1]] == 'v') {
				nr = tank[0];
				nc = tank[1];
				
				while (true) {
					nr = nr + dr[1];
					nc = nc + dc[1];
					if (0<=nr && nr<H && 0<=nc && nc<W) {
						if (map[nr][nc] == '*') {
							map[nr][nc] = '.';
							break;
						}
						else if (map[nr][nc] == '#') {
							break;
						}
					}
					else break;
				}
			}
			else if (map[tank[0]][tank[1]] == '<') {
				nr = tank[0];
				nc = tank[1];
				
				while (true) {
					nr = nr + dr[2];
					nc = nc + dc[2];
					if (0<=nr && nr<H && 0<=nc && nc<W) {
						if (map[nr][nc] == '*') {
							map[nr][nc] = '.';
							break;
						}
						else if (map[nr][nc] == '#') {
							break;
						}
					}
					else break;
				}
			}
			else if (map[tank[0]][tank[1]] == '>') {
				nr = tank[0];
				nc = tank[1];
				
				while (true) {
					nr = nr + dr[3];
					nc = nc + dc[3];
					if (0<=nr && nr<H && 0<=nc && nc<W) {
						if (map[nr][nc] == '*') {
							map[nr][nc] = '.';
							break;
						}
						else if (map[nr][nc] == '#') {
							break;
						}
					}
					else break;
				}
			}
		
		}
	}
}
