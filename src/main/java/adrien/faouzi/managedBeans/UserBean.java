package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.City;
import adrien.faouzi.entities.Country;
import adrien.faouzi.entities.User;

import adrien.faouzi.services.CityService;
import adrien.faouzi.services.CountryService;
import adrien.faouzi.services.UserService;
import adrien.faouzi.utility.UtilityProcessing;
import at.favre.lib.crypto.bcrypt.BCrypt;

import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityTransaction;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import adrien.faouzi.exeption.ConnexionUserExecption;
import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Named
@SessionScoped
public class UserBean implements Serializable
{
    /**
     * Fields
     */

    @NotNull
    @Pattern(regexp = "^[a-zA-Z -]{1,60}$")
    private String lastName;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]{1,60}$")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[+][0-9]{1,4}[ ]{1}[0-9]{2,4}[ ]{1}[0-9]{2}[ ]{1}[0-9]{2}[ ]{1}[0-9]{2}$")
    private String phone;


    private String mail;

    private String messageErrorMail ="hidden";

    @NotNull
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z0-9]{7,}$")
    private String password ="";

    private String passwordVerify= "";

    private String messageErrorPassword = "hidden";
    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]{1,}$")
    private String street;

    @NotNull
    @Min(1)
    private int number;

    @NotNull
    @Pattern(regexp= "^[a-zA-Z1-9]{1,20}")
    private String box;

    @NotNull
    @Pattern(regexp = "^[0-9]{1,}$")
    private String postalCode;

    @NotNull
    private String city;
    private HashMap<String, String> cities ;

    @NotNull
    private String country;
    private List<String> countryList;

    @NotNull
    private Date dateOfBirth;
    private Date minDate;
    private Date maxDate;

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
        //for input date of birth
        Date today = new Date();
        long oneDay = 24 * 60 * 60 * 1000;
        minDate = new Date(today.getTime()- (365 * oneDay) * 100 - ((100/4) * oneDay));
        maxDate = new Date(today.getTime()- (365  * oneDay) * 18 -( 4 * oneDay) );// *18 pour 18 ans

        //For input country
        //initialize.
        CountryService countryService = new CountryService();
        EntityTransaction transaction = countryService.getTransaction();
        this.countryList = new ArrayList<>();
        List <Country> countryListRequest;
        try
        {
            transaction.begin();
            //Call of the service that will use the NamedQuery of the "County" entity
            countryListRequest = countryService.findCountryAll();
            UtilityProcessing.debug("country list : " +countryListRequest.size());
            for(Country countryL : countryListRequest)
            {
                this.countryList.add(countryL.getCountryName());
            }
            transaction.commit();
        }
        catch(Exception e)
        {
            UtilityProcessing.debug("Récupération de données du pays introuvable : " + e);
            if(transaction.isActive())
                transaction.rollback();
        }
        finally
        {
            countryService.close();
        }
    }


    /**
     * Verification input mail method
     */
    public void checkMail()
    {
        //For input mail
        //initialize.
        UserService userService = new UserService();
        EntityTransaction transaction = userService.getTransaction();
        User user;
        try
        {
            transaction.begin();
            //Call of the service that will use the NamedQuery of the "County" entity
            user = userService.findUserByMail();
            UtilityProcessing.debug("mail DB : " + user.getMail());
           // Pattern pattern = Pattern.

            if(user.getMail().equals(this.mail) && this.mail.equals( "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+[.]+[a-zA-Z.]{2,15}$)"))
            {
                this.messageErrorMail ="";
            }
            else
            {
                this.messageErrorMail="hidden";
            }
            transaction.commit();
        }
        catch(Exception e)
        {
            UtilityProcessing.debug("Récupération de données du pays introuvable : " + e);
            if(transaction.isActive())
                transaction.rollback();
        }
        finally
        {
            countryService.close();
        }
    }


    /**
     * Verification input passwordVerify method
     */
    public void checkPasswordVerify()
    {
        if(this.passwordVerify.equals(this.password))
        {
            this.messageErrorPassword = "hidden";
        }
        else
        {
            this.messageErrorPassword = "";
        }
    }

    /**
     * Verification connection method
     */
    public String lastVerificationSignIn()
    {
        //initialize.
        UserService userService = new UserService();
        EntityTransaction transaction = userService.getTransaction();
        String redirect;

        try
        {
            transaction.begin();
            //Call of the service that will use the NamedQuery of the "User" entity
             this.user = userService.findUserByMailAndPassword(this.emailConnexion);
             checkUserConnection(this.user, this.passwordConnexion, this.emailConnexion);
            this.messageErrorConnection = "hidden";
             redirect = "/accueil";
            transaction.commit();
        }
        catch(Exception e)
        {
            UtilityProcessing.debug("Récupération de données d'utilisateur introuvable : " + e);
            /* déclancher un message d'erreur*/
            this.messageErrorConnection = "";
            redirect = "/view/connexion";
            if(transaction.isActive())
                transaction.rollback();
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
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        facesContext.getExternalContext().getSessionMap().put("userBean", null);

//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//        sessionMap.remove();
        this.user = null;
      //  UtilityProcessing.debug( FacesContext.getCurrentInstance().getViewRoot().getViewId());
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

        //Verification password with passwordVerify
        checkPasswordVerify();

        //last check before adding it to DB
        if(this.messageErrorPassword.equals("hidden"))
        {
            return "/accueil";
        }
        else
        {
            return "";
        }


        //        // faire couvertire le champs date en LocalDateTime (private LocalDateTime dateOfBirth;)
        // vérifier le mail unique
        //ajouter idRole à 3
        // type address à "FACTURATION"
        //crypter le mot de passe
        // user.enable à 1

    }

    /**
     * update field city
     */
    public void updatePostalCodeWithCity ()
    {
        if(this.postalCode != null)
        {
            //initialize.
            CityService cityService = new CityService();
            EntityTransaction transaction = cityService.getTransaction();
            List<City> cityList;
            this.cities = new HashMap<>();
            try
            {
                transaction.begin();
                //Call of the service that will use the NamedQuery of the "city" entity
                cityList = cityService.findCityByPostalCode(Integer.parseInt(this.postalCode));
                for (City cityL: cityList)
                {
                    cities.put(cityL.getCityName(),String.valueOf(cityL.getId()));
                }
                
                //UtilityProcessing.debug("" + cities.size());
                transaction.commit();
            }
            catch(Exception e)
            {
                UtilityProcessing.debug("Récupération de données de city introuvable : " + e);
                if(transaction.isActive())
                    transaction.rollback();
            }
            finally
            {
                cityService.close();
            }
        }
        else
        {
            cities = new HashMap<>();
        }
    }

    /**
     * Getter and setter method
     */

    public String getEmailConnexion() {
    return emailConnexion;
}

    public void setEmailConnexion(String emailConnexion) {
        this.emailConnexion = emailConnexion;
    }

    public List<String> getCountryList() {

        return countryList;
    }

    public void setCountryList(List<String> countryList) {
        this.countryList = countryList;
    }

    public String getMessageErrorMail() {
        return messageErrorMail;
    }

    public void setMessageErrorMail(String messageErrorMail) {
        this.messageErrorMail = messageErrorMail;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

    public HashMap<String,String> getCities() {

        return cities;
    }

    public void setCities(HashMap<String,String> cities) {
        this.cities = cities;
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

    public String getMessageErrorPassword() {
        String message = this.messageErrorPassword;
        this.messageErrorPassword = "hidden";
        UtilityProcessing.debug("test  message : "+ message);

        return message;
    }

    public void setMessageErrorPassword(String messageErrorPassword) {
        this.messageErrorPassword = messageErrorPassword;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
}
