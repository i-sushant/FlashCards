package flashcards;

import java.io.*;
import java.util.*;

public class FastIO {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private List<String> logs = new ArrayList<>();
    public String nextLine() throws IOException {
        String line = br.readLine();
        logs.add(line);
        return line;
    }
    public void print(String s) {
        logs.add(s);
        System.out.println(s);
    }
    public void print() {
        System.out.println();
    }
    public void log(String fileName)throws IOException {
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        for(String l : logs) {
            writer.write(l + "\n");
        }
        writer.close();
    }
}
