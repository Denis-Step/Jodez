package com.denis;

import javax.persistence.*;


@Entity
@Table(name = "words", schema = "main", catalog = "")
public class WordsEntity {
    private Long id;
    private Object word;
    private Short wordid;


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @Id
    @Column(name = "word")
    public Object getWord() {
        return word;
    }

    public void setWord(Object word) {
        this.word = word;
    }

    @Basic
    @Column(name = "wordid")
    public Short getWordid() {
        return wordid;
    }

    public void setWordid(Short wordid) {
        this.wordid = wordid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordsEntity that = (WordsEntity) o;

        if (word != null ? !word.equals(that.word) : that.word != null) return false;
        if (wordid != null ? !wordid.equals(that.wordid) : that.wordid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + (wordid != null ? wordid.hashCode() : 0);
        return result;
    }


    public void setId(Long id) {
        this.id = id;
    }

}
