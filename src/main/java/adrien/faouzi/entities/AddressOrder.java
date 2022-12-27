package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addressorder")
public class AddressOrder {
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idAddress", nullable = false)
    private Address idAddress;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idOrder", nullable = false)
    private Order idOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressOrder that = (AddressOrder) o;
        return idAddress == that.idAddress && idOrder == that.idOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAddress, idOrder);
    }

    public Order getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order idOrder) {
        this.idOrder = idOrder;
    }

    public Address getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Address idAddress) {
        this.idAddress = idAddress;
    }
}