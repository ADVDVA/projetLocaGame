package adrien.faouzi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Languageproduct", indexes = {
        @Index(name = "idLanguage", columnList = "idLanguage"),
        @Index(name = "idProduct", columnList = "idProduct")
})
public class LanguageProduct implements Serializable {
    private int idLanguage;
    private int idProduct;
    private LanguageGame languagegameByIdLanguage;
    private Product productByIdProduct;

    @Basic
    @Column(name = "idLanguage")
    public int getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(int idLanguage) {
        this.idLanguage = idLanguage;
    }

    @Basic
    @Column(name = "idProduct")
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

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

    @ManyToOne
    @JoinColumn(name = "idLanguage", referencedColumnName = "idLanguage", nullable = false)
    public LanguageGame getLanguagegameByIdLanguage() {
        return languagegameByIdLanguage;
    }

    public void setLanguagegameByIdLanguage(LanguageGame languagegameByIdLanguage) {
        this.languagegameByIdLanguage = languagegameByIdLanguage;
    }

    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName = "idProduct", nullable = false)
    public Product getProductByIdProduct() {
        return productByIdProduct;
    }

    public void setProductByIdProduct(Product productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }
}
