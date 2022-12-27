package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "languageproduct")
public class LanguageProduct {
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idLanguage", nullable = false)
    private LanguageGame idLanguage;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idProduct", nullable = false)
    private Product idProduct;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageProduct that = (LanguageProduct) o;
        return idLanguage == that.idLanguage && idProduct == that.idProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLanguage, idProduct);
    }
    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    public LanguageGame getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(LanguageGame idLanguage) {
        this.idLanguage = idLanguage;
    }
}