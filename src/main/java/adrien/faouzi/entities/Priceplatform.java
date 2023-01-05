package adrien.faouzi.entities;

import adrien.faouzi.utility.UtilityProcessing;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@NamedQueries(value = {
        @NamedQuery(name= "Priceplatform.SelectPricePlatformByFilter",
                query = "select pp from Priceplatform pp " +
                        //"join Product p on (pp.idPorduct.id = p.id) " +
                        "where ( " +
                        "  (lower(pp.idProduct.productName) like concat('%', lower(:researchWord), '%')) or " +
                        "  (1 like 1) or "+ //condition for filter all category.
                        "  (1 like 1) or "+ //condition for filter all platform.
                        "  (1 like 1) "+    //condition for filter all language.
                        ") " //+
                        //"order by case " +
                        //"  when (:orderBy like 'productname') then p.productName " +
                        //"  else pp.id " +
                        //"end " +
                        //"* case when (:ascOrDesc = 'asc') then 1 else -1 end"
        )
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
        return id == that.id && idProduct == that.idProduct && idPlatform == that.idPlatform && Float.compare(that.rentalPrice, rentalPrice) == 0 && availableStock == that.availableStock && Float.compare(that.latePrice, latePrice) == 0 && enable == that.enable && Objects.equals(picture, that.picture);
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

    public String getEnableFormatStrCatalog(){
        return ((this.enable && this.idProduct.getEnable())? "disponible": "indisponible");
    }

}