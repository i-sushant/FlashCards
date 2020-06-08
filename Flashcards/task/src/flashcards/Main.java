package flashcards;
import java.io.*;
import java.util.*;

public class Main {
    static Map<String, String> map = new LinkedHashMap<>();
    static Map<String, String> map2 = new LinkedHashMap<>();
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            while(true) {
                System.out.println("Input the action (add, remove, import, export, ask, exit):");
                String choice = br.readLine();
                switch (choice) {
                case "add":
                    addCard(br);
                    System.out.println();
                    break;
                case "remove":
                    removeCard(br);
                    System.out.println();
                    break;
                case "import":
                    loadCard(br);
                    System.out.println();
                    break;
                case "export":
                    saveCard(br);
                    System.out.println();
                    break;
                case "ask":
                    askDefinition(br);
                    System.out.println();
                    break;
                case "exit":
                    System.out.println("Bye Bye!");
                    System.exit(0);
                default:
                    break;
            }
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void addCard(BufferedReader br)throws IOException {
            System.out.println("The card:");
            String cardName =  br.readLine().trim();
            if(map.containsKey(cardName)) {
                System.out.println("The card \"" + cardName + "\" already exists. ");
                return;
            }
            System.out.println("The definition of the card");
            String cardDefinition = br.readLine().trim();
            if(map2.containsKey(cardDefinition)) {
                System.out.println("The definition \"" + cardDefinition + "\" already exists.");
                return;
            }
            map.put(cardName,cardDefinition);
            map2.put(cardDefinition,cardName);
            System.out.println("The pair (\""+cardName+"\":\""+map.get(cardName)+"\") has been added.");
    }
    public static void removeCard(BufferedReader br)throws IOException {
        System.out.println("The card");
        String card = br.readLine();
        if(map.containsKey(card)){
            map2.remove(map.get(card));
            map.remove(card);
            System.out.println("The card has been removed.");
        } else {
            System.out.println("Can't remove \"" + card + "\": there is no such card.");
        }
    }
    public static void loadCard(BufferedReader br)throws IOException {
        System.out.println("File name:");
        String fileName = br.readLine();
        File file = new File(fileName);
        if(!file.isFile()) {
            System.out.println("File not found.");
            return;
        }
        Scanner sc = new Scanner(file);
        int count = 0;
        while (sc.hasNextLine()) {
            String[] tokens = sc.nextLine().split(",");
            String key = tokens[0].trim();
            String value = tokens[1].trim();
            if(map.containsKey(key)) {
                String oldDefinition = map.get(key);
                map2.remove(oldDefinition);
            }
            map.put(key,value);
            map2.put(value,key);
            count++;
        }
        System.out.println(count + " cards have been loaded.");

    }
    public static void saveCard(BufferedReader br)throws IOException {
        System.out.println("File name:");
        String fileName = br.readLine();
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        int count = map.size();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append(",").append(value).append("\n");
        }
        writer.write(sb.toString().trim());
        writer.close();
        System.out.println(count + " cards have been saved.");
    }
    public static void askDefinition(BufferedReader br) throws IOException {
        System.out.println("How many times to ask?");
        int times = Integer.parseInt(br.readLine());
        Random random = new Random();
        ArrayList<String> keys = new ArrayList<>(map.keySet());
        int limit = keys.size();
        for(int i = 0; i < times; i++) {
            String card = keys.get(random.nextInt(limit));
            System.out.println("Print the definition of \"" + card +"\":");
            String query = br.readLine();
            String correctAnswer = map.get(card);
            if(query.equals(correctAnswer)) {
                System.out.println("Correct answer.");
            } else {
                if(map2.containsKey(query)) {
                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\", you've just written the definition of \"" + map2.get(query) + "\".");
                } else {
                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\".");
                }
            }
        }
    }
}
