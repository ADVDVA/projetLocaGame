package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Priceplatform;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.PricePlatformService;
import adrien.faouzi.utility.TableFilter;
import adrien.faouzi.utility.UtilityProcessing;

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
public class PricePlatformListBean extends TableFilter implements Serializable {

    //catalog filtered price platform.
    private List<Priceplatform> pricePlatformFiltered;

    public List<Priceplatform> getPricePlatformFiltered(){
        return this.pricePlatformFiltered;
    }
    public void setPricePlatformFiltered(List<Priceplatform> pricePlatformFiltered){
        this.pricePlatformFiltered = pricePlatformFiltered;
    }

    public void doResearch(){

        EntityManager em = EMF.getEM();
        PricePlatformService pricePlatformService = new PricePlatformService();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            pricePlatformFiltered = pricePlatformService.findPricePlatformByFilter(this.filter, this.order, this.orderAsc, em);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            pricePlatformFiltered = new ArrayList<>();

        }finally{
            if(transaction.isActive())
            transaction.rollback();
            em.close();
        }

    }
}
