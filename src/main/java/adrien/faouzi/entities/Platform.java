package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "platform")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlatform", nullable = false)
    private int id;

    @Column(name = "platformName", nullable = false, length = 60)
    private String platformName;

    @OneToMany(mappedBy = "idPlatform")
    private Set<Priceplatform> priceplatforms = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platform platform = (Platform) o;
        return id == platform.id && Objects.equals(platformName, platform.platformName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, platformName);
    }

    public Set<Priceplatform> getPriceplatforms() {
        return priceplatforms;
    }

    public void setPriceplatforms(Set<Priceplatform> priceplatforms) {
        this.priceplatforms = priceplatforms;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}