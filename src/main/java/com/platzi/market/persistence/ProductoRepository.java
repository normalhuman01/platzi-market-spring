package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(long categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(long productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(long productId){
        productoCrudRepository.deleteById(productId);
    }

    @Override
    public ByteArrayInputStream exportAllData() throws Exception {
        String[] columns = {"Id del Producto","Nombre", "Id de la categoria", "Precio", "Stock", "Activo"};

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Productos");
        Row row = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }

        List<Product> products = this.getAll();
        int initRow = 1;
        for (Product product: products){
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(product.getProductId());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getCategoryId());
            row.createCell(3).setCellValue(product.getPrice());
            row.createCell(4).setCellValue(product.getStock());
            row.createCell(5).setCellValue(Boolean.toString(product.isActive()));

            initRow++;
        }

        workbook.write(stream);
        workbook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
}
