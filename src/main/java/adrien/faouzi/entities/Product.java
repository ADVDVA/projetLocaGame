package adrien.faouzi.entities;

import adrien.faouzi.enumeration.MultiPlayer;
import adrien.faouzi.enumeration.Pegi;
import adrien.faouzi.utility.UtilityProcessing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idEditor", nullable = false)
    private Editor idEditor;

    @Column(name = "productName", nullable = false, length = 60)
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "pegi", nullable = false)
    private Pegi pegi;

    @Enumerated(EnumType.STRING)
    @Column(name = "multiPlayer", nullable = false)
    private MultiPlayer multiPlayer;

    @Column(name = "releaseDate", nullable = false)
    private LocalDateTime releaseDate;

    @Column(name = "description")
    private String description;

    @Column(name = "enable", nullable = false)
    private boolean enable = false;

    @OneToMany(mappedBy = "idProduct")
    private Set<Priceplatform> priceplatforms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idProduct")
    private Set<Languageproduct> languageproducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idProduct")
    private Set<Categoryproduct> categoryproducts = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && idEditor == product.idEditor && enable == product.enable && Objects.equals(productName, product.productName) && Objects.equals(pegi, product.pegi) && Objects.equals(multiPlayer, product.multiPlayer) && Objects.equals(releaseDate, product.releaseDate) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idEditor, productName, pegi, multiPlayer, releaseDate, description, enable);
    }

    public Set<Categoryproduct> getCategoryproducts() {
        return categoryproducts;
    }

    public void setCategoryproducts(Set<Categoryproduct> categoryproducts) {
        this.categoryproducts = categoryproducts;
    }

    public Set<Languageproduct> getLanguageproducts() {
        return languageproducts;
    }

    public void setLanguageproducts(Set<Languageproduct> languageproducts) {
        this.languageproducts = languageproducts;
    }

    public Set<Priceplatform> getPriceplatforms() {
        return priceplatforms;
    }

    public void setPriceplatforms(Set<Priceplatform> priceplatforms) {
        this.priceplatforms = priceplatforms;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public String getEnableFormatStr(){
        return (enable? "indisponible": "disponible");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }
    public Date getReleaseDateFormatDate(){
        return UtilityProcessing.castLocalDateTimeToDate(releaseDate);
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMultiPlayer() {
        return multiPlayer.getMultiPlayer();
    }

    public void setMultiPlayer(MultiPlayer multiPlayer) {
        this.multiPlayer = multiPlayer;
    }

    public String getPegiString() {
        return pegi.getPegi();
    }
    public Pegi getPegi() {
        return pegi;
    }
    public int getPegiFormatInt(){
        return pegi.getPegiInt();
    }

    public void setPegi(Pegi pegi) {
        this.pegi = pegi;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Editor getIdEditor() {
        return idEditor;
    }

    public void setIdEditor(Editor idEditor) {
        this.idEditor = idEditor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
