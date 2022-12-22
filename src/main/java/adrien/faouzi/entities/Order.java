package adrien.faouzi.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Order {
    private int idOrder;
    private int idUser;
    private Timestamp startDate;
    private Timestamp receptionDate;
    private Object rentalModOfPayment;
    private Timestamp endDate;
    private Timestamp returnDate;
    private Object returnModOfPayment;
    private byte customerNotCame;
    private List<Addressorder> addressordersByIdOrder;
    private List<Copyorder> copyordersByIdOrder;
    private List<Document> documentsByIdOrder;
    private User userByIdUser;

    @Id
    @Column(name = "idOrder")
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
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
    @Column(name = "startDate")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "receptionDate")
    public Timestamp getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Timestamp receptionDate) {
        this.receptionDate = receptionDate;
    }

    @Basic
    @Column(name = "rentalModOfPayment")
    public Object getRentalModOfPayment() {
        return rentalModOfPayment;
    }

    public void setRentalModOfPayment(Object rentalModOfPayment) {
        this.rentalModOfPayment = rentalModOfPayment;
    }

    @Basic
    @Column(name = "endDate")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "returnDate")
    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    @Basic
    @Column(name = "returnModOfPayment")
    public Object getReturnModOfPayment() {
        return returnModOfPayment;
    }

    public void setReturnModOfPayment(Object returnModOfPayment) {
        this.returnModOfPayment = returnModOfPayment;
    }

    @Basic
    @Column(name = "customerNotCame")
    public byte getCustomerNotCame() {
        return customerNotCame;
    }

    public void setCustomerNotCame(byte customerNotCame) {
        this.customerNotCame = customerNotCame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder && idUser == order.idUser && customerNotCame == order.customerNotCame && Objects.equals(startDate, order.startDate) && Objects.equals(receptionDate, order.receptionDate) && Objects.equals(rentalModOfPayment, order.rentalModOfPayment) && Objects.equals(endDate, order.endDate) && Objects.equals(returnDate, order.returnDate) && Objects.equals(returnModOfPayment, order.returnModOfPayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idUser, startDate, receptionDate, rentalModOfPayment, endDate, returnDate, returnModOfPayment, customerNotCame);
    }

    @OneToMany(mappedBy = "orderByIdOrder")
    public List<Addressorder> getAddressordersByIdOrder() {
        return addressordersByIdOrder;
    }

    public void setAddressordersByIdOrder(List<Addressorder> addressordersByIdOrder) {
        this.addressordersByIdOrder = addressordersByIdOrder;
    }

    @OneToMany(mappedBy = "orderByIdOrder")
    public List<Copyorder> getCopyordersByIdOrder() {
        return copyordersByIdOrder;
    }

    public void setCopyordersByIdOrder(List<Copyorder> copyordersByIdOrder) {
        this.copyordersByIdOrder = copyordersByIdOrder;
    }

    @OneToMany(mappedBy = "orderByIdOrder")
    public List<Document> getDocumentsByIdOrder() {
        return documentsByIdOrder;
    }

    public void setDocumentsByIdOrder(List<Document> documentsByIdOrder) {
        this.documentsByIdOrder = documentsByIdOrder;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }
}
