import java.io.*;
import java.util.*;

public class SWEA4008 {
	static int T, N, opArrLen, countOp, minNum, maxNum, result;
	static int[] numCard;
	static int[] opNum;
	static String[] op;
	static String[] opPerm;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			numCard = new int[N];
			opNum = new int[4];
			
			opArrLen = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<4; i++) {
				opNum[i] = Integer.parseInt(st.nextToken());
				opArrLen += opNum[i];
			}
			
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				numCard[i] = Integer.parseInt(st2.nextToken());
			}
			
			op = new String[opArrLen];
			int idx = 0;
			for (int i=0; i<4; i++) {
				if (i==0) {
					for (int j=0; j<opNum[i]; j++) {
						op[idx] = "+";
						idx++;
					}
				}
				else if (i==1) {
					for (int j=0; j<opNum[i]; j++) {
						op[idx] = "-";
						idx++;
					}
				}
				else if (i==2) {
					for (int j=0; j<opNum[i]; j++) {
						op[idx] = "*";
						idx++;
					}
				}
				else if (i==3) {
					for (int j=0; j<opNum[i]; j++) {
						op[idx] = "/";
						idx++;
					}
				}
			}
			
			minNum = 100_000_000;
			maxNum = -100_000_000;
			opPerm = new String[op.length];
			visited = new boolean[op.length];
			permutation(0);
			
			sb.append('#').append(tc).append(' ').append(maxNum - minNum).append('\n');
		}
		
		System.out.print(sb.toString());
	}
	
	static void permutation(int depth) {
		if (depth == op.length) {
			calculate();
			return;
		}
		
		for (int i=0; i<op.length; i++) {
			if (visited[i]) continue;
			opPerm[depth] = op[i];
			visited[i] = true;
			permutation(depth+1);
			visited[i] = false;
		}
	}
	
	static void calculate() {
		result = numCard[0];
		for (int i=0; i<opPerm.length; i++) {
			if (opPerm[i].equals("+")) {
				result += numCard[i+1]; 
			}
			else if (opPerm[i].equals("-")) {
				result -= numCard[i+1];
			}
			else if (opPerm[i].equals("*")) {
				result *= numCard[i+1];
			}
			else if (opPerm[i].equals("/")) {
				result /= numCard[i+1];
			}
		}
		
		if (result < minNum) minNum = result;
		if (result > maxNum) maxNum = result;
	}
}
