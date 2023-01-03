package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Product;
import adrien.faouzi.enumeration.Pegi;
import adrien.faouzi.utility.UtilityProcessing;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named
@ManagedBean
@ApplicationScoped
public class ProductBean  implements Serializable {

    //all products DB in application scope.
    private static List<Product> products;

    public static List<Product> getProducts(){
        if(products==null)
            initialiseProducts();
        return products;
    }

    //get all row from DB (Application scope).
    private static void initialiseProducts(){
        products = new ArrayList<>();
        products.add(new Product());
        products.get(0).setId(1);
        products.get(0).setProductName("Mario Kart 7");
        products.get(0).setPegi(Pegi.TROIS);
        products.get(0).setReleaseDate(LocalDateTime.now());
        products.get(0).setEnable(false);
        products.add(new Product());
        products.get(1).setId(2);
        products.get(1).setProductName("Resident Evil 6");
        products.get(1).setPegi(Pegi.DIXHUIT);
        products.get(1).setReleaseDate(LocalDateTime.now());
        products.get(1).setEnable(false);
        products.add(new Product());
        products.get(2).setId(3);
        products.get(2).setProductName("Call of Duty");
        products.get(2).setPegi(Pegi.DIXHUIT);
        products.get(2).setReleaseDate(LocalDateTime.now());
        products.get(2).setEnable(true);
    }
}
