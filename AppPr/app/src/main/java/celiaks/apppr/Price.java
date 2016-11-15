package celiaks.apppr;

/**
 * Created by ck on 03/10/16.
 */

public class Price {
    String id;
    String name;
    String price;
    String address;
    String date;
    Product product;
    String product_id;
    String cu;

    public Price(String id, String price, String address) {
        this.id = id;
        this.price = price;
        this.address = address;
        this.date = date;
    }

    public String getCu() {
        return cu;
    }

    public void setCu(String cu) {
        this.cu = cu;
    }
}
