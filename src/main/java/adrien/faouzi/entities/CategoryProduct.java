package adrien.faouzi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Categoryproduct", indexes = {
        @Index(name = "idCategory", columnList = "idCategory"),
        @Index(name = "idProduct", columnList = "idProduct")
})
public class CategoryProduct implements Serializable {
    private int idCategory;
    private int idProduct;
    private Category categoryByIdCategory;
    private Product productByIdProduct;

    @Basic
    @Column(name = "idCategory")
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
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
        CategoryProduct that = (CategoryProduct) o;
        return idCategory == that.idCategory && idProduct == that.idProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, idProduct);
    }

    @ManyToOne
    @JoinColumn(name = "idCategory", referencedColumnName = "idCategory", nullable = false)
    public Category getCategoryByIdCategory() {
        return categoryByIdCategory;
    }

    public void setCategoryByIdCategory(Category categoryByIdCategory) {
        this.categoryByIdCategory = categoryByIdCategory;
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
