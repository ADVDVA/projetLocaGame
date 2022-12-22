package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Editor {
    private int idEditor;
    private String editorName;
    private List<Product> productsByIdEditor;

    @Id
    @Column(name = "idEditor")
    public int getIdEditor() {
        return idEditor;
    }

    public void setIdEditor(int idEditor) {
        this.idEditor = idEditor;
    }

    @Basic
    @Column(name = "editorName")
    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editor editor = (Editor) o;
        return idEditor == editor.idEditor && Objects.equals(editorName, editor.editorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEditor, editorName);
    }

    @OneToMany(mappedBy = "editorByIdEditor")
    public List<Product> getProductsByIdEditor() {
        return productsByIdEditor;
    }

    public void setProductsByIdEditor(List<Product> productsByIdEditor) {
        this.productsByIdEditor = productsByIdEditor;
    }
}
