package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.*;

import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.*;
import adrien.faouzi.utility.UtilityProcessing;
import javax.annotation.PostConstruct;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Named
@RequestScoped
public class UserBean implements Serializable
{
    /**
     * Fields
     */

    private String mail= null;
    private String messageErrorMail ="hidden";


    private String password ="";
    private String passwordVerify= "";
    private String messageErrorPassword = "hidden";

    private City city;
    private List<City> cityList;

    private Country country;
    private List<Country> countryList;

    private Role role;
    private List<Role> roleList;

    private Address address;

    private Date minDate;
    private Date maxDate;

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
        EntityManager em = EMF.getEM();
        CountryService countryService = new CountryService();
        EntityTransaction transaction = em.getTransaction();
        try
        {
            transaction.begin();
            //Call of the service that will use the NamedQuery of the "County" entity
            this.countryList = countryService.findCountryAll(em);
            transaction.commit();
        }
        catch(Exception e)
        {
            UtilityProcessing.debug("Je suis dans le catch de country : " + e);

        }
        finally
        {
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }
    }


    /**
     * Verification input mail method
     */
    public void checkMail()
    {
        //For input mail
        //initialize.
        EntityManager em = EMF.getEM();
        UserService userService = new UserService();
        EntityTransaction transaction = em.getTransaction();
        User userRequest;
        try
        {
            transaction.begin();
            //Call of the service that will use the NamedQuery of the "User" entity
            userRequest = userService.findUserByMail(this.user.getMail(), em);
            this.messageErrorMail ="";
            transaction.commit();
        }
        catch(Exception e)
        {
            UtilityProcessing.debug("Je suis dans le catch de mail : " + e);
            if(this.user.getMail() == null)
            {
                messageErrorMail = "hidden";
            }
            else
            {
                if(UtilityProcessing.checKDataWithRegex(this.user.getMail(), "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+[.]+[a-zA-Z.]{2,15}$"))
                {
                    this.messageErrorMail="hidden";
                }
                else
                {
                    this.messageErrorMail="";
                }
            }

        }
        finally
        {
            if(transaction.isActive())
            transaction.rollback();
            em.close();
        }
    }

    /**
     * Verification input passwordVerify method
     */
    public void checkPasswordVerify()
    {
        if(this.passwordVerify.equals(this.user.getPassword()))
        {
            this.messageErrorPassword = "hidden";
        }
        else
        {
            this.messageErrorPassword = "";
        }
    }

    /**
     * update the city field of the form
     */
    public void updatePostalCodeWithCity ()
    {
        if(this.city.getPostalCode() != 0)
        {
            //initialize.
            EntityManager em = EMF.getEM();
            CityService cityService = new CityService();
            EntityTransaction transaction = em.getTransaction();

            try
            {
                transaction.begin();
                //Call of the service that will use the NamedQuery of the "city" entity
                cityList = cityService.findCityByPostalCode(this.city.getPostalCode(), em);
                
                //UtilityProcessing.debug("" + cities.size());
                transaction.commit();
            }
            catch(Exception e)
            {
                UtilityProcessing.debug("Je suis dans le catch de city  : " + e);
            }
            finally
            {
                if(transaction.isActive())
                transaction.rollback();
                em.close();
            }
        }
        else
        {
            this.cityList = new ArrayList<>();
        }
    }

    /**
     * Verification Sign up method
     * Le mettrer dans un request et puis il se connecte via la connexion
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
            EntityManager em = EMF.getEM();
            UserService userService = new UserService();
            RoleService roleService = new RoleService();
            AddressService addressService = new AddressService();
            CityService cityService = new CityService();
            EntityTransaction transaction = em.getTransaction();

            // mettre directement dans le user

            this.user.setPassword(UtilityProcessing.cryptPassword(this.user.getPassword()));
            this.user.setRegistrationDate(UtilityProcessing.getDateTimeNow());

            try
            {
                transaction.begin();

                //Call of the service that will use the NamedQuery of the "role" entity
                this.role = roleService.findRoleByRoleName("Client", em);

                user.setIdRole(this.role);

                //Call of the service that will use the NamedQuery of the "user" entity
                this.user = userService.addUser(user, em);

                //Call of the service that will use the NamedQuery of the "city" entity
                this.address.setIdCity(cityService.findCityById(this.address.getIdCity().getId(), em));

                address.setIdUser(this.user);

                //Call of the service that will use the NamedQuery of the "address" entity
                addressService.addAddress(address,em);

                transaction.commit();

            }
            catch(Exception e)
            {
                UtilityProcessing.debug("Je suis dans le catch de l'ajout d'un user : " + e);
            }
            finally
            {
                if(transaction.isActive())
                {
                    transaction.rollback();
                }
                em.close();
            }
            return "/accueil";
        }
        else
        {
            return "";
        }
    }

    /**
     * Redirection go to userlist page
     * @return
     */
    public String goToOrderPage(String redirection){
        return "/view/userList";
    }


    /**
     * Getter and setter method
     */

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }
    public String getMessageErrorMail() {
        String message = this.messageErrorMail;
        this.messageErrorMail = "hidden";
        return message;
    }

    public void setMessageErrorMail(String messageErrorMail) {
        this.messageErrorMail = messageErrorMail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPasswordVerify() {
        return passwordVerify;
    }

    public void setPasswordVerify(String passwordVerify) {
        this.passwordVerify = passwordVerify;
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


    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
}
