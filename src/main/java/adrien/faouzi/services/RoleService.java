package adrien.faouzi.services;

import adrien.faouzi.entities.Role;
import adrien.faouzi.projetlocagame.connexion.EMF;

import java.util.List;

public class RoleService extends EMF
{
    /**
     * Role request method by roleName
     * @param roleName
     * @return
     */
    public Role findRoleByRoleName(String roleName)
    {
        return em.createNamedQuery("Role.SelectRoleByRoleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }

    /**
     * Role request method for all
     */
    public List<Role> findRoleAll ()
    {
        return em.createNamedQuery("Role.SelectRoleAll", Role.class)
                .getResultList();
    }
}
