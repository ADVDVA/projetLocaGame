package adrien.faouzi.services;

import adrien.faouzi.entities.City;
import adrien.faouzi.entities.Country;
import adrien.faouzi.projetlocagame.connexion.EMF;

import java.util.List;

public class CountryService extends EMF
{
    /**
     *  Country global request method
     * @return
     */
    public List<Country> findCountryAll()
    {
        return em.createNamedQuery("Country.SelectCountryAll", Country.class)
                .getResultList();
    }
}
