package adrien.faouzi.entities;

import adrien.faouzi.enumération.MultiPlayer;
import adrien.faouzi.enumération.Pegi;

import javax.persistence.*;
import java.time.LocalDateTime ;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private LocalDateTime  releaseDate;

    @Column(name = "description")
    private String description;

    @Column(name = "enable", nullable = false)
    private boolean enable = false;

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

    @OneToMany(mappedBy = "idProduct")
    private List<PricePlatform> pricePlatforms = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "languageproduct",
            joinColumns = @JoinColumn(name = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "idLanguage"))
    private List<LanguageGame> languageGames = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "categoryproduct",
            joinColumns = @JoinColumn(name = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "idCategory"))
    private List<Category> categories = new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }


    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<LanguageGame> getLanguageGames() {
        return languageGames;
    }

    public void setLanguageGames(List<LanguageGame> languageGames) {
        this.languageGames = languageGames;
    }

    public List<PricePlatform> getPricePlatforms() {
        return pricePlatforms;
    }

    public void setPricePlatforms(List<PricePlatform> pricePlatforms) {
        this.pricePlatforms = pricePlatforms;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime  getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime  releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMultiPlayer() {
        return multiPlayer.getMultiPlayer();
    }

    public void setMultiPlayer(MultiPlayer multiPlayer) {
        this.multiPlayer = multiPlayer;
    }

    public String getPegi() {
        return pegi.getPegi();
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