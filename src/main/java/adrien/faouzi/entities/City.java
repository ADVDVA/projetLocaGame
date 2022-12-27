package adrien.faouzi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "City", indexes = {
        @Index(name = "idCountry", columnList = "idCountry")
})
public class City implements Serializable {
    private int idCity;
    private int idCountry;
    private int postalCode;
    private String cityName;
    private List<Address> addressesByIdCity;
    private Country countryByIdCountry;
    private List<Store> storesByIdCity;

    @Id
    @Column(name = "idCity")
    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    @Basic
    @Column(name = "idCountry")
    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    @Basic
    @Column(name = "postalCode")
    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "cityName")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return idCity == city.idCity && idCountry == city.idCountry && postalCode == city.postalCode && Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCity, idCountry, postalCode, cityName);
    }

    @OneToMany(mappedBy = "cityByIdCity")
    public List<Address> getAddressesByIdCity() {
        return addressesByIdCity;
    }

    public void setAddressesByIdCity(List<Address> addressesByIdCity) {
        this.addressesByIdCity = addressesByIdCity;
    }

    @ManyToOne
    @JoinColumn(name = "idCountry", referencedColumnName = "idCountry", nullable = false)
    public Country getCountryByIdCountry() {
        return countryByIdCountry;
    }

    public void setCountryByIdCountry(Country countryByIdCountry) {
        this.countryByIdCountry = countryByIdCountry;
    }

    @OneToMany(mappedBy = "cityByIdCity")
    public List<Store> getStoresByIdCity() {
        return storesByIdCity;
    }

    public void setStoresByIdCity(List<Store> storesByIdCity) {
        this.storesByIdCity = storesByIdCity;
    }
}
