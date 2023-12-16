package com.platzi.market.domain.service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.entity.Producto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(long productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(long categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Optional<List<Product>> getScarseProducts(int quantity){
        return productRepository.getScarseProducts(quantity);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(long productId){
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }

    public ByteArrayInputStream exportAllData() throws Exception{
        return productRepository.exportAllData();
    }
}
