import java.io.*;
import java.util.*;

public class BJ1654 {
    static int K; // 이미 가지고 있는 랜선
    static int N; // 필요한 랜선 수
    static int[] lan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lan = new int[K];
        int ml = 0;
        st = new StringTokenizer(br.readLine());
        for (int k=0; k<K; k++){
            int cur = Integer.parseInt(st.nextToken());
            lan[k] = cur;
            ml = Math.max(ml, cur);
        }

        int left = 1;
        int right = ml;
        int maxLen = 0;
        while (left <= right){
            int mid = (left+right) / 2;

            int count = 0;
            for (int k=0; k<K; k++){
                count += (lan[k] / mid);
            }

            if (count>=N){
                maxLen = mid;
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }

        System.out.println(maxLen);
    }
}
