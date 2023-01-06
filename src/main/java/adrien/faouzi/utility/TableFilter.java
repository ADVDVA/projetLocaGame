package adrien.faouzi.utility;

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

    public int getIdRedirection(){
        return this.idRedirection;
    }

    //redirect page with sending id of row click.
    public String redirectPageWidthId(String url, int id){
        this.idRedirection = id;
        return url;
    }

}
