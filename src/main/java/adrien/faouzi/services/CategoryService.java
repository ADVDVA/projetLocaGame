package adrien.faouzi.services;

import adrien.faouzi.entities.Category;
import adrien.faouzi.entities.Store;
import adrien.faouzi.projetlocagame.connexion.EMF;

import java.util.List;

public class CategoryService extends EMF {

    /**
     * get all category assign to an id product.
     */
    public List<Category> findCategoryByIdProduct(int idProduct)
    {
        return em.createNamedQuery("Category.SelectCategoryByIdProduct", Category.class)
                .setParameter("idProduct", idProduct)
                .getResultList();
    }

}
