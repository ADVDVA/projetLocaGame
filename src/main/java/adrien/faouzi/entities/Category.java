package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries(value = {
        @NamedQuery(name= "Category.SelectCategoryByIdProduct",
                query = "select c from Categoryproduct cp " +
                        "join Category c on (cp.idCategory.id = c.id) " +
                        "where (cp.idProduct.id = :idProduct)")
})
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory", nullable = false)
    private int id;

    @Column(name = "categoryName", nullable = false, length = 60)
    private String categoryName;

    @OneToMany(mappedBy = "idCategory")
    private Set<Categoryproduct> categoryproducts = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

    public Set<Categoryproduct> getCategoryproducts() {
        return categoryproducts;
    }

    public void setCategoryproducts(Set<Categoryproduct> categoryproducts) {
        this.categoryproducts = categoryproducts;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}