package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Category")
public class Category {
    private int idCategory;
    private String categoryName;
    private List<CategoryProduct> categoryproductsByIdCategory;

    @Id
    @Column(name = "idCategory")
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "categoryName")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return idCategory == category.idCategory && Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, categoryName);
    }

    @OneToMany(mappedBy = "categoryByIdCategory")
    public List<CategoryProduct> getCategoryproductsByIdCategory() {
        return categoryproductsByIdCategory;
    }

    public void setCategoryproductsByIdCategory(List<CategoryProduct> categoryproductsByIdCategory) {
        this.categoryproductsByIdCategory = categoryproductsByIdCategory;
    }
}
