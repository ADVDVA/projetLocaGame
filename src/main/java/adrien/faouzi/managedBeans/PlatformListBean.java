package adrien.faouzi.managedBeans;

import adrien.faouzi.convectorCustom.PlatformConverter;
import adrien.faouzi.entities.Platform;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.PlatformService;
import adrien.faouzi.utility.TableFilter;
import adrien.faouzi.utility.UtilityProcessing;
import org.primefaces.PrimeFaces;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ManagedBean
@SessionScoped
public class PlatformListBean extends TableFilter implements Serializable {

    //platform filtered from db.
    private List<Platform> platformFiltered;
    public List<Platform> getPlatformFiltered(){
        return this.platformFiltered;
    }
    public void setPlatformFiltered(List<Platform> platformFiltered){
        this.platformFiltered = platformFiltered;
    }

    public void doResearch(){

        EntityManager em = EMF.getEM();
        PlatformService platformService = new PlatformService();
        try{
            platformFiltered = platformService.findPlatformByFilter(this.filter, this.order, this.orderAsc, em);
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            platformFiltered = new ArrayList<>();
        }finally{
            em.close();
        }

    }


    public void deleteEntity(int idEntity, boolean permission){
        if(!permission)
            return;
        int countJoin;

        EntityManager em = EMF.getEM();
        PlatformService platformService = new PlatformService();
        EntityTransaction transaction = em.getTransaction();
        try{
            countJoin = platformService.getCountOfJoin(idEntity, em);
            if(countJoin==0){ //this entity has no join in db.

                //delete entity
                transaction.begin();
                platformService.delete(PlatformConverter.getAsObjectStatic(String.valueOf(idEntity)), em);
                transaction.commit();

            }
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            countJoin = -1;
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }

        if(countJoin>0){ //impossible delete because join.
            PrimeFaces.current().executeScript("alertI18NMessage(\"errorDeleteBecauseJoin\")");
        }else if(countJoin==0){ //success delete.
            PrimeFaces.current().executeScript("alertI18NMessage(\"successDelete\", true)");
        }else{ //error process.
            PrimeFaces.current().executeScript("alertI18NMessage(\"errorProcessDelete\")");
        }

    }
}
