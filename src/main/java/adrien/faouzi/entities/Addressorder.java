package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Addressorder {
    private int idAddress;
    private int idOrder;
    private Address addressByIdAddress;
    private Order orderByIdOrder;

    @Basic
    @Column(name = "idAddress")
    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    @Basic
    @Column(name = "idOrder")
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addressorder that = (Addressorder) o;
        return idAddress == that.idAddress && idOrder == that.idOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAddress, idOrder);
    }

    @ManyToOne
    @JoinColumn(name = "idAddress", referencedColumnName = "idAddress", nullable = false)
    public Address getAddressByIdAddress() {
        return addressByIdAddress;
    }

    public void setAddressByIdAddress(Address addressByIdAddress) {
        this.addressByIdAddress = addressByIdAddress;
    }

    @ManyToOne
    @JoinColumn(name = "idOrder", referencedColumnName = "idOrder", nullable = false)
    public Order getOrderByIdOrder() {
        return orderByIdOrder;
    }

    public void setOrderByIdOrder(Order orderByIdOrder) {
        this.orderByIdOrder = orderByIdOrder;
    }
}
