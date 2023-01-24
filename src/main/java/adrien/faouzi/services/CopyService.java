package adrien.faouzi.services;

import adrien.faouzi.entities.Copy;
import adrien.faouzi.entities.Editor;

import javax.persistence.EntityManager;

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

}
