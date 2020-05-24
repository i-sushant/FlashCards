import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String cipher  = br.readLine();
        String i1 = br.readLine();
        String i2 = br.readLine();
        Map<Character,Character> map = new HashMap<>();
        Map<Character,Character> cmap = new HashMap<>();
        for(int i = 0;i<text.length();i++){
            map.put(text.charAt(i),cipher.charAt(i));
            cmap.put(cipher.charAt(i),text.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<i1.length();i++){
            sb.append(map.get(i1.charAt(i)));
        }
        System.out.println(sb.toString());
        StringBuilder rb = new StringBuilder();
        for(int i = 0; i<i2.length();i++){
            rb.append(cmap.get(i2.charAt(i)));
        }
        System.out.println(rb.toString());
    }
}