package pl.javastrat.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/produkty")
    public String home(Model model) {
        List<Product> products = productRepository.getProducts();
        double price=0;
        String result;
        for (int i = products.size()-1; i >=0; i--) {
            price = price + products.get(i).getPrice();
        }
        result = "Suma cen wynosi " + price;
        model.addAttribute("allProducts", products);
        model.addAttribute("wynik", result);
        return "produkty";
    }

    @RequestMapping("/add")
    public String addUser(@RequestParam(value = "nazwa") String nazwa, @RequestParam(value = "cena") double cena, @RequestParam(value = "kategoria") String kategoria) {

        Product product = new Product(nazwa, cena, kategoria);
        productRepository.addProduct(product);

        return "redirect:/sukces";
    }

    @RequestMapping("/sukces")
    public String success() {
        return "sukces.html";
    }

    @GetMapping("/produktySpoz")
    public String productsSpoz(Model model) {
        List<Product> products = productRepository.getProducts();
        List<Product> products2 = new ArrayList<>();
        double price=0;
        String result;
        for (int i = products.size()-1; i >=0; i--) {
            if (products.get(i).getCategory().equals("Art.spożywcze")) {
                price = price + products.get(i).getPrice();
                products2.add(products.get(i));
            } 
        }
        result = "Suma cen wynosi " + price;
        model.addAttribute("allProducts", products2);
        model.addAttribute("wynik", result);
        //return
        return "produkty";
    }

    @GetMapping("/produktyGosp")
    public String productsGosp(Model model) {
        List<Product> products = productRepository.getProducts();
        List<Product> products2 = new ArrayList<>();
        double price=0;
        String result;
        for (int i = products.size()-1; i >=0; i--) {
            if (products.get(i).getCategory().equals("Art.gosp.domowego")) {
                products2.add(products.get(i));
                price = price + products.get(i).getPrice();
            }
        }
        result = "Suma cen wynosi " + price;
        model.addAttribute("allProducts", products2);
        model.addAttribute("wynik", result);
        //return
        return "produkty";
    }
    @GetMapping("/produktyInne")
    public String productsInne(Model model) {
        List<Product> products = productRepository.getProducts();
        List<Product> products2 = new ArrayList<>();
        double price=0;
        String result;
        for (int i = products.size()-1; i >=0; i--) {
            if (products.get(i).getCategory().equals("Inne")) {
                products2.add(products.get(i));
                price = price + products.get(i).getPrice();
            }
        }
        result = "Suma cen wynosi " + price;
        model.addAttribute("allProducts", products2);
        model.addAttribute("wynik", result);
        //return
        return "produkty";
    }
}
