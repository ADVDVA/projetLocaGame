package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Address {
    private int idAddress;
    private int idUser;
    private int idCity;
    private String street;
    private int number;
    private String box;
    private Object typeAddress;
    private boolean enable;
    private City cityByIdCity;
    private User userByIdUser;
    private List<Addressorder> addressordersByIdAddress;

    @Id
    @Column(name = "idAddress")
    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    @Basic
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "box")
    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    @Basic
    @Column(name = "typeAddress")
    public Object getTypeAddress() {
        return typeAddress;
    }

    public void setTypeAddress(Object typeAddress) {
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
    public List<Addressorder> getAddressordersByIdAddress() {
        return addressordersByIdAddress;
    }

    public void setAddressordersByIdAddress(List<Addressorder> addressordersByIdAddress) {
        this.addressordersByIdAddress = addressordersByIdAddress;
    }
}
