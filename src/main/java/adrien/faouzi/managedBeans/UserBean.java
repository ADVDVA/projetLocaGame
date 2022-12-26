package adrien.faouzi.managedBeans;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
    private String name;
    private String password;

    public String testreturn ()
    {
        return testName();
    }

    public String testName ()
    {
        return !this.name.equals("toto") ? "welcome": "index";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}