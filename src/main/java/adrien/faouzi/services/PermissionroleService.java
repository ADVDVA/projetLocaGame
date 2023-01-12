package adrien.faouzi.services;

import adrien.faouzi.entities.Permissionrole;
import adrien.faouzi.entities.User;

import javax.persistence.EntityManager;
import java.util.List;

public class PermissionroleService
{
    /**
     * Permissionrole request method by idRole
     * @param idUser
     * @return
     */
    public List<Permissionrole> findPermissionRoleByIdRole(int idRole, EntityManager em)
    {
        return em.createNamedQuery("Permissionrole.selectPermissionRoleByIdRole", Permissionrole.class)
                .setParameter("idRole", idRole)
                .getResultList();
    }

}
