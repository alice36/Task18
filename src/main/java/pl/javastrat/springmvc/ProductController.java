package pl.javastrat.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping
    public String home() {
        return "index.html";
    }

    @RequestMapping("/add")
    public String addUser(@RequestParam(value = "nazwa", defaultValue = "Jogurt") String nazwa , @RequestParam(value = "cena", defaultValue = "2.5") double cena, @RequestParam(value = "kategoria", defaultValue = "CAT1") String kategoria) {

        Product product = new Product(nazwa, cena, ProductCategory.valueOf(kategoria));
        productRepository.addProduct(product);

        return "redirect:/sukces";
    }

    @RequestMapping("/sukces")
    public String success() {
        return "sukces.html";
    }

//    @GetMapping("/produktySpoz")
//    public String productsSpoz(Model model) {
//        List<Product> products = productRepository.getProducts();
//        List<Product> products2 = new ArrayList<>();
//        double price=0;
//        String result;
//        for (int i = products.size()-1; i >=0; i--) {
//            if (products.get(i).getCategory().equals("Art.spożywcze")) {
//                price = price + products.get(i).getPrice();
//                products2.add(products.get(i));
//            }
//        }
//        result = "Suma cen wynosi " + price;
//        model.addAttribute("allProducts", products2);
//        model.addAttribute("wynik", result);
//        //return
//        return "produkty";
//    }
    @GetMapping("/lista")
    public String productsList(@RequestParam(required = false, value = "kategoria") String category, Model model) {
        List<Product> products = productRepository.getProducts();
        List<Product> products2 = new ArrayList<>();
        double price=0;
        String result, kat="";

        if(category == null) {
            for (Product product : products) {
                products2.add(product);
                price = price+product.getPrice();
            }
        } else{
            switch (category) {
                case "spozywcze":
                    kat = "Art.spożywcze";
                    break;
                case "domowe":
                    kat = "Art.gosp.domowego";
                    break;
                case "inne":
                    kat = "Inne";
                    break;
            }

            for (Product product : products) {
                if (product.getCategory().getDescription().equals(kat)){
                    price = price+product.getPrice();
                    products2.add(product);
                }
            }
        }
        result = "Suma cen wynosi " + price;
        model.addAttribute("allProducts", products2);
        model.addAttribute("wynik", result);
        return "produkty";
    }

}
