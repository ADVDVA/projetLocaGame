package adrien.faouzi.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {
    private int idProduct;
    private int idEditor;
    private String productName;
    private Object pegi;
    private Object multiPlayer;
    private Timestamp releaseDate;
    private String description;
    private byte enable;
    private List<Categoryproduct> categoryproductsByIdProduct;
    private List<Languageproduct> languageproductsByIdProduct;
    private List<Priceplatform> priceplatformsByIdProduct;
    private Editor editorByIdEditor;

    @Id
    @Column(name = "idProduct")
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Basic
    @Column(name = "idEditor")
    public int getIdEditor() {
        return idEditor;
    }

    public void setIdEditor(int idEditor) {
        this.idEditor = idEditor;
    }

    @Basic
    @Column(name = "productName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "pegi")
    public Object getPegi() {
        return pegi;
    }

    public void setPegi(Object pegi) {
        this.pegi = pegi;
    }

    @Basic
    @Column(name = "multiPlayer")
    public Object getMultiPlayer() {
        return multiPlayer;
    }

    public void setMultiPlayer(Object multiPlayer) {
        this.multiPlayer = multiPlayer;
    }

    @Basic
    @Column(name = "releaseDate")
    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "enable")
    public byte getEnable() {
        return enable;
    }

    public void setEnable(byte enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return idProduct == product.idProduct && idEditor == product.idEditor && enable == product.enable && Objects.equals(productName, product.productName) && Objects.equals(pegi, product.pegi) && Objects.equals(multiPlayer, product.multiPlayer) && Objects.equals(releaseDate, product.releaseDate) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, idEditor, productName, pegi, multiPlayer, releaseDate, description, enable);
    }

    @OneToMany(mappedBy = "productByIdProduct")
    public List<Categoryproduct> getCategoryproductsByIdProduct() {
        return categoryproductsByIdProduct;
    }

    public void setCategoryproductsByIdProduct(List<Categoryproduct> categoryproductsByIdProduct) {
        this.categoryproductsByIdProduct = categoryproductsByIdProduct;
    }

    @OneToMany(mappedBy = "productByIdProduct")
    public List<Languageproduct> getLanguageproductsByIdProduct() {
        return languageproductsByIdProduct;
    }

    public void setLanguageproductsByIdProduct(List<Languageproduct> languageproductsByIdProduct) {
        this.languageproductsByIdProduct = languageproductsByIdProduct;
    }

    @OneToMany(mappedBy = "productByIdProduct")
    public List<Priceplatform> getPriceplatformsByIdProduct() {
        return priceplatformsByIdProduct;
    }

    public void setPriceplatformsByIdProduct(List<Priceplatform> priceplatformsByIdProduct) {
        this.priceplatformsByIdProduct = priceplatformsByIdProduct;
    }

    @ManyToOne
    @JoinColumn(name = "idEditor", referencedColumnName = "idEditor", nullable = false)
    public Editor getEditorByIdEditor() {
        return editorByIdEditor;
    }

    public void setEditorByIdEditor(Editor editorByIdEditor) {
        this.editorByIdEditor = editorByIdEditor;
    }
}
