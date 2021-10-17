package com.fa.springmobilestore.dao;

import com.fa.springmobilestore.entity.Product;
import com.fa.springmobilestore.form.ProductForm;
import com.fa.springmobilestore.model.ProductInfo;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Product findProduct(int proID) {
        try {
            String sql = "SELECT p "
                    + "FROM " + Product.class.getName() + " p "
                    + "WHERE p.proID = :proID ";
            Session session = this.sessionFactory.getCurrentSession();
            Query<Product> query = session.createQuery(sql, Product.class);
            query.setParameter("proID", proID);
            return (Product) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(ProductForm productForm) {
        Session session = this.sessionFactory.getCurrentSession();

        Product product = new Product();
        product.setProName(productForm.getProName());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setStock(productForm.getStock());
        product.setManufacturer(productForm.getManufacturer());
        product.setCategory(productForm.getCategory());
        product.setCondition(productForm.getCondition());
        product.setImgLink(productForm.getImgLink());

        session.persist(product);

        session.flush();
    }

    public List<ProductInfo> queryProducts() {
        String sql = "SELECT NEW " + ProductInfo.class.getName()
                + "(p.proID, p.proName, p.description, p.price, "
                + "p.stock, p.manufacturer, p.category, p.condition, "
                + "p.imgLink) "
                + "FROM " + Product.class.getName() + " p ";
        Session session = this.sessionFactory.getCurrentSession();
        Query<ProductInfo> query = session.createQuery(sql, ProductInfo.class);

        return (List<ProductInfo>) query.getResultList();
    }
}
