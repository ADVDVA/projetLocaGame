package adrien.faouzi.services;

import adrien.faouzi.entities.Priceplatform;
import adrien.faouzi.entities.Store;
import adrien.faouzi.projetlocagame.connexion.EMF;

import java.util.List;

public class PricePlatformService extends EMF
{

    /**
     * PricePlatform make research catalog.
     */
    public List<Priceplatform> findPricePlatformByFilter(String researchWord, String orderBy, boolean asc)
    {
        return em.createNamedQuery("Priceplatform.SelectPricePlatformByFilter", Priceplatform.class)
                .setParameter("researchWord", researchWord)
                //.setParameter("orderBy", orderBy)
                //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                .getResultList();
    }
}
