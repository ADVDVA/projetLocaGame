package adrien.faouzi.services;

import adrien.faouzi.entities.City;
import adrien.faouzi.projetlocagame.connexion.EMF;

import java.util.List;

public class CityService extends EMF {

    /**
     *  City request method by Postal Code
     * @param postalCode
     * @return
     */
    public List<City> findCityByPostalCode(int postalCode)
    {
        return em.createNamedQuery("City.SelectCityByPostalCode", City.class)
                .setParameter("postalCode", postalCode)
                .getResultList();
    }
}
