package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Permissionrole", indexes = {
        @Index(name = "idPermission", columnList = "idPermission"),
        @Index(name = "idRole", columnList = "idRole")
})
public class PermissionRole {
    private int idPermission;
    private int idRole;
    private Permission permissionByIdPermission;
    private Role roleByIdRole;

    @Basic
    @Column(name = "idPermission")
    public int getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(int idPermission) {
        this.idPermission = idPermission;
    }

    @Basic
    @Column(name = "idRole")
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionRole that = (PermissionRole) o;
        return idPermission == that.idPermission && idRole == that.idRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPermission, idRole);
    }

    @ManyToOne
    @JoinColumn(name = "idPermission", referencedColumnName = "idPermission", nullable = false)
    public Permission getPermissionByIdPermission() {
        return permissionByIdPermission;
    }

    public void setPermissionByIdPermission(Permission permissionByIdPermission) {
        this.permissionByIdPermission = permissionByIdPermission;
    }

    @ManyToOne
    @JoinColumn(name = "idRole", referencedColumnName = "idRole", nullable = false)
    public Role getRoleByIdRole() {
        return roleByIdRole;
    }

    public void setRoleByIdRole(Role roleByIdRole) {
        this.roleByIdRole = roleByIdRole;
    }
}
