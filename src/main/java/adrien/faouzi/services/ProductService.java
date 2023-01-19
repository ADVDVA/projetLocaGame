package adrien.faouzi.services;

import adrien.faouzi.entities.Priceplatform;
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

    /**
     * PricePlatform make research catalog.
     */
    public List<Product> findProductByFilter(String researchWord, String orderBy, boolean asc, EntityManager em)
    {
        if(orderBy.equals("pegi") || orderBy.equals("enable"))
            asc = !asc;

        if(asc){
            return em.createNamedQuery("Product.SelectProductByFilterAsc", Product.class)
                    .setParameter("researchWord", researchWord.toLowerCase())
                    .setParameter("orderBy", orderBy)
                    //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                    .getResultList();
        }else{
            return em.createNamedQuery("Product.SelectProductByFilterDesc", Product.class)
                    .setParameter("researchWord", researchWord.toLowerCase())
                    .setParameter("orderBy", orderBy)
                    //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                    .getResultList();
        }
    }

}
