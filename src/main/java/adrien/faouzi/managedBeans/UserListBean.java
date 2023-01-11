package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.User;
import adrien.faouzi.services.UserService;
import adrien.faouzi.utility.TableFilter;
import adrien.faouzi.utility.UtilityProcessing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class UserListBean extends TableFilter implements Serializable {

    //all users filtered by user.
    private List<User> userFiltered;


    /**
     * do SQL research method (width filter, order by, pagination managed by PrimeFaces).
     */
    public void initialiseUserFiltered(){
        userFiltered = new ArrayList<>();

        UserService userService = new UserService();
        EntityTransaction transactionUser = userService.getTransaction();
        boolean orderby = this.orderAsc;
        try
        {
            transactionUser.begin();

            if(this.order.equals("enable"))
            {
                orderby = !orderby;
            }
            if(orderby)
            {
                this.userFiltered = userService.findUserByFilterAsc(this.filter, this.order);
            }
            else
            {
                this.userFiltered = userService.findUserByFilterDesc(this.filter, this.order);
            }
            transactionUser.commit();
        }catch(Exception e)
        {
            UtilityProcessing.debug("Je suis dans le catch de la recherche par utilisateur : " +e);
            if(transactionUser.isActive())
            {
                transactionUser.rollback();
            }
        }finally {
            userService.close();
        }
    }



    /**
     * getter method
     */

    public void setUserFiltered(List<User> userFiltered) {
        this.userFiltered = userFiltered;
    }

    public List<User> getUserFiltered() {
        return userFiltered;
    }

}
