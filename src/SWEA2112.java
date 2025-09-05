import java.io.*;
import java.util.*;

public class SWEA2112 {
	static int T;
	static int D; // 보호 필름의 두께 (보호필름을 몇 장 쌓는가?)
	static int W; // 가로 크기
	static int K; // 합격 기준
	static boolean pass;
	static int best;
	static boolean hasFound;
	
	// 보호 필름의 모든 열이 합격 기준을 통과해야 함
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[][] film = new int[D][W];
			boolean[] changed = new boolean[D];
			
			for (int d=0; d<D; d++) {
				st = new StringTokenizer(br.readLine());
				for (int w=0; w<W; w++) {
					film[d][w] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append('#').append(tc).append(' ');
		
			// 약품 투입 없이 성능 통과
			if (passCheck(film)) sb.append(0).append('\n');
			// 약품 투입 없으면 통과 안됨 (첫 변경)
			else {
				best = Integer.MAX_VALUE;
				dfs(film, 0, 0);
				sb.append(best).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	static boolean passCheck(int[][] film) {
		if (K==1) return true;
		
		for (int w=0; w<W; w++) {
			boolean ok = false;
			int count = 1;
			for (int d=1; d<D; d++) {
				if (film[d][w] == film[d-1][w]) {
					count++;
					if (count >= K) {
						ok = true;
						break;
					}
				}
				else count = 1;
			}
			if (!ok) return false;
		}
		return true;
	}
	
	static void dfs(int[][] film, int row, int depth) {
		if (depth >= best) return;
		
		// 한 줄 바꿔서 검사 통과했다면
		if (passCheck(film)) {
			best = depth;
			return;
		}
		if (row == D) return;
		
		// 첫번째 줄부터 바꿔보자.
		// case는 2가지 경우가 있다 (약품 0, 약품 1)
		
		// 백트래킹을 위해 변경 전 행 복사
		int[] save = film[row].clone();
		
		// 행 변경 안함
		dfs(film, row+1, depth);
		
		// case 1. 약품 0
		Arrays.fill(film[row], 0);
		dfs(film, row+1, depth+1);
		System.arraycopy(save, 0, film[row], 0, W);
		
		// case 2. 약품 1
		Arrays.fill(film[row], 1);
		dfs(film, row+1, depth+1);
		System.arraycopy(save, 0, film[row], 0, W);
	}
}