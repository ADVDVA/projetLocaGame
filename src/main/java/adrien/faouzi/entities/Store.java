package adrien.faouzi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Store", indexes = {
        @Index(name = "idCity", columnList = "idCity")
})
public class Store implements Serializable {
    private int idStore;
    private int idCity;
    private String storeName;
    private String streetStore;
    private int numberStore;
    private String boxStore;
    private String phone;
    private List<Copy> copiesByIdStore;
    private City cityByIdCity;

    @Id
    @Column(name = "idStore")
    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    @Basic
    @Column(name = "idCity")
    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    @Basic
    @Column(name = "storeName")
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Basic
    @Column(name = "streetStore")
    public String getStreetStore() {
        return streetStore;
    }

    public void setStreetStore(String streetStore) {
        this.streetStore = streetStore;
    }

    @Basic
    @Column(name = "numberStore")
    public int getNumberStore() {
        return numberStore;
    }

    public void setNumberStore(int numberStore) {
        this.numberStore = numberStore;
    }

    @Basic
    @Column(name = "boxStore")
    public String getBoxStore() {
        return boxStore;
    }

    public void setBoxStore(String boxStore) {
        this.boxStore = boxStore;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return idStore == store.idStore && idCity == store.idCity && numberStore == store.numberStore && Objects.equals(storeName, store.storeName) && Objects.equals(streetStore, store.streetStore) && Objects.equals(boxStore, store.boxStore) && Objects.equals(phone, store.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStore, idCity, storeName, streetStore, numberStore, boxStore, phone);
    }

    @OneToMany(mappedBy = "storeByIdStore")
    public List<Copy> getCopiesByIdStore() {
        return copiesByIdStore;
    }

    public void setCopiesByIdStore(List<Copy> copiesByIdStore) {
        this.copiesByIdStore = copiesByIdStore;
    }

    @ManyToOne
    @JoinColumn(name = "idCity", referencedColumnName = "idCity", nullable = false)
    public City getCityByIdCity() {
        return cityByIdCity;
    }

    public void setCityByIdCity(City cityByIdCity) {
        this.cityByIdCity = cityByIdCity;
    }
}
