import java.io.*;
import java.util.*;

public class SWEA2382 {
	static int T;
	static int N; // 한 변의 크기
	static int M; // 격리 시간
	static int K; // 미생물 군집의 수
	static Micro[] micro;
	static boolean[][] medicine;
	static int[] dr = {0, -1, 1, 0, 0}; // 0 1 2 3 4
	static int[] dc = {0, 0, 0, -1, 1}; // 0 상 하 좌 우
	
	static class Micro{
		int row, col, num, move; // 1(상) 2(하) 3(좌) 4(우)
		boolean dead = false;
		
		Micro(int row, int col, int num, int move){
			this.row = row;
			this.col = col;
			this.num = num;
			this.move = move;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			medicine = new boolean[N][N];
			for (int i=0; i<N; i++) {
				medicine[i][0] = true;
				medicine[i][N-1] = true;
			}
			for (int i=1; i<N-1; i++) {
				medicine[0][i] = true;
				medicine[N-1][i] = true;
			}
			micro = new Micro[K];
			for (int k=0; k<K; k++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				micro[k] = new Micro(Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()));
			}
			
			
			for (int m=0; m<M; m++) {
				for (int k=0; k<K; k++) {
					if (!micro[k].dead) action(micro[k]);
				}
				for (int k=0; k<K-1; k++) {
					// 겹치는 경우
					if ((micro[k].row == micro[k+1].row) && (micro[k].col == micro[k+1].col)) {
						if (micro[k].num > micro[k+1].num) {
							micro[k+1].dead = true;
							micro[k].num += micro[k+1].num;
						}
						else {
							micro[k].dead = true;
							micro[k+1].num += micro[k].num;
						}
					}
				}
			}
			
			int count = 0;
			for (int k=0; k<K; k++) {
				if (!micro[k].dead) count += micro[k].num;
			}
			
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.println(sb);
	}
	
	static void action(Micro curM) {
		curM.row += dr[curM.move];
		curM.col += dc[curM.move];
		
		if (medicine[curM.row][curM.col]) {
			curM.num /= 2;
			if (curM.num == 0) {
				curM.dead = true;
			}
			if (curM.move % 2 == 1) curM.move++;
			else curM.move--;
		}
	}
}
