package adrien.faouzi.services;

import adrien.faouzi.entities.*;

import javax.persistence.EntityManager;

public class LanguageProductService {

    /**
     * insert join LanguageProductService in db.
     */
    public Languageproduct insertLanguageProduct(Languagegame language, Product product, EntityManager em)
    {
        //make join.
        Languageproduct languageProduct = new Languageproduct();
        languageProduct.setIdLanguage(language);
        languageProduct.setIdProduct(product);

        //do insert.
        em.persist(languageProduct);
        em.flush();
        return languageProduct;
    }

    /**
     * delete join LanguageProductService in db.
     */
    public void deleteLanguageProduct(Languagegame language, Product product, EntityManager em)
    {
        em.createNamedQuery("LanguageProduct.deleteLanguageProduct")
                .setParameter("idLanguage", language.getId())
                .setParameter("idProduct", product.getId())
                .executeUpdate();
    }

}
