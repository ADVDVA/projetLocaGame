package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Languageproduct {
    private int idLanguage;
    private int idProduct;
    private Languagegame languagegameByIdLanguage;
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
        Languageproduct that = (Languageproduct) o;
        return idLanguage == that.idLanguage && idProduct == that.idProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLanguage, idProduct);
    }

    @ManyToOne
    @JoinColumn(name = "idLanguage", referencedColumnName = "idLanguage", nullable = false)
    public Languagegame getLanguagegameByIdLanguage() {
        return languagegameByIdLanguage;
    }

    public void setLanguagegameByIdLanguage(Languagegame languagegameByIdLanguage) {
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
