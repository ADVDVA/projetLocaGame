package adrien.faouzi.services;

import adrien.faouzi.entities.Category;
import adrien.faouzi.entities.Languagegame;
import adrien.faouzi.projetlocagame.connexion.EMF;

import java.util.List;

public class LanguageGameService extends EMF {

    /**
     * get all language game assign to an id product.
     */
    public List<Languagegame> findLanguageGameByIdProduct(int idProduct)
    {
        return em.createNamedQuery("LanguageGame.SelectLanguageGameByIdProduct", Languagegame.class)
                .setParameter("idProduct", idProduct)
                .getResultList();
    }
}
