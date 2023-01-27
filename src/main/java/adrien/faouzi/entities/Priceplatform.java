package adrien.faouzi.entities;

import adrien.faouzi.utility.UtilityProcessing;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@NamedQueries(value = {
        @NamedQuery(name= "PricePlatform.SelectPricePlatformByFilterAsc",
                query = "select distinct pp from Priceplatform pp " +
                        "join fetch pp.idProduct p " +
                        "join Categoryproduct cp on (cp.idProduct = p) " +
                        "join fetch cp.idCategory c " +
                        "join Languageproduct lp on (lp.idProduct = p) " +
                        "join fetch lp.idLanguage lg " +
                        "where ( " +
                        "  ((lower(pp.idProduct.productName) like concat('%', :researchWord, '%'))) or " +
                        "  ((lower(c.categoryName)) like concat('%', :researchWord, '%')) or "+ //condition for filter all category.
                        "  (lower(pp.idPlatform.platformName) like concat('%', :researchWord, '%')) or "+ //condition for filter all platform.
                        "  (lower(lg.languageName)) like concat('%', :researchWord, '%') "+ //condition for filter all language.
                        ") "+
                        "order by case " +
                        "  when (:orderBy like 'productname') then pp.idProduct.productName " +
                        "  when (:orderBy like 'pegi') then pp.idProduct.pegi "+
                        "  when (:orderBy like 'rentaleprice') then pp.rentalPrice "+
                        "  when (:orderBy like 'enable') then pp.enable "+
                        "  else pp.id " +
                        "end asc" //+
                        //"* case " +
                        //"  when (:ascOrDesc like 'asc') then 1 " +
                        //"  else -1 " +
                        //"end"
        ),
        @NamedQuery(name= "PricePlatform.SelectPricePlatformByFilterDesc",
                query = "select distinct pp from Priceplatform pp " +
                        "join fetch pp.idProduct p " +
                        "join Categoryproduct cp on (cp.idProduct = p) " +
                        "join fetch cp.idCategory c " +
                        "join Languageproduct lp on (lp.idProduct = p) " +
                        "join fetch lp.idLanguage lg " +
                        "where ( " +
                        "  ((lower(pp.idProduct.productName) like concat('%', :researchWord, '%'))) or " +
                        "  ((lower(c.categoryName)) like concat('%', :researchWord, '%')) or "+ //condition for filter all category.
                        "  (lower(pp.idPlatform.platformName) like concat('%', :researchWord, '%')) or "+ //condition for filter all platform.
                        "  (lower(lg.languageName)) like concat('%', :researchWord, '%') "+ //condition for filter all language.
                        ") "+
                        "order by case " +
                        "  when (:orderBy like 'productname') then pp.idProduct.productName " +
                        "  when (:orderBy like 'pegi') then pp.idProduct.pegi "+
                        "  when (:orderBy like 'rentaleprice') then pp.rentalPrice "+
                        "  when (:orderBy like 'enable') then pp.enable "+
                        "  else pp.id " +
                        "end desc" //+
                        //"* case " +
                        //"  when (:ascOrDesc like 'asc') then 1 " +
                        //"  else -1 " +
                        //"end"
        ),
        @NamedQuery(name= "PricePlatform.SelectPricePlatformById",
                query = "select pp from Priceplatform pp where (:idPricePlatform = pp.id)"
        ),
        @NamedQuery(name= "PricePlatform.SelectJoinCopy",
                query = "select c from Copy c where (c.idPricePlatform.id = :idPricePlatform)"
        ),
        @NamedQuery(name= "PricePlatform.SelectPricePlatformAll", query = "select p from Priceplatform p")


})
@Entity
@Table(name = "priceplatform")
public class Priceplatform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPricePlatform", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idProduct", nullable = false)
    private Product idProduct;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPlatform", nullable = false)
    private Platform idPlatform;

    @Column(name = "rentalPrice", nullable = false)
    private float rentalPrice;

    @Column(name = "availableStock", nullable = false)
    private int availableStock;

    @Column(name = "latePrice", nullable = false)
    private float latePrice;

    @Column(name = "picture")
    private String picture;

    @Column(name = "enable", nullable = false)
    private boolean enable = false;

    @OneToMany(mappedBy = "idPricePlatform")
    private Set<Copy> copies = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Priceplatform that = (Priceplatform) o;
        return id == that.id;// && idProduct == that.idProduct && idPlatform == that.idPlatform && Float.compare(that.rentalPrice, rentalPrice) == 0 && availableStock == that.availableStock && Float.compare(that.latePrice, latePrice) == 0 && enable == that.enable && Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idProduct, idPlatform, rentalPrice, availableStock, latePrice, picture, enable);
    }

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public float getLatePrice() {
        return latePrice;
    }

    public void setLatePrice(float latePrice) {
        this.latePrice = latePrice;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public float getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public Platform getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(Platform idPlatform) {
        this.idPlatform = idPlatform;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getRentalPriceFormatStr(){
        return UtilityProcessing.floatToStrTwoDigit(this.rentalPrice);
    }

    public String getEnableFormatStr(){
        return ((this.enable && this.idProduct.getEnable())? "disponible": "indisponible");
    }
    public String getEnableFormatIco(){
        return ((this.enable && this.idProduct.getEnable())? "pi pi-check-circle colorGreen": "pi pi-times-circle colorRed");
    }
    public boolean getEnableForDisabledButton(){
        return !(this.enable && this.idProduct.getEnable());
    }
    public String getEnableClassColor(){
        return ((this.enable && this.idProduct.getEnable())? "colorGreen": "colorRed");
    }



}