package adrien.faouzi.managedBeans;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ConnexionBean implements Serializable {

    /**
     * fields
     */

    private String mail;

    private String password;





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

