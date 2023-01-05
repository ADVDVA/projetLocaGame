package adrien.faouzi.managedBeans;

import org.primefaces.model.ResponsiveOption;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class HomePageBean implements Serializable {

    private List<String> imagesCarousel;
    public List<String> getImagesCarousel(){
        return this.imagesCarousel;
    }

    private List<ResponsiveOption> responsiveOptions;
    public List<ResponsiveOption> getResponsiveOptions() {
        return responsiveOptions;
    }

    @PostConstruct
    private void initialize(){
        imagesCarousel = new ArrayList<>();
        imagesCarousel.add("../images/ImagesCarousel01.jpg");
        imagesCarousel.add("../images/ImagesCarousel02.jpg");
        imagesCarousel.add("../images/ImagesCarousel03.jpg");

        responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new ResponsiveOption("1024px", 1, 1));
    }

}
