package adrien.faouzi.managedBeans;

import adrien.faouzi.enumeration.TypeAddress;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class UserBean implements Serializable
{
    /**
     * Fields
     */

    @Pattern(regexp = "^[a-zA-Z ]{1,60}$")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z ]{1,60}$")
    private String firstName;

    @Pattern(regexp = "^[+][0-9]{1,4}[ ]{1}[0-9]{2,4}[ ]{1}[0-9]{2}[ ]{1}[0-9]{2}[ ]{1}[0-9]{2}$")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+.[a-zA-Z.]{2,15}$")
    private String mail;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z0-9]{7,}$")
    private String password;

    private String passwordVerify;

    @Pattern(regexp = "^[a-zA-Z ]{1,}$")
    private String street;

    private int number;

    @Pattern(regexp= "^[a-zA-Z1-9]{1,20}")
    private String box;

    @Pattern(regexp = "^[0-9]{1,}$")
    private String postalCode;
    private List<String> city = new ArrayList<>();
    private String country;

    private Date dateOfBirth;
    private Date minDate;
    private Date maxDate;

    private String hideNoSelectOption;

    /**
     * Post construtor for input date
     */
    @PostConstruct
    public void init()
    {
        Date today = new Date();
        long oneDay = 24 * 60 * 60 * 1000;
        minDate = new Date(today.getTime()- (365 * oneDay) * 100 - ((100/4) * oneDay));
        maxDate = new Date(today.getTime()- (365  * oneDay) * 18 -( 4 * oneDay) );// *18 pour 18 ans
    }


    /**
     * Verification method
     */
    public String lastVerificationSignIn()
    {
        //        // faire couvertire le champs date en LocalDateTime (private LocalDateTime dateOfBirth;)
        // vérifier mot de passe avec l'autre mot de passe.
        return "/accueil";
    }

//    /**
//     *
//     */
//    public void updatePostalCodeWithCity ()
//    {
//        if(this.postalCode.equals("6000"))
//        {
//            this.city = new ArrayList<>();
//            this.city.add("Charleroi");
//            this.city.add("kjhgf");
//            this.city.add("oiuyt");
//            this.city.add("Gerpinnes");
//        }
//    }

    /**
     * Getter and setter method
     */
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswordVerify() {
        return passwordVerify;
    }

    public void setPasswordVerify(String passwordVerify) {
        this.passwordVerify = passwordVerify;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBox() {
        return box;
    }

    public List<String> getCity() {

        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHideNoSelectOption() {
        return hideNoSelectOption;
    }

    public void setHideNoSelectOption(String hideNoSelectOption) {
        this.hideNoSelectOption = hideNoSelectOption;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
}
