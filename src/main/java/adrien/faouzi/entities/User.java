package adrien.faouzi.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "User", indexes = {
        @Index(name = "idRole", columnList = "idRole")
})
public class User {
    private int idUser;
    private int idRole;
    private String lastName;
    private String firstName;
    private LocalDateTime dateOfBirth;
    private String phone;
    private String mail;
    private String password;
    private LocalDateTime registrationDate;
    private boolean enable;
    private List<Address> addressesByIdUser;
    private List<Order> ordersByIdUser;
    private Role roleByIdRole;

    @Id
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "idRole")
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "dateOfBirth")
    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "registrationDate")
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Basic
    @Column(name = "enable")
    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser && idRole == user.idRole && enable == user.enable && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(phone, user.phone) && Objects.equals(mail, user.mail) && Objects.equals(password, user.password) && Objects.equals(registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idRole, lastName, firstName, dateOfBirth, phone, mail, password, registrationDate, enable);
    }

    @OneToMany(mappedBy = "userByIdUser")
    public List<Address> getAddressesByIdUser() {
        return addressesByIdUser;
    }

    public void setAddressesByIdUser(List<Address> addressesByIdUser) {
        this.addressesByIdUser = addressesByIdUser;
    }

    @OneToMany(mappedBy = "userByIdUser")
    public List<Order> getOrdersByIdUser() {
        return ordersByIdUser;
    }

    public void setOrdersByIdUser(List<Order> ordersByIdUser) {
        this.ordersByIdUser = ordersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "idRole", referencedColumnName = "idRole", nullable = false)
    public Role getRoleByIdRole() {
        return roleByIdRole;
    }

    public void setRoleByIdRole(Role roleByIdRole) {
        this.roleByIdRole = roleByIdRole;
    }
}
