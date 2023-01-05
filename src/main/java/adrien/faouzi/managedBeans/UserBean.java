package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.User;

import adrien.faouzi.services.UserService;
import adrien.faouzi.utility.UtilityProcessing;
import at.favre.lib.crypto.bcrypt.BCrypt;

import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import adrien.faouzi.exeption.ConnexionUserExecption;
import org.primefaces.PrimeFaces;

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

    @Pattern(regexp = "^[a-zA-Z -]{1,60}$")
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

    private String messageErrorConnection ="hidden";

    private String emailConnexion;
    private  String passwordConnexion;
    private User user;

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
     * Verification connection method
     */
    public String lastVerificationSignIn()
    {
        //initialize.
        UserService userService = new UserService();
        String redirect;

        try
        {
            //Call of the service that will use the NamedQuery of the "User" entity
             this.user = userService.findUserByMailAndPassword(this.emailConnexion);
             checkUserConnection(this.user, this.passwordConnexion, this.emailConnexion);
            this.messageErrorConnection = "hidden";
             redirect = "/accueil";

        }
        catch(Exception e)
        {
            UtilityProcessing.debug("Récupération de données d'utilisateur introuvable : " + e);
            /* déclancher un message d'erreur*/
            this.messageErrorConnection = "";
            redirect = "/view/connexion";
        }
        finally
        {
            userService.close();
        }

        return redirect;
    }

    /**
     * distroy session connected method
     */
    public String destroySession()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.user = null;
        UtilityProcessing.debug( FacesContext.getCurrentInstance().getViewRoot().getViewId());
        if("/accueil.xhtml".equals( FacesContext.getCurrentInstance().getViewRoot().getViewId()))
        {
            // managed bean go to js
            PrimeFaces.current().executeScript("submitLanguageForm(\"headerLanguageButtonContainer\")");
            return "";
        }
        else
        {
            return "/accueil";
        }


    }


    /**
     * Redirection go to connection page
     * @return
     */
    public String goToPageConnection()
    {
        return "/view/connexion";
    }

    /**
     * Redirection go to home page
     * @return
     */
    public String goToPageAccueil(){
        return "/accueil";
    }

    /**
     * User processing method
     */
    public void checkUserConnection (User user, String password, String mail) throws ConnexionUserExecption
    {
        if (!(user.getMail().equals(mail) && user.getPassword().equals(password) /*checkPassword(password,user)*/ && user.getEnable()))
        {
            throw new ConnexionUserExecption();
        }
    }

    /**
     * Verification password method
     * @param password
     * @return
     */
    public boolean checkPassword (String password, User user){
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        return result.verified;
    }

    /**
     * Verification Sign up method
     * @return
     */
    public String lastVerificationSignUp()
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

    public String getEmailConnexion() {
        return emailConnexion;
    }

    public void setEmailConnexion(String emailConnexion) {
        this.emailConnexion = emailConnexion;
    }

    public String getPasswordConnexion() {
        return passwordConnexion;
    }

    public void setPasswordConnexion(String passwordConnexion) {
        this.passwordConnexion = passwordConnexion;
    }

    public String getMessageErrorConnection() {
        String message = this.messageErrorConnection;
        this.messageErrorConnection = "hidden";
        return message;
    }

    public void setMessageErrorConnection(String messageErrorConnection) {
        this.messageErrorConnection = messageErrorConnection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
