package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

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
}