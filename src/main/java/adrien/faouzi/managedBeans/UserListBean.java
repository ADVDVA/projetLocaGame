package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.User;
import adrien.faouzi.utility.TableFilter;
import adrien.faouzi.utility.UtilityProcessing;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UserListBean extends TableFilter implements Serializable {

    //all users filtered by user, session scope.
    private List<User> userFiltered;


    /**
     * do SQL research method (width filter, order by, pagination managed by PrimeFaces).
     */
    public void initialiseUserFiltered(){
        userFiltered = new ArrayList<>();
        /* mettre le service*/;
        UtilityProcessing.debug("actualiseResearch");

        //SQL.
        //select * from *Product*
        //where (
        // *all param == this.filter*
        //)
        //order by this.order (orderAsc? "asc": "desc")
    }

    /**
     * getter method
     * @return
     */

    public List<User> getUserFiltered() {
        return userFiltered;
    }

}
