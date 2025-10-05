import java.io.*;
import java.util.*;

public class SWEA4008 {
    static int T, N, minNum, maxNum;
    static int[] numCard;
    static int[] opNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            numCard = new int[N];
            opNum = new int[4];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                opNum[i] = Integer.parseInt(st.nextToken());
            }

            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numCard[i] = Integer.parseInt(st2.nextToken());
            }

            minNum = 100_000_000;
            maxNum = -100_000_000;

            dfs(1, numCard[0], opNum[0], opNum[1], opNum[2], opNum[3]);

            sb.append('#').append(tc).append(' ').append(maxNum - minNum).append('\n');
        }

        System.out.print(sb.toString());
    }

    static void dfs(int idx, int cur, int plus, int minus, int mul, int div) {
        if (idx == N) { // 모든 숫자 사용 완료
            minNum = Math.min(minNum, cur);
            maxNum = Math.max(maxNum, cur);
            return;
        }

        int next = numCard[idx];

        if (plus > 0)  dfs(idx + 1, cur + next, plus - 1, minus, mul, div);
        if (minus > 0) dfs(idx + 1, cur - next, plus, minus - 1, mul, div);
        if (mul > 0)   dfs(idx + 1, cur * next, plus, minus, mul - 1, div);
        if (div > 0)   dfs(idx + 1, cur / next, plus, minus, mul, div - 1);
    }
}