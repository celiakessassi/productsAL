package celiaks.myproducts;

/**
 * Created by ck on 01/10/16.
 */

public class Store {
   public String name;
   public String address;

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
