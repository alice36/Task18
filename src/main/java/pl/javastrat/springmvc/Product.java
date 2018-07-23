package pl.javastrat.springmvc;

public class Product {
    private String name;
    private double price;
    private ProductCategory category;

    public Product(String name, double price, ProductCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " " + price + " " + category;
    }
}
