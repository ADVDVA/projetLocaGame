package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoryproduct")
public class CategoryProduct {
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idCategory", nullable = false)
    private Category idCategory;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idProduct", nullable = false)
    private Product idProduct;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryProduct that = (CategoryProduct) o;
        return idCategory == that.idCategory && idProduct == that.idProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, idProduct);
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }
}