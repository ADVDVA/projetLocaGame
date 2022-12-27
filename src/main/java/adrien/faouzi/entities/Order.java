package adrien.faouzi.entities;

import adrien.faouzi.enumération.RentalModOfPayment;
import adrien.faouzi.enumération.ReturnModOfPayment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Order", indexes = {
        @Index(name = "idUser", columnList = "idUser")
})
public class Order implements Serializable {
    private int idOrder;
    private int idUser;
    private LocalDateTime startDate;
    private LocalDateTime receptionDate;
    private RentalModOfPayment rentalModOfPayment;
    private LocalDateTime endDate;
    private LocalDateTime returnDate;
    private ReturnModOfPayment returnModOfPayment;
    private boolean customerNotCame;
    private List<AddressOrder> addressordersByIdOrder;
    private List<CopyOrder> copyordersByIdOrder;
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
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "receptionDate")
    public LocalDateTime getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(LocalDateTime receptionDate) {
        this.receptionDate = receptionDate;
    }

    @Basic
    @Column(name = "rentalModOfPayment")
    public String getRentalModOfPayment() {
        return rentalModOfPayment.getRentalModOfPayement();
    }

    public void setRentalModOfPayment(RentalModOfPayment rentalModOfPayment) {
        this.rentalModOfPayment = rentalModOfPayment;
    }

    @Basic
    @Column(name = "endDate")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "returnDate")
    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Basic
    @Column(name = "returnModOfPayment")
    public String getReturnModOfPayment() {
        return returnModOfPayment.getReturnModOfPayment();
    }

    public void setReturnModOfPayment(ReturnModOfPayment returnModOfPayment) {
        this.returnModOfPayment = returnModOfPayment;
    }

    @Basic
    @Column(name = "customerNotCame")
    public boolean getCustomerNotCame() {
        return customerNotCame;
    }

    public void setCustomerNotCame(boolean customerNotCame) {
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
    public List<AddressOrder> getAddressordersByIdOrder() {
        return addressordersByIdOrder;
    }

    public void setAddressordersByIdOrder(List<AddressOrder> addressordersByIdOrder) {
        this.addressordersByIdOrder = addressordersByIdOrder;
    }

    @OneToMany(mappedBy = "orderByIdOrder")
    public List<CopyOrder> getCopyordersByIdOrder() {
        return copyordersByIdOrder;
    }

    public void setCopyordersByIdOrder(List<CopyOrder> copyordersByIdOrder) {
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
