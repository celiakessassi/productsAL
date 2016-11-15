package celiaks.myapp;


/**
 * Created by ck on 01/10/16.
 */

public class Products {
    public String name ;
    public int price ;
    public int IMG ;
    //  public Object store ;
    public String cu ;
    public String date ;

    public Products(String name, int price, String cu, String date) {
        this.name = name;
        this.price = price;
        this.cu = cu;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //public Object getStore() {
        //return store;
    //}

    //public void setStore(Object store) {
        //this.store = store;
    //}

    public String getCu() {
        return cu;
    }

    public void setCu(String cu) {
        this.cu = cu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
