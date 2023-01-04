package adrien.faouzi.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user", indexes = {
        @Index(name = "mail", columnList = "mail", unique = true)
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idRole", nullable = false)
    private Role idRole;

    @Column(name = "lastName", nullable = false, length = 60)
    private String lastName;

    @Column(name = "firstName", nullable = false, length = 60)
    private String firstName;

    @Column(name = "dateOfBirth", nullable = false)
    private LocalDateTime dateOfBirth;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registrationDate", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "enable", nullable = false)
    private boolean enable = false;

    @OneToMany(mappedBy = "idUser")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Address> addresses = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && idRole == user.idRole && enable == user.enable && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(phone, user.phone) && Objects.equals(mail, user.mail) && Objects.equals(password, user.password) && Objects.equals(registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRole, lastName, firstName, dateOfBirth, phone, mail, password, registrationDate, enable);
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}