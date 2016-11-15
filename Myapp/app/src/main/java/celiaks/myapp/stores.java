package celiaks.myapp;

/**
 * Created by ck on 01/10/16.
 */

public class stores {
    public stores(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String name;
    public String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
