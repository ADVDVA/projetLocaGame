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
    public User findUserByMail(String mail)
    {
        return em.createNamedQuery("User.SelectUserConnexion", User.class)
                .setParameter("mail", mail)
                .getSingleResult();
    }


    /**
     * Add user in DB method
     */
    public User addUser(User user)
    {
        em.persist(user);
        em.flush();
        return user;
    }

}
