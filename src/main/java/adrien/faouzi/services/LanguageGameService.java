package adrien.faouzi.services;

import adrien.faouzi.entities.Category;
import adrien.faouzi.entities.Languagegame;
import adrien.faouzi.projetlocagame.connexion.EMF;

import javax.persistence.EntityManager;
import java.util.List;

public class LanguageGameService{

    /**
     * get all language game assign to an id product.
     */
    public List<Languagegame> findLanguageGameByIdProduct(int idProduct, EntityManager em)
    {
        return em.createNamedQuery("LanguageGame.SelectLanguageGameByIdProduct", Languagegame.class)
                .setParameter("idProduct", idProduct)
                .getResultList();
    }


    /**
     * get all language game from db.
     */
    public List<Languagegame> selectLanguageGameAll(EntityManager em)
    {
        return em.createNamedQuery("LanguageGame.SelectLanguageGameAll", Languagegame.class)
                .getResultList();
    }


    /**
     * get a language game from db selected by id language game.
     */
    public Languagegame selectLanguageByIdLanguage(int idLanguage, EntityManager em)
    {
        return em.createNamedQuery("LanguageGame.SelectLanguageGameByIdLanguage", Languagegame.class)
                .setParameter("idLanguage", idLanguage)
                .getSingleResult();
    }


}
