import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int charMaxCount = 50;
            char[] characters = new char[charMaxCount];
            int charCount = reader.read(characters);
            for (int i = charCount - 1; i >= 0; i--) {
                System.out.print(characters[i]);
            }
        }
    }
}