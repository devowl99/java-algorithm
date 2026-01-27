package Season1;

import java.io.*;
import java.util.*;

public class SWEA1251 {
	static int T;
	static int N; // 섬의 개수 (정점 수)
	static double E; // 해저 터널 건설의 환경 부담 세율 실수
	static List<Edge> edgeList;
	static int[] X;
	static int[] Y;
	static int[] p;
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		double weight;
		
		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight); // 가중치 기준 오름차순 정렬 되도록 비교 결과 리턴
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			X = new int[N];
			Y = new int[N];
			p = new int[N];
			make();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				Y[i] = Integer.parseInt(st2.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			
			edgeList = new ArrayList<>();
			// 간선이 만들어질 수 있는 모든 경우의 수 생성
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    double w = calWeight(X[i], X[j], Y[i], Y[j]) * E;
                    edgeList.add(new Edge(i, j, w));
                }
            }
			
			Collections.sort(edgeList);
			
			int count = 0;
			double totalWeight = 0;
			for (Edge x: edgeList) {
				if (union(x.from, x.to)) {
					count++;
					totalWeight += x.weight;
				}
				if (count == N-1) break; // 간선 수가 (정점-1)개일 때 종료
			}
			
			sb.append("#").append(tc).append(" ").append(Math.round(totalWeight)).append("\n");
		}
		System.out.println(sb);
	}
	
	// 오버플로우 발생
	static int calWeight(int x1, int x2, int y1, int y2) {
		return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
	}
	
//	static long calWeight(int x1, int x2, int y1, int y2) {
//	    long dx = (long)x1-x2;
//	    long dy = (long)y1-y2;
//	    return dx*dx + dy*dy;
//	}
	
	static void make() {
		for (int i=0; i<N; i++) p[i] = i;
	}
	
	static int find(int x) {
		if (x == p[x]) return x;
		return p[x] = find(p[x]); // 경로 압축
	}
	
	static boolean union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX == rootY) return false;
		
		// 트리가 한쪽으로 치우치는 것을 방지하기 위한 조건문(랭크 관리는 아니다!)
		if (rootX>rootY) p[rootY] = rootX;
		else p[rootX] = rootY;
		
		return true;
	}
}