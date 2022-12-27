package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "languagegame")
public class LanguageGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLanguage", nullable = false)
    private int id;

    @Column(name = "languageName", nullable = false, length = 60)
    private String languageName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageGame that = (LanguageGame) o;
        return id == that.id && Objects.equals(languageName, that.languageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, languageName);
    }

    @ManyToMany
    @JoinTable(name = "languageproduct",
            joinColumns = @JoinColumn(name = "idLanguage"),
            inverseJoinColumns = @JoinColumn(name = "idProduct"))
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}