package pl.javastrat.springmvc;

public enum ProductCategory {
    CAT1("Art.spożywcze"),
    CAT2("Art.gosp.domowego"),
    CAT3("Inne");

    private final String description;

    ProductCategory(String description) {
        this.description = description;
    }

    public String printProductName() {
        return this.description;
    }

    public String getDescription() {
        return description;
    }
}