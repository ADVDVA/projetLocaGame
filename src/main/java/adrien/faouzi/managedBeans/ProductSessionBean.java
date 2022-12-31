package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductSessionBean implements Serializable {

    //all products filtered by user, session scope.
    private List<Product> productsFiltered;

    public List<Product> getProductsFiltered(){
        if(productsFiltered==null)
            initialiseProductsFiltered();
        return productsFiltered;
    }

    public void initialiseProductsFiltered(){
        productsFiltered = ProductBean.getProducts();
    }


    private String orderFiltered = "Id";

    public void setOrderFiltered(String orderFiltered){
        this.orderFiltered = orderFiltered;

        //reload table catalogue. !!! a faire !!!
    }

    public String orderFilteredLogoOrder(String columnName){
        return (isOrderFilteredOrder(columnName)? "⌄": "");
    }
    public boolean isOrderFilteredOrder(String columnName){
        return orderFiltered.equals(columnName);
    }

}