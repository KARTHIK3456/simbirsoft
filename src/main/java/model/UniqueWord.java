package model;

import javax.persistence.*;

/**
 * This is an entity that is intended to be saved to the database
 */
@Entity
@Table(name = "unique_words")
public class UniqueWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unique_word_id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "word")
    private String word;

    @Column(name = "count")
    private int count;

    public UniqueWord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return word.concat(" - ").concat(String.valueOf(count));
    }
}
