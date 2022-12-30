package adrien.faouzi.entities;

import adrien.faouzi.enumeration.RentalModOfPayment;
import adrien.faouzi.enumeration.ReturnModOfPayment;

import javax.persistence.*;
import java.time.LocalDateTime ;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrder", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idUser", nullable = false)
    private User idUser;

    @Column(name = "startDate")
    private LocalDateTime  startDate;

    @Column(name = "receptionDate")
    private LocalDateTime  receptionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "rentalModOfPayment", nullable = false)
    private RentalModOfPayment rentalModOfPayment;

    @Column(name = "endDate")
    private LocalDateTime  endDate;

    @Column(name = "returnDate")
    private LocalDateTime  returnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "returnModOfPayment", nullable = false)
    private ReturnModOfPayment returnModOfPayment;

    @Column(name = "customerNotCame", nullable = false)
    private boolean customerNotCame = false;

    @OneToMany(mappedBy = "idOrder")
    private List<CopyOrder> copyOrders = new ArrayList<>();

    @OneToMany(mappedBy = "idOrder")
    private List<Document> documents = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && idUser == order.idUser && customerNotCame == order.customerNotCame && Objects.equals(startDate, order.startDate) && Objects.equals(receptionDate, order.receptionDate) && Objects.equals(rentalModOfPayment, order.rentalModOfPayment) && Objects.equals(endDate, order.endDate) && Objects.equals(returnDate, order.returnDate) && Objects.equals(returnModOfPayment, order.returnModOfPayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, startDate, receptionDate, rentalModOfPayment, endDate, returnDate, returnModOfPayment, customerNotCame);
    }

    @ManyToMany
    @JoinTable(name = "addressorder",
            joinColumns = @JoinColumn(name = "idOrder"),
            inverseJoinColumns = @JoinColumn(name = "idAddress"))
    private List<Address> addresses = new ArrayList<>();

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<CopyOrder> getCopyOrders() {
        return copyOrders;
    }

    public void setCopyOrders(List<CopyOrder> copyOrders) {
        this.copyOrders = copyOrders;
    }

    public boolean getCustomerNotCame() {
        return customerNotCame;
    }

    public void setCustomerNotCame(boolean customerNotCame) {
        this.customerNotCame = customerNotCame;
    }

    public String getReturnModOfPayment() {
        return returnModOfPayment.getReturnModOfPayment();
    }

    public void setReturnModOfPayment(ReturnModOfPayment returnModOfPayment) {
        this.returnModOfPayment = returnModOfPayment;
    }

    public LocalDateTime  getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime  returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDateTime  getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime  endDate) {
        this.endDate = endDate;
    }

    public String getRentalModOfPayment() {
        return rentalModOfPayment.getRentalModOfPayement();
    }

    public void setRentalModOfPayment(RentalModOfPayment rentalModOfPayment) {
        this.rentalModOfPayment = rentalModOfPayment;
    }

    public LocalDateTime  getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(LocalDateTime  receptionDate) {
        this.receptionDate = receptionDate;
    }

    public LocalDateTime  getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime  startDate) {
        this.startDate = startDate;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}