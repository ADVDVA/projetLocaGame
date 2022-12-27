package adrien.faouzi.entities;

import adrien.faouzi.enumération.StatusCopy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "City", indexes = {
        @Index(name = "idStore", columnList = "idStore"),
        @Index(name = "idPricePlatform", columnList = "idPricePlatform")
})
public class Copy implements Serializable {
    private int idCopy;
    private int idStore;
    private int idPricePlatform;
    private String copyName;
    private float buyPrice;
    private StatusCopy status;
    private Platform platformByIdPricePlatform;
    private Store storeByIdStore;
    private List<CopyOrder> copyordersByIdCopy;

    @Id
    @Column(name = "idCopy")
    public int getIdCopy() {
        return idCopy;
    }

    public void setIdCopy(int idCopy) {
        this.idCopy = idCopy;
    }

    @Basic
    @Column(name = "idStore")
    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    @Basic
    @Column(name = "idPricePlatform")
    public int getIdPricePlatform() {
        return idPricePlatform;
    }

    public void setIdPricePlatform(int idPricePlatform) {
        this.idPricePlatform = idPricePlatform;
    }

    @Basic
    @Column(name = "copyName")
    public String getCopyName() {
        return copyName;
    }

    public void setCopyName(String copyName) {
        this.copyName = copyName;
    }

    @Basic
    @Column(name = "buyPrice")
    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status.getStatusCopy();
    }

    public void setStatus(StatusCopy status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copy copy = (Copy) o;
        return idCopy == copy.idCopy && idStore == copy.idStore && idPricePlatform == copy.idPricePlatform && Float.compare(copy.buyPrice, buyPrice) == 0 && Objects.equals(copyName, copy.copyName) && Objects.equals(status, copy.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCopy, idStore, idPricePlatform, copyName, buyPrice, status);
    }

    @ManyToOne
    @JoinColumn(name = "idPricePlatform", referencedColumnName = "idPlatform", nullable = false)
    public Platform getPlatformByIdPricePlatform() {
        return platformByIdPricePlatform;
    }

    public void setPlatformByIdPricePlatform(Platform platformByIdPricePlatform) {
        this.platformByIdPricePlatform = platformByIdPricePlatform;
    }

    @ManyToOne
    @JoinColumn(name = "idStore", referencedColumnName = "idStore", nullable = false)
    public Store getStoreByIdStore() {
        return storeByIdStore;
    }

    public void setStoreByIdStore(Store storeByIdStore) {
        this.storeByIdStore = storeByIdStore;
    }

    @OneToMany(mappedBy = "copyByIdCopy")
    public List<CopyOrder> getCopyordersByIdCopy() {
        return copyordersByIdCopy;
    }

    public void setCopyordersByIdCopy(List<CopyOrder> copyordersByIdCopy) {
        this.copyordersByIdCopy = copyordersByIdCopy;
    }
}
