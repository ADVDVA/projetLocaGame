package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "permission", indexes = {
        @Index(name = "permissionName", columnList = "permissionName", unique = true)
})
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPermission", nullable = false)
    private int id;

    @Column(name = "permissionName", nullable = false, length = 60)
    private String permissionName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return id == that.id && Objects.equals(permissionName, that.permissionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, permissionName);
    }

    @ManyToMany
    @JoinTable(name = "permissionrole",
            joinColumns = @JoinColumn(name = "idPermission"),
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}