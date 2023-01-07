package adrien.faouzi.utility;

import org.primefaces.PrimeFaces;

public class TableFilter {

    protected String order = "id";

    protected boolean orderAsc = false;

    public void editOrderTable(String order){

        if(this.order.equals(order)){
            orderAsc = !orderAsc;
        }else{
            orderAsc = true;
            this.order = order;
        }

    }

    public String getOrderIcon(String order){
        if(!this.order.equals(order))
            return "pi pi-circle-off";
        return ((orderAsc)? "pi pi-chevron-circle-down": "pi pi-chevron-circle-up");
    }



    protected String filter = "";

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFilter(){
        return this.filter;
    }



    private int idRedirection;
    private char modeRedirection;

    public int getIdRedirection(){
        return this.idRedirection;
    }
    public char getModeRedirection() { return this.modeRedirection; }

    //redirect page with sending id of row click.
    public String redirectPageWidthId(String url, int id){
        this.idRedirection = id;
        return url;
    }


    //redirect page with sending id of row click AND mode (c,r,u,d).
    public String redirectPageWidthId(String url, int id, char mode){
        this.modeRedirection = mode;
        return this.redirectPageWidthId(url, id);
    }



    //call JS for apply class research word in a table list.
    public void applyResearchWordClass(){
        PrimeFaces.current().executeScript("applyClassWordResearch(\""+this.filter+"\")");
    }

}
