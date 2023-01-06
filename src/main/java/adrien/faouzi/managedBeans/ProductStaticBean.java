package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Platform;
import adrien.faouzi.entities.Product;
import adrien.faouzi.services.CategoryService;
import adrien.faouzi.services.LanguageGameService;
import adrien.faouzi.utility.UtilityProcessing;
import sun.plugin.services.PlatformService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class ProductStaticBean {

    public static void initListCategory(Product product){

        CategoryService categoryService = new CategoryService();
        EntityTransaction transaction = categoryService.getTransaction();

        try{
            transaction.begin();
            product.setListCategory(
                    categoryService.findCategoryByIdProduct(product.getId())
            );
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            product.setListCategory(new ArrayList<>());
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            categoryService.close();
        }

    }


    public static void initListLanguageGame(Product product){

        LanguageGameService languageGameService = new LanguageGameService();
        EntityTransaction transaction = languageGameService.getTransaction();

        try{
            transaction.begin();
            product.setListLanguageGame(
                    languageGameService.findLanguageGameByIdProduct(product.getId())
            );
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            product.setListCategory(new ArrayList<>());
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            languageGameService.close();
        }

    }

}
