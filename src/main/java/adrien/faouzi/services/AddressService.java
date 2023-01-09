package adrien.faouzi.services;

import adrien.faouzi.entities.Address;
import adrien.faouzi.projetlocagame.connexion.EMF;

public class AddressService extends EMF
{
    /**
     * Add address in DB method
     */
    public void addAddress(Address address)
    {
        em.persist(address);
    }
}
