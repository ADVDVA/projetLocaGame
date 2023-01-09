package adrien.faouzi.services;

import adrien.faouzi.entities.Role;
import adrien.faouzi.entities.User;
import adrien.faouzi.projetlocagame.connexion.EMF;

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
}
