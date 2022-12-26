package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Role")
public class Role {
    private int idRole;
    private String roleName;
    private List<PermissionRole> permissionrolesByIdRole;
    private List<User> usersByIdRole;

    @Id
    @Column(name = "idRole")
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Basic
    @Column(name = "roleName")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return idRole == role.idRole && Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, roleName);
    }

    @OneToMany(mappedBy = "roleByIdRole")
    public List<PermissionRole> getPermissionrolesByIdRole() {
        return permissionrolesByIdRole;
    }

    public void setPermissionrolesByIdRole(List<PermissionRole> permissionrolesByIdRole) {
        this.permissionrolesByIdRole = permissionrolesByIdRole;
    }

    @OneToMany(mappedBy = "roleByIdRole")
    public List<User> getUsersByIdRole() {
        return usersByIdRole;
    }

    public void setUsersByIdRole(List<User> usersByIdRole) {
        this.usersByIdRole = usersByIdRole;
    }
}
