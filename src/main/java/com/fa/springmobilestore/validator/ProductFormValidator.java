package com.fa.springmobilestore.validator;

import com.fa.springmobilestore.form.ProductForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ProductForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductForm productForm = (ProductForm) target;

        /* Validation messages from validation.properties */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proName", "Length.productForm.proName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Length.productForm.description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Pattern.productForm.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock", "Pattern.productForm.stock");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "manufacturer", "Length.productForm.manufacturer");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "Length.productForm.category");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "condition", "Length.productForm.condition");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "imgLink", "Length.productForm.imgLink");

        String proName = productForm.getProName();
        if (proName != null && proName.length() > 0) {
            if (proName.length() > 50 || proName.length() < 2) {
                errors.rejectValue("proName", "Length.productForm.proName");
            }
        }
        String description = productForm.getDescription();
        if (description != null && description.length() > 0) {
            if (description.length() > 250 || description.length() < 2) {
                errors.rejectValue("description", "Length.productForm.description");
            }
        }
        String manufacturer = productForm.getManufacturer();
        if (manufacturer != null && manufacturer.length() > 0) {
            if (manufacturer.length() > 50 || manufacturer.length() < 2) {
                errors.rejectValue("manufacturer", "Length.productForm.manufacturer");
            }
        }
        String category = productForm.getCategory();
        if (category != null && category.length() > 0) {
            if (category.length() > 50 || category.length() < 2) {
                errors.rejectValue("category", "Length.productForm.category");
            }
        }
        String imgLink = productForm.getImgLink();
        if (imgLink != null && imgLink.length() > 0) {
            if (imgLink.length() < 2) {
                errors.rejectValue("imgLink", "Length.productForm.imgLink");
            }
        }
        float price = productForm.getPrice();
        if (price < 0) {
            errors.rejectValue("price", "Value.productForm.price");
        }
        int stock = productForm.getStock();
        if (stock < 0) {
            errors.rejectValue("stock", "Value.productForm.stock");
        }
    }

}
