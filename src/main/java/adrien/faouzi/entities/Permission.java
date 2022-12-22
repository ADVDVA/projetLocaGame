package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Permission {
    private int idPermission;
    private String permissionName;
    private List<Permissionrole> permissionrolesByIdPermission;

    @Id
    @Column(name = "idPermission")
    public int getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(int idPermission) {
        this.idPermission = idPermission;
    }

    @Basic
    @Column(name = "permissionName")
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return idPermission == that.idPermission && Objects.equals(permissionName, that.permissionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPermission, permissionName);
    }

    @OneToMany(mappedBy = "permissionByIdPermission")
    public List<Permissionrole> getPermissionrolesByIdPermission() {
        return permissionrolesByIdPermission;
    }

    public void setPermissionrolesByIdPermission(List<Permissionrole> permissionrolesByIdPermission) {
        this.permissionrolesByIdPermission = permissionrolesByIdPermission;
    }
}
