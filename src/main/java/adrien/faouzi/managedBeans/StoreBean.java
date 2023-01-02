package adrien.faouzi.managedBeans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;

import adrien.faouzi.entities.Store;
import adrien.faouzi.projetlocagame.connexion.EMF;
import javax.persistence.EntityManager;

import adrien.faouzi.services.StoreService;
import org.apache.log4j.Logger;

@Named
@ApplicationScoped
public class StoreBean implements Serializable {

    private Store store;

    /**
     * DB data recovery
     */
    public void loadDataStore()
    {
        //DB data recovery
        if(this.store == null)
        {

            //initialize
            Logger log = Logger.getLogger(StoreBean.class);
            log.info("---------------- A");
            EntityManager em = EMF.getEM();
            StoreService storeService = new StoreService();
            log.info("---------------- B");

            try
            {
                //Call of the service that will use the NamedQuery of the "Store" entity
                 this.store = storeService.findStoreByIdStore(1, em);

            }catch(Exception e)
            {
                log.info("Erreur de récupération de données de la page du magasin " + e);
            }
            finally {
                em.close();
            }
        }
    }

    /**
     *getter and setter method
     * @return Store
     */

    public Store getStore()
    {
        this.loadDataStore();
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
