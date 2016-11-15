package celiaks.apppr;

/**
 * Created by ck on 03/10/16.
 */

public class Product {
    String id ;
    String name ;
    Price price ;
    String address ;
    String format;
    String content;
    String description;

    String cu;

    //   Add item to adapter
    public Product(String id, String name,Price price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.address = address;
        this.cu = cu;
        this.format = format;
        this.content = content;
        this.description = description;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
