package adrien.faouzi.services;

import adrien.faouzi.entities.User;
import adrien.faouzi.projetlocagame.connexion.EMF;

public class UserService extends EMF
{
    /**
     * User request method by email and password
     * @param mail
     * @return
     */
    public User findUserByMailAndPassword(String mail)
    {
        return em.createNamedQuery("User.SelectUserConnexion", User.class)
                .setParameter("mail", mail)
                .getSingleResult();
    }
}
