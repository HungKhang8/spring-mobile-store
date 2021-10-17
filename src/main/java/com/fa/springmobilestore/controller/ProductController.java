package com.fa.springmobilestore.controller;

import com.fa.springmobilestore.dao.ProductDAO;
import com.fa.springmobilestore.entity.Product;
import com.fa.springmobilestore.form.ProductForm;
import com.fa.springmobilestore.model.ProductInfo;
import com.fa.springmobilestore.validator.ProductFormValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductFormValidator productFormValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == ProductForm.class) {
            dataBinder.setValidator(productFormValidator);
        }
    }

    @GetMapping("/product/list")
    public String listProduct(Model model) {
        List<ProductInfo> result = productDAO.queryProducts();
        if (result == null
                || (result != null && result.isEmpty())) {
            model.addAttribute("noProduct", true);
        }
        model.addAttribute("productList", result);
        return "index";
    }

    @GetMapping("/product/details")
    public String viewProduct(Model model, //
            @RequestParam(value = "proID", defaultValue = "") String proID) {
        Product product = null;

        if (proID != null && proID.length() > 0) {
            try {
                product = productDAO.findProduct(Integer.parseInt(proID));
            } catch (NumberFormatException ex) {
                return "redirect:/";
            }
        }
        if (product != null) {
            ProductInfo productInfo = new ProductInfo(product);
            model.addAttribute("productInfo", productInfo);
        }
        return "productDetails";
    }

    @GetMapping("/admin/product/create")
    public String createProduct(Model model) {
        ProductForm productForm = new ProductForm();
        model.addAttribute("productForm", productForm);
        return "createProduct";
    }

    @PostMapping("/admin/product/create")
    public String createProductSubmit(Model model, //
            @ModelAttribute("productForm") @Validated ProductForm productForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "createProduct";
        }
        try {
            productDAO.save(productForm);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "createProduct";
        }
        return "redirect:/admin/product/create/successful";
    }

    @GetMapping("/admin/product/create/successful")
    public String createProductSuccessful(Model model) {
        return "createProductSuccessful";
    }

}
