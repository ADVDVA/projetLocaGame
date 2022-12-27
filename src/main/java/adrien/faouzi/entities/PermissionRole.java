package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "permissionrole")
public class PermissionRole {
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPermission", nullable = false)
    private Permission idPermission;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idRole", nullable = false)
    private Role idRole;

    public Role getIdRole() {
        return idRole;
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

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    public Permission getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Permission idPermission) {
        this.idPermission = idPermission;
    }
}