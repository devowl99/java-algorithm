package Season1;

import java.io.*;
import java.util.*;

public class SWEA1949 {
	static int T;
	static int N; // 지도 한 변의 크기
	static int K; // 최대 공사 가능 깊이
	static int[][] map;
	static boolean[][] visited;
	static List<Node> nodeList;
	static int longest;
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	// 1. 등산로는 가장 높은 봉우리에서 시작해야 함
	// 2-1. 높은 지형 -> 낮은 지형 (같은 곳도 안됨)
	// 2-1. 상,하,좌,우 이동만 가능
	// 3. 딱 한 곳만 최대 k 깊이만큼 깎을 수 있음
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			int isMax = 0;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					isMax = Math.max(isMax, map[i][j]);
				}
			}
			
			nodeList = new ArrayList<>();
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					if (map[y][x] == isMax) {
						nodeList.add(new Node(x, y));
					}
				}
			}
			
			longest = Integer.MIN_VALUE;
			for (Node startPoint: nodeList) {
				visited = new boolean[N][N];
				visited[startPoint.y][startPoint.x] = true;
				int pathLen = 1;
				int drillChance = 1;
				dfs(startPoint.x, startPoint.y, pathLen, drillChance);
			}
			
			sb.append('#').append(tc).append(' ').append(longest).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int x, int y, int len, int dc) {
		for (int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// 범위 안이고, 방문하지 않은 경로일 경우
			if (0<=nx && nx<N && 0<=ny && ny<N && !visited[ny][nx]) {
				
				// case1) 더 낮은 봉우리인 경우
				if (map[ny][nx] < map[y][x]) {
					visited[ny][nx] = true;
					dfs(nx, ny, len+1, dc);
					visited[ny][nx] = false;
				}
				
				// case2) 같거나 높은 봉우리인 경우
				else if (map[ny][nx] >= map[y][x]) {
					// 깎을 수 있는 횟수가 남아있는 경우
					if (dc > 0) {
						// 최대 깎을 수 있는 높이로 현재 봉우리보다 낮게 만들 수 있는 경우
						if (map[ny][nx]-K < map[y][x]) {
							// 일단 기존 높이 저장
							int save = map[ny][nx];
							// 현재 봉우리보다 1 작게 만들기 (최대한 작게 해야 다음 선택지가 넓어짐)
							map[ny][nx] = map[y][x]-1;
							visited[ny][nx] = true;
							dfs(nx, ny, len+1, dc-1);
							visited[ny][nx] = false;
							map[ny][nx] = save;
						}
					}
				}
			}
		}
		
		longest = Integer.max(longest, len);
	}
}