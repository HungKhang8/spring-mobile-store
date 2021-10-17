package com.fa.springmobilestore.controller;

import com.fa.springmobilestore.dao.ProductDAO;
import com.fa.springmobilestore.entity.Product;
import com.fa.springmobilestore.model.CartInfo;
import com.fa.springmobilestore.model.ProductInfo;
import com.fa.springmobilestore.utils.Utils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
public class CartController {

    @Autowired
    ProductDAO productDAO;

    @GetMapping("/cart/view")
    public String viewCart(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        model.addAttribute("cartInfo", cartInfo);
        return "cart";
    }

    @GetMapping("/cart/clear")
    public String clearCart(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.clearCart();
        return "redirect:/cart/view";
    }

    @GetMapping("/cart/add/product")
    public String addProductToCart(HttpServletRequest request, Model model,
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
            CartInfo cartInfo = Utils.getCartInSession(request);
            ProductInfo productInfo = new ProductInfo(product);
            cartInfo.addProduct(productInfo);
        }
        return "redirect:/cart/view";
    }

    @GetMapping("/cart/remove/product")
    public String removeProductFromCart(HttpServletRequest request, Model model,
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
            CartInfo cartInfo = Utils.getCartInSession(request);
            ProductInfo productInfo = new ProductInfo(product);
            cartInfo.removeProduct(productInfo);
        }
        return "redirect:/cart/view";
    }
}
