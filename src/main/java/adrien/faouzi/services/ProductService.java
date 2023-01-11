package adrien.faouzi.services;

import adrien.faouzi.entities.Product;
import adrien.faouzi.entities.Store;
import adrien.faouzi.projetlocagame.connexion.EMF;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductService{

    /**
     * get all product from db.
     */
    public List<Product> selectProductAll(EntityManager em)
    {
        return em.createNamedQuery("Product.SelectProductAll", Product.class)
                .getResultList();
    }

    /**
     * get one product from db, selected by id.
     */
    public Product selectProductByIdProduct(int idProduct, EntityManager em){
        return em.createNamedQuery("Product.SelectProductByIdProduct", Product.class)
                .setParameter("idProduct", idProduct)
                .getSingleResult();
    }
}
