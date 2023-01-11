package adrien.faouzi.services;

import adrien.faouzi.entities.Store;
import adrien.faouzi.projetlocagame.connexion.EMF;

import javax.persistence.EntityManager;

public class StoreService
{
    /**
     * Store recovery method
     */
    public Store findStoreByIdStore(int id, EntityManager em)
    {
        return em.createNamedQuery("Store.SelectStoreByIdStore", Store.class)
                .setParameter("idStore", id)
                .getSingleResult();
    }
}
