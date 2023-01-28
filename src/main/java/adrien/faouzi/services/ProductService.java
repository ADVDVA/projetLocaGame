package adrien.faouzi.services;

import adrien.faouzi.Interface.IService;
import adrien.faouzi.entities.*;
import adrien.faouzi.projetlocagame.connexion.EMF;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductService implements IService<Product> {

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
    public Product selectProductByIdProduct(int idProduct, EntityManager em)
    {
        return em.createNamedQuery("Product.SelectProductByIdProduct", Product.class)
                .setParameter("idProduct", idProduct)
                .getSingleResult();
    }

    /**
     * PricePlatform make research catalog.
     */
    public List<Product> findProductByFilter(String researchWord, String orderBy, boolean asc, EntityManager em)
    {
        if(orderBy.equals("enable"))
            asc = !asc;

        return em.createNamedQuery("Product.SelectProductByFilter"+
                ((orderBy.equals("id"))? "OrderByNum": "OrderByStr")+
                ((asc)? "Asc": "Desc"),
                Product.class)
                .setParameter("researchWord", researchWord.toLowerCase())
                .setParameter("orderBy", orderBy)
                //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                .getResultList();
    }

    /**
     * get Product from db select by id.
     */
    public Product selectEntityByIdEntity(int idEntity, EntityManager em)
    {
        return selectProductByIdProduct(idEntity, em);
    }

    /**
     * get Product from db select by id.
     */
    public static ProductService instantiate(){
        return new ProductService();
    }

    /**
     * insert Product in db.
     */
    public Product insertProduct(Product product, EntityManager em)
    {
        em.persist(product);
        em.flush();
        return product;
    }

    /**
     * update Product in db.
     */
    public Product updateProduct(Product product, EntityManager em)
    {
        em.merge(product);
        em.flush();
        return product;
    }


    public int getCountOfJoinPricePlatform(int idProduct, EntityManager em){
        return em.createNamedQuery("Product.SelectJoinPricePlatform", Priceplatform.class)
                .setParameter("idProduct", idProduct)
                .getResultList().size();
    }


    public void delete(Product product, EntityManager em){
        if(!em.contains(product))
            product = em.merge(product);
        em.remove(product);
        em.flush();
    }


    /**
     * delete join bitwin product and category (before delete product)
     */
    public void deleteCategoryProductJoinToAProduct(int idProduct, EntityManager em){
        List<Categoryproduct> categoryProducts = em.createNamedQuery("Product.SelectJoinCategoryProduct", Categoryproduct.class)
                .setParameter("idProduct", idProduct)
                .getResultList();
        int i;
        for(i=0; i<categoryProducts.size(); i++){
            if(!em.contains(categoryProducts.get(i)))
                categoryProducts.set(i, em.merge(categoryProducts.get(i)));
            em.remove(categoryProducts.get(i));
        }
        em.flush();
    }


    /**
     * delete join bitwin product and language (before delete product)
     */
    public void deleteLanguageProductJoinToAProduct(int idProduct, EntityManager em){
        List<Languageproduct> languageProducts = em.createNamedQuery("Product.SelectJoinLanguageProduct", Languageproduct.class)
                .setParameter("idProduct", idProduct)
                .getResultList();
        int i;
        for(i=0; i<languageProducts.size(); i++){
            if(!em.contains(languageProducts.get(i)))
                languageProducts.set(i, em.merge(languageProducts.get(i)));
            em.remove(languageProducts.get(i));
        }
        em.flush();
    }

}
