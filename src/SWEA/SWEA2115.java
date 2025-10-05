import java.io.*;
import java.util.*;

public class SWEA2115 {
	static int T;
	static int N; // 벌통의 크기
	static int M; // 선택할 수 있는 벌통의 개수
	static int C; // 채취할 수 있는 꿀의 최대 양
	static int[][] hive;
	
	static int maxSub;
	static int maxCost;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			hive = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					hive[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxCost = 0;
			// 무조건 2명이 가로로 M개씩 뽑아야 함.
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (j+M > N) continue;
					
                    int[] arrA = new int[M];
                    for (int k = 0; k < M; k++) arrA[k] = hive[i][j + k];

                    maxSub = 0;
                    makeSubset(arrA, new ArrayList<>(), 0, 0, 0);
                    int subCost1 = maxSub;

                    // 같은 행
                    for (int c = j + M; c <= N - M; c++) {
                        int[] arrB = new int[M];
                        for (int k = 0; k < M; k++) arrB[k] = hive[i][c + k];
                        maxSub = 0;
                        makeSubset(arrB, new ArrayList<>(), 0, 0, 0);
                        int subCost2 = maxSub;
                        maxCost = Math.max(maxCost, subCost1 + subCost2);
                    }

                    // 다른 행
                    for (int r = i + 1; r < N; r++) {
                        for (int c = 0; c <= N - M; c++) {
                            int[] arrB = new int[M];
                            for (int k = 0; k < M; k++) arrB[k] = hive[r][c + k];
                            maxSub = 0;
                            makeSubset(arrB, new ArrayList<>(), 0, 0, 0);
                            int subCost2 = maxSub;
                            maxCost = Math.max(maxCost, subCost1 + subCost2);
                        }
                    }
                }
            }
			sb.append('#').append(tc).append(' ').append(maxCost).append('\n');
		}
		System.out.println(sb);
	}
	
    static void makeSubset(int[] arr, List<Integer> subset, int idx, int sum, int sq) {
        if (sum > C) return;
        if (idx == arr.length) {
            maxSub = Math.max(maxSub, sq);
            return;
        }
        
        // 선택하는 경우
        subset.add(arr[idx]);
        makeSubset(arr, subset, idx + 1, sum + arr[idx], sq + arr[idx] * arr[idx]);
        subset.remove(subset.size() - 1);
        
        // 선택하지 않는 경우
        makeSubset(arr, subset, idx + 1, sum, sq);
    }
}
