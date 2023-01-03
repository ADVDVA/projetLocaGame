package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Product;
import adrien.faouzi.utility.TableFilter;
import adrien.faouzi.utility.UtilityProcessing;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

@Named
@SessionScoped
public class ProductSessionBean extends TableFilter implements Serializable {

    //all products filtered by user, session scope.
    private List<Product> productsFiltered;

    public List<Product> getProductsFiltered(){
        return productsFiltered;
    }

    // do SQL research (width filter, order by, pagination managed by PrimeFaces).
    public void initialiseProductsFiltered(){
        productsFiltered = ProductBean.getProducts();

        UtilityProcessing.debug("actualiseResearch");

        //SQL.
        //select * from *Product*
        //where (
        // *all param == this.filter*
        //)
        //order by this.order (orderAsc? "asc": "desc")
    }

}