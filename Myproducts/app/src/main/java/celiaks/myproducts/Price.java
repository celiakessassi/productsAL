package celiaks.myproducts;

/**
 * Created by ck on 03/10/16.
 */

public class Price {
    public String price;
    public String store;
    public String date;
    public String product;

    public Price(String price, String store, String date, String product) {
        this.price = price;
        this.store = store;
        this.date = date;
    }

    public String getProduct() {
        return price;
    }

    public void setProduct(String price) {
        this.price = price;
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
}
