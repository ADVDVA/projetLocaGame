package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "editor")
public class Editor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEditor", nullable = false)
    private int id;

    @Column(name = "editorName", nullable = false, length = 60)
    private String editorName;

    @OneToMany(mappedBy = "idEditor")
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editor editor = (Editor) o;
        return id == editor.id && Objects.equals(editorName, editor.editorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, editorName);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}