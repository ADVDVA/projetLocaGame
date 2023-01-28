package adrien.faouzi.services;

import adrien.faouzi.entities.*;
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
        if(orderBy.equals("enable")) //reverse bool and order like a string ("false" > "true")
            asc = !asc;

        return em.createNamedQuery("PricePlatform.SelectPricePlatformByFilter"+
                ((orderBy.equals("id") || orderBy.equals("rentalprice"))? "OrderByNum": "OrderByStr")+ //force 2 type query (order string and order number).
                ((asc)? "Asc": "Desc"), //force 2 type query (asc and desc).
                Priceplatform.class)
                .setParameter("researchWord", researchWord.toLowerCase())
                .setParameter("orderBy", orderBy)
                //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                .getResultList();
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
        return em.createNamedQuery("PricePlatform.SelectJoinCopy", Copy.class)
                .setParameter("idPricePlatform", idPricePlatform)
                .getResultList().size();
    }


    public void delete(Priceplatform pricePlatform, EntityManager em){
        if(!em.contains(pricePlatform))
            pricePlatform = em.merge(pricePlatform);
        em.remove(pricePlatform);
        em.flush();
    }


    /**
     * get all pricePlatform from db.
     * @param em entity manager for aces to db.
     * @return list of all pricePlatform from db.
     */
    public List<Priceplatform> selectPricePlatformAll(EntityManager em)
    {
        return em.createNamedQuery("PricePlatform.SelectPricePlatformAll", Priceplatform.class)
                .getResultList();
    }


    /**
     * insert PricePlatform in db.
     */
    public Priceplatform insert(Priceplatform pricePlatform, EntityManager em)
    {
        em.persist(pricePlatform);
        em.flush();
        return pricePlatform;
    }


    /**
     * update PricePlatform in db.
     */
    public Priceplatform update(Priceplatform pricePlatform, EntityManager em)
    {
        em.merge(pricePlatform);
        em.flush();
        return pricePlatform;
    }

}
