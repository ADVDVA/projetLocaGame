package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Platform;
import adrien.faouzi.entities.Product;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.CategoryService;
import adrien.faouzi.services.LanguageGameService;
import adrien.faouzi.utility.UtilityProcessing;
import sun.plugin.services.PlatformService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class ProductStaticBean {

    public static void initListCategory(Product product){

        EntityManager em = EMF.getEM();
        CategoryService categoryService = new CategoryService();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            product.setListCategory(
                    categoryService.findCategoryByIdProduct(product.getId(),em)
            );
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            product.setListCategory(new ArrayList<>());

        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }

    }


    public static void initListLanguageGame(Product product){

        EntityManager em = EMF.getEM();
        LanguageGameService languageGameService = new LanguageGameService();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            product.setListLanguageGame(
                    languageGameService.findLanguageGameByIdProduct(product.getId(), em)
            );
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            product.setListCategory(new ArrayList<>());

        }finally{
            if(transaction.isActive())
            transaction.rollback();
            em.close();
        }

    }

}
