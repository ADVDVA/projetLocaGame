package adrien.faouzi.entities;

import adrien.faouzi.enumération.MultiPlayer;
import adrien.faouzi.enumération.Pegi;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Product", indexes = {
        @Index(name = "idEditor", columnList = "idEditor")
})
public class Product {
    private int idProduct;
    private int idEditor;
    private String productName;
    private Pegi pegi;
    private MultiPlayer multiPlayer;
    private LocalDateTime releaseDate;
    private String description;
    private boolean enable;
    private List<CategoryProduct> categoryproductsByIdProduct;
    private List<LanguageProduct> languageproductsByIdProduct;
    private List<PricePlatform> priceplatformsByIdProduct;
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
    public String getPegi() {
        return pegi.getPegi();
    }

    public void setPegi(Pegi pegi) {
        this.pegi = pegi;
    }

    @Basic
    @Column(name = "multiPlayer")
    public String getMultiPlayer() {
        return multiPlayer.getMultiPlayer();
    }

    public void setMultiPlayer(MultiPlayer multiPlayer) {
        this.multiPlayer = multiPlayer;
    }

    @Basic
    @Column(name = "releaseDate")
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
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
    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
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
    public List<CategoryProduct> getCategoryproductsByIdProduct() {
        return categoryproductsByIdProduct;
    }

    public void setCategoryproductsByIdProduct(List<CategoryProduct> categoryproductsByIdProduct) {
        this.categoryproductsByIdProduct = categoryproductsByIdProduct;
    }

    @OneToMany(mappedBy = "productByIdProduct")
    public List<LanguageProduct> getLanguageproductsByIdProduct() {
        return languageproductsByIdProduct;
    }

    public void setLanguageproductsByIdProduct(List<LanguageProduct> languageproductsByIdProduct) {
        this.languageproductsByIdProduct = languageproductsByIdProduct;
    }

    @OneToMany(mappedBy = "productByIdProduct")
    public List<PricePlatform> getPriceplatformsByIdProduct() {
        return priceplatformsByIdProduct;
    }

    public void setPriceplatformsByIdProduct(List<PricePlatform> priceplatformsByIdProduct) {
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
