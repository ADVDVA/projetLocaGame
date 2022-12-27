package adrien.faouzi.entities;

import adrien.faouzi.enumération.TypeAddress;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "address", indexes = {
        @Index(name = "idUser", columnList = "idUser"),
        @Index(name = "idCity", columnList = "idCity")
})
public class Address implements Serializable {
    private int idAddress;
    private int idUser;
    private int idCity;
    private String street;
    private int number;
    private String box;
    private TypeAddress typeAddress;
    private boolean enable;
    private City cityByIdCity;
    private User userByIdUser;
    private List<AddressOrder> addressordersByIdAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAddress", nullable = false)
    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    @Basic
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "idCity", nullable = false)
    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    @Basic
    @Column(name = "street", nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "box", length = 20, nullable = true)
    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    @Basic
    @Column(name = "typeAddress", nullable = false)
    public String getTypeAddress() {
        return typeAddress.getTypeAddress();
    }

    public void setTypeAddress(TypeAddress typeAddress) {
        this.typeAddress = typeAddress;
    }

    @Basic
    @Column(name = "enable")
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return idAddress == address.idAddress && idUser == address.idUser && idCity == address.idCity && number == address.number && enable == address.enable && Objects.equals(street, address.street) && Objects.equals(box, address.box) && Objects.equals(typeAddress, address.typeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAddress, idUser, idCity, street, number, box, typeAddress, enable);
    }

    @ManyToOne
    @JoinColumn(name = "idCity", referencedColumnName = "idCity", nullable = false)
    public City getCityByIdCity() {
        return cityByIdCity;
    }

    public void setCityByIdCity(City cityByIdCity) {
        this.cityByIdCity = cityByIdCity;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    @OneToMany(mappedBy = "addressByIdAddress")
    public List<AddressOrder> getAddressordersByIdAddress() {
        return addressordersByIdAddress;
    }

    public void setAddressordersByIdAddress(List<AddressOrder> addressordersByIdAddress) {
        this.addressordersByIdAddress = addressordersByIdAddress;
    }
}
