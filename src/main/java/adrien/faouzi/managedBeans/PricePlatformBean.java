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
    public boolean pricePlatformSelectedIsErrorLoad(){
        return (this.pricePlatformSelected == null);
    }

    //char for type of page generate (c,r,u,d).
    private char modeSelected = 'r';
    public boolean isModeSelected(char modeAsk){
        return (this.modeSelected == modeAsk);
    }


    //load price platform selected in previous page.
    public void loadPricePlatformSelected(PricePlatformListBean pricePlatformListBean){

        this.modeSelected = pricePlatformListBean.getModeRedirection();
        if(modeSelected == 'c') {
            this.pricePlatformSelected = new Priceplatform();
            return; //no load from DB with create mode.
        }

        PricePlatformService pricePlatformService = new PricePlatformService();
        EntityTransaction transaction = pricePlatformService.getTransaction();

        try{
            transaction.begin();
            this.pricePlatformSelected = pricePlatformService.findPricePlatformById(
                    pricePlatformListBean.getIdRedirection()
            );
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



    //submit form (c,u).
    public String submitForm(String urlRedirectIfSucces){
        //do Create or Update.
        //if error, return null for stay in page.
        return urlRedirectIfSucces;
    }

}
