import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FilesOps {
    public static void main(String[] args) {
        String pathToFile = "F:\\Flashcards\\data.txt";
        String pathToWrite = "F:\\Flashcards\\even.txt";
        try {
            String[] a = readFileAsString(pathToFile).split("\\s+");
            int[] arr = new int[a.length];
            int i = 0;
            int count = 0;
            for(String s : a){
                arr[i] = Integer.parseInt(s);
                i++;
            }
            for(int x : arr){
                if(x == 0) {
                    break;
                } else if(x % 2 == 0){
                    count++;
                }
            }
            File file;
            FileWriter writer = new FileWriter(pathToWrite);
            StringBuilder sb = new StringBuilder();
            Arrays.stream(arr).forEach(j -> {
                if(j % 2 == 0 && j != 0){
                    sb.append(j + "\n");
                }
            });
            writer.write(sb.toString());
            System.out.println(count);
        } catch(IOException e){
            e.printStackTrace();
        }

    }
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
