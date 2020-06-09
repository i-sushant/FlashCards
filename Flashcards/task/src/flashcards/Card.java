package flashcards;

public class Card {
    public String definition;
    public String term;
    public int mistakes;

    public Card(String term, String definition, int mistakes) {
        this.definition = definition;
        this.term = term;
        this.mistakes = mistakes;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
        this.mistakes = 0;
    }
    public void makeMistake() {
        this.mistakes++;
    }
    public void eraseMistake() {
        this.mistakes = 0;
    }
}
