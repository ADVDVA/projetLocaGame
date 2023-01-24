package adrien.faouzi.entities;

import adrien.faouzi.enumeration.StatusCopy;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries(value = {
        @NamedQuery(name= "Copy.SelectCopyByIdCopy", query = "select c from Copy c where (c.id = :idCopy)")
})
@Entity
@Table(name = "copy", indexes = {
        @Index(name = "copyName", columnList = "copyName", unique = true)
})
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCopy", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idStore", nullable = false)
    private Store idStore;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPricePlatform", nullable = false)
    private Priceplatform idPricePlatform;

    //!!! auto generate !!!.
    @Column(name = "copyName", nullable = false, length = 60)
    private String copyName;

    @Column(name = "buyPrice", nullable = false)
    private float buyPrice;

    @Enumerated
    @Column(name = "status", nullable = false)
    private StatusCopy status;

    //---->>

    @OneToMany(mappedBy = "idCopy")
    private Set<Copyorder> copyorders = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copy copy = (Copy) o;
        return id == copy.id && idStore == copy.idStore && idPricePlatform == copy.idPricePlatform && Float.compare(copy.buyPrice, buyPrice) == 0 && Objects.equals(copyName, copy.copyName) && Objects.equals(status, copy.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idStore, idPricePlatform, copyName, buyPrice, status);
    }

    public Set<Copyorder> getCopyorders() {
        return copyorders;
    }

    public void setCopyorders(Set<Copyorder> copyorders) {
        this.copyorders = copyorders;
    }

    public String getStatus() {
        return status.getStatusCopy();
    }

    public void setStatus(StatusCopy status) {
        this.status = status;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getCopyName() {
        return copyName;
    }

    public void setCopyName(String copyName) {
        this.copyName = copyName;
    }

    public Priceplatform getIdPricePlatform() {
        return idPricePlatform;
    }

    public void setIdPricePlatform(Priceplatform idPricePlatform) {
        this.idPricePlatform = idPricePlatform;
    }

    public Store getIdStore() {
        return idStore;
    }

    public void setIdStore(Store idStore) {
        this.idStore = idStore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}