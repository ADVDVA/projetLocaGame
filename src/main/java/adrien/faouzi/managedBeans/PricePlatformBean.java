package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Priceplatform;
import adrien.faouzi.services.PricePlatformService;
import adrien.faouzi.utility.UtilityProcessing;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class PricePlatformBean implements Serializable {


    //object price platform load from other page.
    private Priceplatform pricePlatformSelected;
    public Priceplatform getPricePlatformSelected(){
        return pricePlatformSelected;
    }
    public void setPricePlatformSelected(Priceplatform pricePlatformSelected){
        this.pricePlatformSelected = pricePlatformSelected;
    }


    //load price platform selected in previous page.
    public void loadPricePlatformSelected(int idRedirection){

        PricePlatformService pricePlatformService = new PricePlatformService();
        EntityTransaction transaction = pricePlatformService.getTransaction();

        try{
            transaction.begin();
            this.pricePlatformSelected = pricePlatformService.findPricePlatformById(idRedirection);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.pricePlatformSelected = null;
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            pricePlatformService.close();
        }

    }

}