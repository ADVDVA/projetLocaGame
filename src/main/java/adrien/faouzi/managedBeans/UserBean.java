package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.*;

import adrien.faouzi.enumeration.TypeAddress;
import adrien.faouzi.services.*;
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
import adrien.faouzi.exeption.ConnectionUserExecption;
import org.primefaces.PrimeFaces;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    @Pattern(regexp = "^[a-zA-Z -]{1,60}$")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[+][0-9]{1,4}[ ]{1}[0-9]{2,4}[ ]{1}[0-9]{2}[ ]{1}[0-9]{2}[ ]{1}[0-9]{2}$")
    private String phone;


    private String mail= null;
    private String messageErrorMail ="hidden";

    @NotNull
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z0-9]{7,}$")
    private String password ="";
    private String passwordVerify= "";
    private String messageErrorPassword = "hidden";

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,}$")
    private String street;

    @NotNull
    @Min(1)
    private int number;

    @Pattern(regexp= "^[a-zA-Z1-9]{0,20}$")
    private String box;

    @NotNull
    @Pattern(regexp = "^[0-9]{1,}$")
    private String postalCode;

    @NotNull
    private String city;
    private HashMap<String, String> citiesMap ;

    @NotNull
    private String country;
    private HashMap<String, String> countryMap;

    @NotNull
    private Date dateOfBirth;
    private Date minDate;
    private Date maxDate;

    private String messageErrorConnection ="hidden";



    private String emailConnection;
    private  String passwordConnection;
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
        this.countryMap = new HashMap<>();
        List <Country> countryListRequest;
        try
        {
            transaction.begin();
            //Call of the service that will use the NamedQuery of the "County" entity
            countryListRequest = countryService.findCountryAll();
            for(Country countryL : countryListRequest)
            {
                this.countryMap.put(countryL.getCountryName(), String.valueOf(countryL.getId()));
            }
            transaction.commit();
        }
        catch(Exception e)
        {
            //UtilityProcessing.debug("Je suis dans le catch de country : " + e);
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
        try
        {
            transaction.begin();
            //Call of the service that will use the NamedQuery of the "User" entity
            User user = userService.findUserByMail(this.mail);
            this.messageErrorMail ="";
            transaction.commit();
        }
        catch(Exception e)
        {
            //UtilityProcessing.debug("Je suis dans le catch de mail : " + e);
            if(this.mail == null)
            {
                messageErrorMail = "hidden";
            }
            else
            {
                if(UtilityProcessing.checKDataWithRegex(this.mail, "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+[.]+[a-zA-Z.]{2,15}$"))
                {
                    this.messageErrorMail="hidden";
                }
                else
                {
                    this.messageErrorMail="";
                }
            }
            if(transaction.isActive())
                transaction.rollback();
        }
        finally
        {
            userService.close();
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
             this.user = userService.findUserByMail(this.emailConnection);
             checkUserConnection(this.user, this.passwordConnection, this.emailConnection);
            this.messageErrorConnection = "hidden";
             redirect = "/accueil";
            transaction.commit();
        }
        catch(Exception e)
        {
            //UtilityProcessing.debug(" je suis dans le catch de la connexion : " + e);
            /* déclancher un message d'erreur*/
            this.messageErrorConnection = "";
            redirect = "/view/connection";
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
     * destroy session connected method
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
        return "/view/connection";

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
    public void checkUserConnection (User user, String password, String mail) throws ConnectionUserExecption
    {
        if (!(user.getMail().equals(mail) && UtilityProcessing.checkPassword(password,user) && user.getEnable()))
        {
            throw new ConnectionUserExecption();
        }
    }

    /**
     * update the city field of the form
     */
    public void updatePostalCodeWithCity ()
    {
        if(this.postalCode != null)
        {
            //initialize.
            CityService cityService = new CityService();
            EntityTransaction transaction = cityService.getTransaction();
            List<City> cityList;
            this.citiesMap = new HashMap<>();
            try
            {
                transaction.begin();
                //Call of the service that will use the NamedQuery of the "city" entity
                cityList = cityService.findCityByPostalCode(Integer.parseInt(this.postalCode));
                for (City cityL: cityList)
                {
                    citiesMap.put(cityL.getCityName(),String.valueOf(cityL.getId()));
                }
                
                //UtilityProcessing.debug("" + cities.size());
                transaction.commit();
            }
            catch(Exception e)
            {
                //UtilityProcessing.debug("Je suis dans le catch de city  : " + e);
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
            citiesMap = new HashMap<>();
        }
    }

    /**
     * Verification Sign up method
     * @return
     */
    public String lastVerificationSignUp()
    {

        //Verification password with passwordVerify
        checkPasswordVerify();

        //Verification mail
        checkMail();

        //last check before adding it to DB
        if(this.messageErrorPassword.equals("hidden") && this.messageErrorMail.equals("hidden"))
        {
            //For user
            //initialize.
            UserService userService = new UserService();
            RoleService roleService = new RoleService();
            AddressService addressService = new AddressService();
            CityService cityService = new CityService();
            EntityTransaction transactionUser = userService.getTransaction();
            EntityTransaction transactionAddress = addressService.getTransaction();
            EntityTransaction transactionCity = cityService.getTransaction();
            EntityTransaction transactionRole = roleService.getTransaction();

            Role role;
            City city;
            User user = new User();
            Address address = new Address();


            user.setLastName(this.lastName);
            user.setFirstName(this.firstName);
            user.setDateOfBirth(UtilityProcessing.castDateToLocalDateTime(this.dateOfBirth));
            user.setPhone(this.phone);
            user.setMail(this.mail);
            user.setPassword(UtilityProcessing.cryptPassword(this.password));
            user.setRegistrationDate(UtilityProcessing.getDateTimeNow());
            user.setEnable(true);

            address.setStreet(this.street);
            address.setNumber(this.number);
            if(this.box.equals(""))
            {
               address.setBox(null);
            }
            else
            {
              address.setBox(this.box);
            }

            address.setTypeAddress(TypeAddress.FACTURATION);
            address.setEnable(true);

            try
            {
                transactionUser.begin();
                transactionCity.begin();
                transactionAddress.begin();
                transactionRole.begin();

                //Call of the service that will use the NamedQuery of the "role" entity
                role = roleService.findRoleByRoleName("Client");

                user.setIdRole(role);

                //Call of the service that will use the NamedQuery of the "user" entity
                this.user = userService.addUser(user);

                //Call of the service that will use the NamedQuery of the "city" entity
                city = cityService.findCityById(Integer.parseInt(this.city));

                address.setIdCity(city);
                address.setIdUser(this.user);
                //Call of the service that will use the NamedQuery of the "address" entity
                addressService.addAddress(address);

                transactionCity.commit();
                transactionRole.commit();
                transactionUser.commit();
                transactionAddress.commit();
            }
            catch(Exception e)
            {
                //UtilityProcessing.debug("Je suis dans le catch de l'ajout d'un user : " + e);
                if(transactionAddress.isActive()||
                transactionCity.isActive()||
                transactionUser.isActive()||
                transactionRole.isActive())
                {
                    transactionAddress.rollback();
                    transactionCity.rollback();
                    transactionUser.rollback();
                    transactionRole.rollback();
                }
            }
            finally
            {
                userService.close();
                roleService.close();
                cityService.close();
                addressService.close();
            }


            return "/accueil";
        }
        else
        {
            return "";
        }
    }

    /**
     * Getter and setter method
     */

    public String getEmailConnection() {
    return emailConnection;
}

    public void setEmailConnection(String emailConnection) {
        this.emailConnection = emailConnection;
    }

    public HashMap<String, String> getCountryMap() {

        return countryMap;
    }

    public void setCountryMap(HashMap<String, String> countryMap) {
        this.countryMap = countryMap;
    }

    public String getMessageErrorMail() {
        String message = this.messageErrorMail;
        this.messageErrorMail = "hidden";
        return message;
    }

    public void setMessageErrorMail(String messageErrorMail) {
        this.messageErrorMail = messageErrorMail;
    }

    public String getPasswordConnection() {
        return passwordConnection;
    }

    public void setPasswordConnection(String passwordConnection) {
        this.passwordConnection = passwordConnection;
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

    public HashMap<String,String> getCitiesMap() {

        return citiesMap;
    }

    public void setCitiesMap(HashMap<String,String> citiesMap) {
        this.citiesMap = citiesMap;
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
