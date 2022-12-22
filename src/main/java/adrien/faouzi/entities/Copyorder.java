package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Copyorder {
    private int idCopy;
    private int idOrder;
    private Double copyPrice;
    private byte returnDestroy;
    private Double penalityPrice;
    private Copy copyByIdCopy;
    private Order orderByIdOrder;

    @Basic
    @Column(name = "idCopy")
    public int getIdCopy() {
        return idCopy;
    }

    public void setIdCopy(int idCopy) {
        this.idCopy = idCopy;
    }

    @Basic
    @Column(name = "idOrder")
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Basic
    @Column(name = "copyPrice")
    public Double getCopyPrice() {
        return copyPrice;
    }

    public void setCopyPrice(Double copyPrice) {
        this.copyPrice = copyPrice;
    }

    @Basic
    @Column(name = "returnDestroy")
    public byte getReturnDestroy() {
        return returnDestroy;
    }

    public void setReturnDestroy(byte returnDestroy) {
        this.returnDestroy = returnDestroy;
    }

    @Basic
    @Column(name = "penalityPrice")
    public Double getPenalityPrice() {
        return penalityPrice;
    }

    public void setPenalityPrice(Double penalityPrice) {
        this.penalityPrice = penalityPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copyorder copyorder = (Copyorder) o;
        return idCopy == copyorder.idCopy && idOrder == copyorder.idOrder && returnDestroy == copyorder.returnDestroy && Objects.equals(copyPrice, copyorder.copyPrice) && Objects.equals(penalityPrice, copyorder.penalityPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCopy, idOrder, copyPrice, returnDestroy, penalityPrice);
    }

    @ManyToOne
    @JoinColumn(name = "idCopy", referencedColumnName = "idCopy", nullable = false)
    public Copy getCopyByIdCopy() {
        return copyByIdCopy;
    }

    public void setCopyByIdCopy(Copy copyByIdCopy) {
        this.copyByIdCopy = copyByIdCopy;
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
