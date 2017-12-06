package com.fibanez.spring4annotation.web.service;

import com.fibanez.spring4annotation.model.Product;

public interface ProductService {

    Product getByName(String name);

    void refreshAllProducts();

    Product updateProduct(Product product);
}
