package adrien.faouzi.services;

import adrien.faouzi.entities.Category;
import adrien.faouzi.entities.Categoryproduct;
import adrien.faouzi.entities.Product;
import adrien.faouzi.entities.Store;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.utility.UtilityProcessing;

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


    /**
     * get category from db with research filter.
     */
    public List<Category> findCategoryByFilter(String researchWord, String orderBy, boolean asc, EntityManager em)
    {
        return em.createNamedQuery("Category.SelectCategoryByFilter"+
                ((orderBy.equals("id"))? "OrderByNum": "OrderByStr")+
                ((asc)? "Asc": "Desc"),
                Category.class)
                .setParameter("researchWord", researchWord.toLowerCase())
                .setParameter("orderBy", orderBy)
                //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                .getResultList();
    }

    public int getCountOfJoin(int idCategory, EntityManager em){
        return em.createNamedQuery("Category.SelectJoin", Categoryproduct.class)
                .setParameter("idCategory", idCategory)
                .getResultList().size();
    }

    public void delete(Category category, EntityManager em){
        if(!em.contains(category))
            category = em.merge(category);
        em.remove(category);
        em.flush();
    }


}
