package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Product;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.ProductService;
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
public class ProductListBean extends TableFilter implements Serializable {

    //product filtered.
    private List<Product> productFiltered;

    public List<Product> getProductFiltered(){
        return this.productFiltered;
    }
    public void setProductFiltered(List<Product> productFiltered){
        this.productFiltered = productFiltered;
    }

    public void doResearch(){

        EntityManager em = EMF.getEM();
        ProductService productService = new ProductService();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            productFiltered = productService.findProductByFilter(this.filter, this.order, this.orderAsc, em);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            productFiltered = new ArrayList<>();
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }

    }

}
