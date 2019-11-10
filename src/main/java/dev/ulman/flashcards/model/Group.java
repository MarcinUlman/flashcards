package dev.ulman.flashcards.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Groups")
public class Group {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "GroupId")
    private int id;
    private String name;

    @OneToMany (mappedBy = "group", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Card> cards;

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
