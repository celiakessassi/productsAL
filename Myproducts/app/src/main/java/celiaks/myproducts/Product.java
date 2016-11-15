package celiaks.myproducts;

/**
 * Created by ck on 02/10/16.
 */

public class Product {
    public String id;
    public String name;
    public String price;
    public String store;
    public String date;
    public String cu;

    public Product(String id, String name, String price, String store, String date, String cu) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.store = store;
        this.date = date;
        this.cu = cu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCu() {
        return cu;
    }

    public void setCu(String cu) {
        this.cu = cu;
    }
}
