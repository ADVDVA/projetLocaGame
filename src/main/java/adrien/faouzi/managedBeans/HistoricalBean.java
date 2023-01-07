package adrien.faouzi.managedBeans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class HistoricalBean implements Serializable {

    private String[] historic;
    private int index;

    @PostConstruct
    public void init(){
        this.historic = new String[2];
        this.historic[0] = "";
        this.historic[1] = "";
        this.index = 0;
    }

    public void saveNewPageHistoric(String url){
        historic[++index%2]=url;
    }

    public String backLastPageHistoric(){
        return historic[index++%2];
    }



}
