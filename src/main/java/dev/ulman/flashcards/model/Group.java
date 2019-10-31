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
}
