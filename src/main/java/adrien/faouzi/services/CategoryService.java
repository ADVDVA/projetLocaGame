package adrien.faouzi.services;

import adrien.faouzi.entities.Category;
import adrien.faouzi.entities.Product;
import adrien.faouzi.entities.Store;
import adrien.faouzi.projetlocagame.connexion.EMF;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryService {

    /**
     * get all category assign to an id product.
     */
    public List<Category> findCategoryByIdProduct(int idProduct, EntityManager em)
    {
        return em.createNamedQuery("Category.SelectCategoryByIdProduct", Category.class)
                .setParameter("idProduct", idProduct)
                .getResultList();
    }


    /**
     * get all category from db.
     */
    public List<Category> selectCategoryAll(EntityManager em)
    {
        return em.createNamedQuery("Category.SelectCategoryAll", Category.class)
                .getResultList();
    }


    /**
     * get category selected by id.
     */
    public Category selectCategoryByIdCategory(int idCategory, EntityManager em)
    {
        return em.createNamedQuery("Category.SelectCategoryByIdCategory", Category.class)
                .setParameter("idCategory", idCategory)
                .getSingleResult();
    }


    /**
     * insert Category in db.
     */
    public Category insertCategory(Category category, EntityManager em)
    {
        em.persist(category);
        em.flush();
        return category;
    }


    /**
     * update Category in db.
     */
    public Category updateCategory(Category category, EntityManager em)
    {
        em.merge(category);
        em.flush();
        return category;
    }


}
