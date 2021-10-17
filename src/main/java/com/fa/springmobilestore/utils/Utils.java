package com.fa.springmobilestore.utils;

import com.fa.springmobilestore.model.CartInfo;
import javax.servlet.http.HttpServletRequest;

public class Utils {

    private static final String USER_CART = "USER_CART";

    public static CartInfo getCartInSession(HttpServletRequest request) {
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute(USER_CART);

        if (cartInfo == null) {
            cartInfo = new CartInfo();

            request.getSession().setAttribute(USER_CART, cartInfo);
        }
        return cartInfo;
    }

    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_CART);
    }
}
