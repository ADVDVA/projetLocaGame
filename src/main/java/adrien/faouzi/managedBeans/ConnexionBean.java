package adrien.faouzi.managedBeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Named
@RequestScoped
public class ConnexionBean implements Serializable {

    /**
     * field
     */
    private String mail;

    private String password;

    public String goToPageConnexion()
    {
        return "view/connexion";
    }

    public String goToPageAccueil(){
        return "accueil";
    }

    /**
     * getter and setter method
     * @return
     */

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
}

