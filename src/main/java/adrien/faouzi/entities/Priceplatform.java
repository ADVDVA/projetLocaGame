package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Priceplatform {
    private int idPricePlatform;
    private int idProduct;
    private int idPlatform;
    private double rentalPrice;
    private int availableStock;
    private double latePrice;
    private String picture;
    private byte enable;
    private Platform platformByIdPlatform;
    private Product productByIdProduct;

    @Id
    @Column(name = "idPricePlatform")
    public int getIdPricePlatform() {
        return idPricePlatform;
    }

    public void setIdPricePlatform(int idPricePlatform) {
        this.idPricePlatform = idPricePlatform;
    }

    @Basic
    @Column(name = "idProduct")
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Basic
    @Column(name = "idPlatform")
    public int getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(int idPlatform) {
        this.idPlatform = idPlatform;
    }

    @Basic
    @Column(name = "rentalPrice")
    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    @Basic
    @Column(name = "availableStock")
    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    @Basic
    @Column(name = "latePrice")
    public double getLatePrice() {
        return latePrice;
    }

    public void setLatePrice(double latePrice) {
        this.latePrice = latePrice;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
        Priceplatform that = (Priceplatform) o;
        return idPricePlatform == that.idPricePlatform && idProduct == that.idProduct && idPlatform == that.idPlatform && Double.compare(that.rentalPrice, rentalPrice) == 0 && availableStock == that.availableStock && Double.compare(that.latePrice, latePrice) == 0 && enable == that.enable && Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPricePlatform, idProduct, idPlatform, rentalPrice, availableStock, latePrice, picture, enable);
    }

    @ManyToOne
    @JoinColumn(name = "idPlatform", referencedColumnName = "idPlatform", nullable = false)
    public Platform getPlatformByIdPlatform() {
        return platformByIdPlatform;
    }

    public void setPlatformByIdPlatform(Platform platformByIdPlatform) {
        this.platformByIdPlatform = platformByIdPlatform;
    }

    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName = "idProduct", nullable = false)
    public Product getProductByIdProduct() {
        return productByIdProduct;
    }

    public void setProductByIdProduct(Product productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }
}
