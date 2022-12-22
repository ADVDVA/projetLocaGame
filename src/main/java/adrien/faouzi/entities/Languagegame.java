package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Languagegame {
    private int idLanguage;
    private String languageName;
    private List<Languageproduct> languageproductsByIdLanguage;

    @Id
    @Column(name = "idLanguage")
    public int getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(int idLanguage) {
        this.idLanguage = idLanguage;
    }

    @Basic
    @Column(name = "languageName")
    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Languagegame that = (Languagegame) o;
        return idLanguage == that.idLanguage && Objects.equals(languageName, that.languageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLanguage, languageName);
    }

    @OneToMany(mappedBy = "languagegameByIdLanguage")
    public List<Languageproduct> getLanguageproductsByIdLanguage() {
        return languageproductsByIdLanguage;
    }

    public void setLanguageproductsByIdLanguage(List<Languageproduct> languageproductsByIdLanguage) {
        this.languageproductsByIdLanguage = languageproductsByIdLanguage;
    }
}
