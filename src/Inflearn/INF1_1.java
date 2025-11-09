//import java.io.*;
//
//public class INF1 {
//    static String str;
//    static String c;
//    static int count;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        str = br.readLine();
//        String lowStr = str.toLowerCase();
//
//        c = br.readLine();
//        char lowC = c.toLowerCase().charAt(0);
//
//        count = 0;
//        for (int i=0; i<str.length(); i++){
//            if (lowStr.charAt(i) == lowC) count++;
//        }
//
//        System.out.println(count);
//    }
//}