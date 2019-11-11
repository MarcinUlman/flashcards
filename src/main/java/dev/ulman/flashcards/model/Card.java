package dev.ulman.flashcards.model;

import javax.persistence.*;

@Entity
@Table (name = "Cards")
public class Card {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "CardId")
    private int id;
    private String word;
    private String translation;
    private String description;
    private String imageURL;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "GroupId")
    private Group group;

    public Card(String word, String translation, String description, String imageURL, Group group) {
        this.word = word;
        this.translation = translation;
        this.description = description;
        this.imageURL = imageURL;
        this.group = group;
    }

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
