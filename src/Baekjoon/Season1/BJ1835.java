package Season1;

import java.io.*;
import java.util.*;

public class BJ1835 {
    static int N;
    static Deque<Integer> cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine()); // 카드의 수 겸 N번 반복

        cards = new ArrayDeque<>();
        cards.add(N);
        for (int n=N-1; n>0; n--){
            cards.addFirst(n);
            for (int i=0; i<n; i++){
                // 뒤에 있는걸 앞으로 옮긴다.
                cards.addFirst(cards.pollLast());
            }
        }

        for (int x: cards){
            sb.append(x).append(' ');
        }

        System.out.println(sb);
    }
}
