package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.annotations.*;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Buscar todos los productos", authorizations = {@Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un producto a traves de su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") long productId){
        return ResponseEntity.of(productService.getProduct(productId));
    }

    @ApiOperation(value = "Obtiene un producto a traves del id de su categoria", authorizations = {@Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") long categoryId){
        return ResponseEntity.of(productService.getByCategory(categoryId));
    }

    @ApiOperation(value = "Guarda un producto nuevo", authorizations = {@Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina un producto a traves de su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Producto no encontrado")
    })
    @DeleteMapping("/{productId}")
    public ResponseEntity delete(@ApiParam(value = "El id del producto", required = true, example = "1")
                                     @PathVariable("productId") long productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Genera un excel con los productos existentes en la base de datos", authorizations = {@Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/export/all")
    public ResponseEntity<InputStreamResource> exportAllData() throws Exception{

        ByteArrayInputStream stream = productService.exportAllData();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=productos.xlsx");
        headers.setContentType(MediaType.parseMediaType("application/csv"));

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }
}
