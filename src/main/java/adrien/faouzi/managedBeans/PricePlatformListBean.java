package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Priceplatform;
import adrien.faouzi.entities.Product;
import adrien.faouzi.services.PricePlatformService;
import adrien.faouzi.utility.TableFilter;
import adrien.faouzi.utility.UtilityProcessing;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
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

        PricePlatformService pricePlatformService = new PricePlatformService();
        EntityTransaction transaction = pricePlatformService.getTransaction();

        try{
            transaction.begin();
            pricePlatformFiltered = pricePlatformService.findPricePlatformByFilter(this.filter, this.order, this.orderAsc);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            pricePlatformFiltered = new ArrayList<>();
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            pricePlatformService.close();
        }

    }
}
