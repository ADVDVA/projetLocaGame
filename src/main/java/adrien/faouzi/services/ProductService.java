package adrien.faouzi.services;

import adrien.faouzi.entities.Product;
import adrien.faouzi.entities.Store;
import adrien.faouzi.projetlocagame.connexion.EMF;

import java.util.List;

public class ProductService extends EMF {

    /**
     * get all product from db.
     */
    public List<Product> selectProductAll()
    {
        return em.createNamedQuery("Product.SelectProductAll", Product.class)
                .getResultList();
    }

    /**
     * get one product from db, selected by id.
     */
    public Product selectProductByIdProduct(int idProduct){
        return em.createNamedQuery("Product.SelectProductByIdProduct", Product.class)
                .setParameter("idProduct", idProduct)
                .getSingleResult();
    }
}
