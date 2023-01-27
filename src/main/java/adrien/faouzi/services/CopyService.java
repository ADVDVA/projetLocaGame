package adrien.faouzi.services;

import adrien.faouzi.entities.Copy;
import adrien.faouzi.entities.Editor;
import adrien.faouzi.entities.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class CopyService {

    /**
     * select one copy from db, selected by id.
     * @param idCopy id of copy research in db.
     * @param em entity manager for aces to db.
     * @return one instance of copy.
     */
    public Copy selectCopyByIdCopy(int idCopy, EntityManager em)
    {
        return em.createNamedQuery("Copy.SelectCopyByIdCopy", Copy.class)
                .setParameter("idCopy", idCopy)
                .getSingleResult();
    }

    /**
     * insert Copy in db.
     */
    public Copy insertCopy(Copy copy, EntityManager em)
    {
        em.persist(copy);
        em.flush();
        return copy;
    }


    /**
     * update Copy in db.
     */
    public Copy updateCopy(Copy copy, EntityManager em)
    {
        em.merge(copy);
        em.flush();
        return copy;
    }


    /**
     * get copy from db with research filter.
     */
    public List<Copy> findCopyByFilter(String researchWord, String orderBy, boolean asc, EntityManager em)
    {
        if(asc){
            return em.createNamedQuery("Copy.SelectCopyByFilterAsc", Copy.class)
                    .setParameter("researchWord", researchWord.toLowerCase())
                    .setParameter("orderBy", orderBy)
                    //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                    .getResultList();
        }else{
            return em.createNamedQuery("Copy.SelectCopyByFilterDesc", Copy.class)
                    .setParameter("researchWord", researchWord.toLowerCase())
                    .setParameter("orderBy", orderBy)
                    //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                    .getResultList();
        }
    }


    /**
     * count every join to a copy, for know if this copy can be deleted.
     */
    public int getCountOfJoin(int idCopy, EntityManager em){
        return 0;
    }


    public void delete(Copy copy, EntityManager em){
        if(!em.contains(copy))
            copy = em.merge(copy);
        em.remove(copy);
        em.flush();
    }


    public int getCountCopy(Copy copy, EntityManager em){
        if(copy.getId()==0 || copy.getIdPricePlatform()==null)
            return 0;
        return em.createNamedQuery("Copy.SelectCountCopy", Copy.class)
                .setParameter("idCopy", copy.getId())
                .setParameter("idPricePlatform", copy.getIdPricePlatform().getId())
                .getResultList().size();
    }


    /**
     * insert Copy in db.
     */
    public Copy insert(Copy copy, EntityManager em)
    {
        em.persist(copy);
        em.flush();
        return copy;
    }



    /**
     * update Copy in db.
     */
    public Copy update(Copy copy, EntityManager em)
    {
        em.merge(copy);
        em.flush();
        return copy;
    }

}
