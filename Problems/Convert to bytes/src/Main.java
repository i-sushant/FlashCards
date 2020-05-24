import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        int c = br.read();
        while(c != -1){
            System.out.print(c);
            c = br.read();
        }
    }
}