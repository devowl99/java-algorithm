import java.io.*;
import java.util.*;
 
public class SWEA1486 {
    static int T, N, B;
    static int[] H;
    static int[] arrH;
    static int[] comb;
    static List<Integer> sumList;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
             
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            H = new int[N];
            for (int i=0; i<H.length; i++) {
                H[i] = Integer.parseInt(st2.nextToken());
            }
             
            sumList = new ArrayList<>();
            for (int x: H) {
                if (x >= B) sumList.add(x);
            }
            for (int i=2; i<=N; i++) {
                comb = new int[N];
                combination(0, 0, i);
            }
            int answer = Collections.min(sumList) - B;
             
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
     
    static void combination(int depth, int start, int r) {
        if (depth == r) {
            heightSum();
            return;
        }
        for (int i=start; i<N; i++) {
            comb[depth] = H[i];
            combination(depth+1, i+1, r);
        }
    }
     
    static void heightSum() {
        int sum = 0;
        for (int x: comb) {
            sum += x;
        }       
        if (sum >= B) sumList.add(sum);
    }
}
