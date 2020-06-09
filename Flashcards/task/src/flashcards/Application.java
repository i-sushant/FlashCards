package flashcards;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Application {
    private static final String ADD_CARD = "add";
    private static final String REMOVE_CARD = "remove";
    private static final String IMPORT_CARD = "import";
    private static final String EXPORT_CARD = "export";
    private static final String ASK = "ask";
    private static final String EXIT = "exit";
    private static final String HARDEST_CARD = "hardest card";
    private static final String LOG = "log";
    private static final String RESET_STATS = "reset stats";

    FastIO fio = new FastIO();
    Set<Card> cards = new LinkedHashSet<>();

    public void start()throws IOException {
        while(true) {
            fio.print("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            String choice = fio.nextLine();
            switch (choice) {
                case ADD_CARD:
                    addCard();
                    break;
                case REMOVE_CARD:
                    removeCard();
                    break;
                case IMPORT_CARD:
                    loadCard();
                    break;
                case EXPORT_CARD:
                    saveCard();
                    break;
                case ASK:
                    askDefinition();
                    break;
                case HARDEST_CARD:
                    hardestCard();
                    break;
                case LOG:
                    setLog();
                    break;
                case RESET_STATS :
                    reset();
                    break;
                case EXIT:
                    fio.print("Bye Bye!");
                    System.exit(0);
                default:
                    break;
            }
            fio.print();
        }
    }
    public void addCard()throws IOException {
        fio.print("The card:");
        String cardName =  fio.nextLine().trim();
        if(cards.stream().anyMatch(card -> card.term.equals(cardName))) {
            fio.print("The card \"" + cardName + "\" already exists. ");
            return;
        }
        fio.print("The definition of the card");
        String cardDefinition = fio.nextLine().trim();
        if(cards.stream().anyMatch(card -> card.definition.equals(cardDefinition))) {
            fio.print("The definition \"" + cardDefinition + "\" already exists.");
            return;
        }
        cards.add(new Card(cardName,cardDefinition));
        fio.print("The pair (\""+cardName+"\":\""+cardDefinition+"\") has been added.");
    }
    public void removeCard()throws IOException {
        fio.print("The card");
        String cardName = fio.nextLine();
        cards.stream()
                .filter(card -> card.term.equals(cardName))
                .findFirst()
                .ifPresentOrElse(this::remove, () -> fio.print("Can't remove \"" + cardName + "\": there is no such card."));
    }
    private void remove(Card card) {
        cards.remove(card);
        fio.print("The card has been removed.");
    }
    public void loadCard() throws IOException {
        fio.print("File name:");
        String fileName = fio.nextLine();
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            int count = 0;
            while (sc.hasNextLine()) {
                String[] tokens = sc.nextLine().split(",");
                String term = tokens[0].trim();
                String definition = tokens[1].trim();
                int mistakes = Integer.parseInt(tokens[2].trim());
                if(cards.stream().anyMatch(card -> card.term.equals(term))) {
                    Card oldCard = cards.stream().filter(card -> card.term.equals(term)).findFirst().orElse(null);
                    if (oldCard == null) throw new NullPointerException();
                    oldCard.setTerm(term);
                    oldCard.setDefinition(definition);
                    oldCard.setMistakes(mistakes);
                } else {
                    cards.add(new Card(term, definition, mistakes));
                }
                count++;
            }
            fio.print(count + " cards have been loaded.");
            sc.close();
        } catch(Exception e) {
            fio.print("File not found.");
        }

    }
    public void saveCard()throws IOException {
        fio.print("File name:");
        String fileName = fio.nextLine();
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        int count = cards.size();
        StringBuilder sb = new StringBuilder();
        cards.forEach(card -> sb.append(card.term).append(",").append(card.definition).append(",").append(card.mistakes).append("\n"));
        writer.write(sb.toString().trim());
        writer.close();
        fio.print(count + " cards have been saved.");
    }
    public void askDefinition() throws IOException {
        fio.print("How many times to ask?");
        int times = Integer.parseInt(fio.nextLine());
        Random random = new Random();
        ArrayList<Card> keys = new ArrayList<>(cards);
        int limit = keys.size();
        for(int i = 0; i < times; i++) {
            Card randomCard = keys.get(random.nextInt(limit));
            fio.print("Print the definition of \"" + randomCard.term +"\":");
            String query = fio.nextLine();
            String correctAnswer = randomCard.definition;
            if(query.equals(correctAnswer)) {
                fio.print("Correct answer.");
            } else {
                randomCard.makeMistake();
                Card correctCard = cards.stream().filter(c -> c.definition.equals(query)).findFirst().orElse(null);
                if(correctCard != null) {
                    fio.print("Wrong answer. The correct one is \"" + correctAnswer + "\", you've just written the definition of \"" + correctCard.term + "\".");
                } else {
                    fio.print("Wrong answer. The correct one is \"" + correctAnswer + "\".");
                }
            }
        }
    }
    public void hardestCard() {
        cards.stream()
                .mapToInt(Card::getMistakes)
                .filter(mistakes -> mistakes > 0)
                .max()
                .ifPresentOrElse(this::printHardest, () -> fio.print("There are no cards with errors."));
    }
    private void printHardest(int maxMistakes) {
        String is = cards.stream()
                .filter(card -> card.getMistakes() == maxMistakes)
                .count() == 1 ? " is " : "s are ";
        String act = cards.stream()
                .filter(card -> card.getMistakes() == maxMistakes)
                .map(card -> String.format("\"%s\"", card.term))
                .collect(Collectors.joining(", "));
        String error = "error" + (maxMistakes > 1 ? "s" : "");
        fio.print("The hardest card" + is + act + ". You have "+ maxMistakes +" " + error + " answering it.");
    }
    public void setLog()throws IOException {
        fio.print("File name:");
        String file = fio.nextLine().trim();
        fio.log(file);
        fio.print("The log has been saved.");
    }
    public void reset() {
        cards.forEach(Card::eraseMistake);
        fio.print("Card statistics has been reset.");
    }
}
