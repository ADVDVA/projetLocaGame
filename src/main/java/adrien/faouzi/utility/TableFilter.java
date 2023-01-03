package adrien.faouzi.utility;

public class TableFilter {

    private String order = "Id";
    private boolean orderAsc = true;

    public void editOrderTableProduct(String order){

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



    private String filter = "";

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFilter(){
        return this.filter;
    }


}
