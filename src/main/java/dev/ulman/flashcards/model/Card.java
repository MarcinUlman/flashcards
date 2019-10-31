package dev.ulman.flashcards.model;

public class Card {

    private int id;
    private String word;
    private String translation;
    private String descripion;
    private String imaneURL;

    private Group group;

    public Card(String word, String translation, String descripion, String imaneURL, Group group) {
        this.word = word;
        this.translation = translation;
        this.descripion = descripion;
        this.imaneURL = imaneURL;
        this.group = group;
    }

    public Card() {
    }


}
