package adrien.faouzi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Platform")
public class Platform implements Serializable {
    private int idPlatform;
    private String platformName;
    private List<Copy> copiesByIdPlatform;
    private List<PricePlatform> priceplatformsByIdPlatform;

    @Id
    @Column(name = "idPlatform")
    public int getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(int idPlatform) {
        this.idPlatform = idPlatform;
    }

    @Basic
    @Column(name = "platformName")
    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platform platform = (Platform) o;
        return idPlatform == platform.idPlatform && Objects.equals(platformName, platform.platformName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlatform, platformName);
    }

    @OneToMany(mappedBy = "platformByIdPricePlatform")
    public List<Copy> getCopiesByIdPlatform() {
        return copiesByIdPlatform;
    }

    public void setCopiesByIdPlatform(List<Copy> copiesByIdPlatform) {
        this.copiesByIdPlatform = copiesByIdPlatform;
    }

    @OneToMany(mappedBy = "platformByIdPlatform")
    public List<PricePlatform> getPriceplatformsByIdPlatform() {
        return priceplatformsByIdPlatform;
    }

    public void setPriceplatformsByIdPlatform(List<PricePlatform> priceplatformsByIdPlatform) {
        this.priceplatformsByIdPlatform = priceplatformsByIdPlatform;
    }
}
