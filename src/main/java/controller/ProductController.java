package controller;

import model.Category;
import model.Product;
import service.category.ICategoryService;
import service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    //Show All
    @GetMapping("")
    public ModelAndView showAll(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Product> products = productService.findAll(pageable);
        modelAndView.addObject("products", products);
        return modelAndView;
    }

//    @GetMapping("")
//    public ModelAndView getAll(@RequestParam("search") Optional<String> name) {
//        List<Product> products;
//        if (name.isPresent()) {
//            products = productService.findByName(name.get());
//        } else {
//            products = productService.findALl();
//        }
//        ModelAndView modelAndView = new ModelAndView("list");
//        modelAndView.addObject("products", products);
//        return modelAndView;
//    }

    //Tạo mới
    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("create");
        productService.save(product);
        modelAndView.addObject("product", product);
        modelAndView.addObject("mess", "Tao moi thanh cong product:" + product.getName());
        return modelAndView;
    }

    //Sửa
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product products = productService.findById(id);
        modelAndView.addObject("product", products);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, @ModelAttribute Product product) {
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }

    //Xóa
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable long id) {
        productService.remove(id);
        return new ModelAndView("redirect:/products");
    }

    //Chi tiết sản phẩm
    @GetMapping("/view/{id}")
    public ModelAndView viewDetail(@PathVariable long id) {
        return new ModelAndView("view", "product", productService.findById(id));
    }

    //Search
    @PostMapping("")
    public ModelAndView searchProductByName(@RequestParam String name) {
        String nameProduct = "%" + name + "%";
        List<Product> products = productService.findByName(nameProduct);
        return new ModelAndView("list", "products", products);
    }

//    @PostMapping("")
//    public ModelAndView searchProductByCategory(@RequestParam Long id) {
//        List<Product> products = productService.findByCategoryName(id);
//        if (products.size() == 0) return new ModelAndView("error-404");
//        else return new ModelAndView("list", "products", products);
//
//    }

}