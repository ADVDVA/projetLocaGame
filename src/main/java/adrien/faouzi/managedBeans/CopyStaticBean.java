package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Copy;
import adrien.faouzi.entities.Product;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.CategoryService;
import adrien.faouzi.services.CopyService;
import adrien.faouzi.utility.UtilityProcessing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class CopyStaticBean {

    public static int getCountCopy(Copy copy){
        int output;

        EntityManager em = EMF.getEM();
        CopyService copyService = new CopyService();
        try{
            output = copyService.getCountCopy(copy, em);
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            output = -1;
        }finally{
            em.close();
        }

        return output;

    }

}
