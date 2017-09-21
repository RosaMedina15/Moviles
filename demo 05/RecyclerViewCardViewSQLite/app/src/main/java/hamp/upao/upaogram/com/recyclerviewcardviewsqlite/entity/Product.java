package hamp.upao.upaogram.com.recyclerviewcardviewsqlite.entity;

/**
 * Created by hamp on 19/09/2017.
 */

public class Product {
    private	Integer	id;
    private	String name;
    private	Integer	quantity;
    private String image;

    public Product() {

    }

    public Product(String name, Integer quantity,String image) {
        this.name = name;
        this.quantity = quantity;
        this.image=image;
    }

    public Product(Integer id, String name, Integer quantity, String image) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.image=image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
