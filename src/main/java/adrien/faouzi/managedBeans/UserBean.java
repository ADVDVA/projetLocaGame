package adrien.faouzi.managedBeans;
import adrien.faouzi.enumération.MultiPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class UserBean implements Serializable {
    private String name;
    private String password;
    private List<String> favoriteSports;



    public String testreturn ()
    {
        return testName();
    }

    public String testName ()
    {
        return this.name.equals("toto") ? "index": "welcome";
    }

    public List<String> getFavoriteSports() {
        if(favoriteSports == null)
        {
            favoriteSports = new ArrayList<String>();
            favoriteSports.add("football");
            favoriteSports.add("tennis");
            favoriteSports.add("basket");
        }
        return favoriteSports;
    }

    public void setFavoriteSports(List<String> favoriteSports) {
        this.favoriteSports = favoriteSports;
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