package adrien.faouzi.services;

import adrien.faouzi.entities.Store;
import adrien.faouzi.projetlocagame.connexion.EMF;

import javax.persistence.EntityManager;

public class StoreService extends EMF
{
    /**
     * Store recovery method
     */
    public Store findStoreByIdStore(int id)
    {
        return em.createNamedQuery("Store.SelectStoreByIdStore", Store.class)
                .setParameter("idStore", id)
                .getSingleResult();
    }
}
