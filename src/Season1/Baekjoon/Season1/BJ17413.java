package Season1;

import java.io.*;
import java.util.*;

public class BJ17413 {
	static String S;
	static List<Character> slist;
	static boolean isTag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		S = br.readLine();
		slist = new ArrayList<>();
		isTag = false;
		
		for (int i=0; i<S.length(); i++) {
			char next = S.charAt(i);
			
			if (next == ' ') {
				if (isTag) {
					sb.append(' ');
					continue;
				}
				Collections.reverse(slist);
				for (int j=0; j<slist.size(); j++) {
					sb.append(slist.get(j));
				}
				sb.append(' ');
				slist.clear();
			}
			
			else if (next == '<') {
				isTag = true;
				if (!slist.isEmpty()) {
					Collections.reverse(slist);
					for (int j=0; j<slist.size(); j++) {
						sb.append(slist.get(j));
					}
					sb.append('<');
					slist.clear();
				}
				else sb.append('<');
			}
			
			else if (next == '>') {
				isTag = false;
				slist.clear();
				sb.append('>');
			}
			
			else {
				if (isTag) sb.append(next); // �±� ���ڸ� sb�� �ٷ� ����
				slist.add(next);
			}
		}
		Collections.reverse(slist);
		for (int j=0; j<slist.size(); j++) {
			sb.append(slist.get(j));
		}
		
		System.out.println(sb);
	}
}
