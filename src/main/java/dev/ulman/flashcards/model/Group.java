package dev.ulman.flashcards.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private int id;
    private String name;
    private List cards;

    public Group(String name) {
        this.name = name;
        this.cards = new ArrayList();
    }

    public Group() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getCards() {
        return cards;
    }

    public void setCards(List cards) {
        this.cards = cards;
    }
}
