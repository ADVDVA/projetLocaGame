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



    private String order = "Id";
    private boolean orderAsc = true;

    public void editOrderTableProduct(String order){

        if(this.order.equals(order)){
            orderAsc = !orderAsc;
        }else{
            orderAsc = true;
            this.order = order;
        }

    }

    public String getOrderIcon(String order){
        if(!this.order.equals(order))
            return "pi pi-circle-off";
        return ((orderAsc)? "pi pi-chevron-circle-down": "pi pi-chevron-circle-up");
    }
}