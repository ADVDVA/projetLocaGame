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
        if(orderBy.equals("pegi") || orderBy.equals("productname") || orderBy.equals("enable"))
            asc = !asc;

        if(asc){
            return em.createNamedQuery("PricePlatform.SelectPricePlatformByFilterAsc", Priceplatform.class)
                    .setParameter("researchWord", researchWord.toLowerCase())
                    .setParameter("orderBy", orderBy)
                    //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                    .getResultList();
        }else{
            return em.createNamedQuery("PricePlatform.SelectPricePlatformByFilterDesc", Priceplatform.class)
                    .setParameter("researchWord", researchWord.toLowerCase())
                    .setParameter("orderBy", orderBy)
                    //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                    .getResultList();
        }
    }


    /**
     * PricePlatform make research catalog.
     */
    public Priceplatform findPricePlatformById(int idPricePlatform){
        return em.createNamedQuery("PricePlatform.SelectPricePlatformById", Priceplatform.class)
                .setParameter("idPricePlatform", idPricePlatform)
                .getSingleResult();
    }

}
