package MVC.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by plesha on 07-Apr-18.
 */
@XStreamAlias("model.MedicationModel")
public class MedicationModel {
    @XStreamAlias("id")
    private int id;

    @XStreamAlias("name")
    private String name;

    @XStreamAlias("ingredients")
    private String ingredients;

    @XStreamAlias("manufacturer")
    private String manufacturer;

    @XStreamAlias("quantity")
    private int quantity;

    @XStreamAlias("price")
    private int price;

    public MedicationModel() {

    }

    public MedicationModel(int id, String name, String ingredients, String manufacturer, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
