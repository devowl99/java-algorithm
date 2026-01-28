package Season2;

import java.io.*;
import java.util.*;

public class BJ1110 {
    static int N;
    static String s;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        s = String.valueOf(N);

        while (true){
            count++;

            if (s.length()<2){
                s = "0" + s;
            }

            char fir = s.charAt(0);
            char sec = s.charAt(1);

            String newS = ""+fir+sec;

            if (s.equals(newS)){
                break;
            }
        }

        System.out.println(count);
    }
}


// 미완성
