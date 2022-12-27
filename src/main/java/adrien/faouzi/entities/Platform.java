package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.*;

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
    private List<PricePlatform> pricePlatforms = new ArrayList<>();

    @OneToMany(mappedBy = "idPricePlatform")
    private List<Copy> copies = new ArrayList<>();

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

    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    public List<PricePlatform> getPricePlatforms() {
        return pricePlatforms;
    }

    public void setPricePlatforms(List<PricePlatform> pricePlatforms) {
        this.pricePlatforms = pricePlatforms;
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