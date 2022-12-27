package adrien.faouzi.managedBeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ConnexionBean implements Serializable {

    private String name;
    private String password;

    public String goToPageConnexion()
    {
        return "view/connexion";
    }

    public String goToPageAccueil(){
        return "accueil";
    }
}

