package adrien.faouzi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Country")
public class Country implements Serializable {
    private int idCountry;
    private String countryName;
    private List<City> citiesByIdCountry;

    @Id
    @Column(name = "idCountry")
    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    @Basic
    @Column(name = "countryName")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return idCountry == country.idCountry && Objects.equals(countryName, country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCountry, countryName);
    }

    @OneToMany(mappedBy = "countryByIdCountry")
    public List<City> getCitiesByIdCountry() {
        return citiesByIdCountry;
    }

    public void setCitiesByIdCountry(List<City> citiesByIdCountry) {
        this.citiesByIdCountry = citiesByIdCountry;
    }
}
