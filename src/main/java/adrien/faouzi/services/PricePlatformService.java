package adrien.faouzi.services;

import adrien.faouzi.entities.Priceplatform;
import adrien.faouzi.entities.Product;
import adrien.faouzi.entities.Store;
import adrien.faouzi.projetlocagame.connexion.EMF;

import javax.persistence.EntityManager;
import java.util.List;

public class PricePlatformService
{

    /**
     * PricePlatform make research catalog.
     */
    public List<Priceplatform> findPricePlatformByFilter(String researchWord, String orderBy, boolean asc, EntityManager em)
    {
        if(orderBy.equals("editor") || orderBy.equals("pegi") || orderBy.equals("enable"))
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
    public Priceplatform findPricePlatformById(int idPricePlatform, EntityManager em){
        return em.createNamedQuery("PricePlatform.SelectPricePlatformById", Priceplatform.class)
                .setParameter("idPricePlatform", idPricePlatform)
                .getSingleResult();
    }


    public int getCountOfJoinCopy(int idPricePlatform, EntityManager em){
        return em.createNamedQuery("PricePlatform.SelectJoinCopy", Priceplatform.class)
                .setParameter("idPricePlatform", idPricePlatform)
                .getResultList().size();
    }


    public void delete(Priceplatform pricePlatform, EntityManager em){
        if(!em.contains(pricePlatform))
            pricePlatform = em.merge(pricePlatform);
        em.remove(pricePlatform);
        em.flush();
    }

}
