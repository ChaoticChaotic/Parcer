package com.ChaoticChaotic.parcer.entity;

import com.ChaoticChaotic.parcer.langDetector.SupportedLanguages;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value", nullable = false, unique = true)
    private String value;

    @Column(name = "repeats_counter", nullable = false)
    private Integer repeats;

    @Column(name = "url_address")
    private String urlAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private SupportedLanguages language;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getRepeats() {
        return repeats;
    }

    public void setRepeats(Integer repeats) {
        this.repeats = repeats;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public SupportedLanguages getLanguage() {
        return language;
    }

    public void setLanguage(SupportedLanguages language) {
        this.language = language;
    }

    public Word() {
    }

    public Word(String value, Integer repeats, String urlAdress, SupportedLanguages language) {
        this.value = value;
        this.repeats = repeats;
        this.urlAddress = urlAdress;
        this.language = language;
    }

    public Word(Long id, String value, Integer repeats, String urlAdress, SupportedLanguages language) {
        this.id = id;
        this.value = value;
        this.repeats = repeats;
        this.urlAddress = urlAdress;
        this.language = language;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word that = (Word) o;
        return Objects.equals(id, that.id)
                && Objects.equals(value, that.value)
                && Objects.equals(repeats, that.repeats)
                && Objects.equals(urlAddress, that.urlAddress)
                && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, repeats, urlAddress, language);
    }


    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", repeats=" + repeats +
                ", urlAddress='" + urlAddress + '\'' +
                ", language=" + language +
                '}';
    }

}
