package com.platzi.market.web.controller;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation(value = "Buscar todas las compras", authorizations = {@Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar una compra a traves de su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/{purchaseId}")
    public ResponseEntity<Purchase> getPurchase(@PathVariable("purchaseId") Long purchaseId){
        return ResponseEntity.of(purchaseService.getPurchase(purchaseId));
    }

    @ApiOperation(value = "Buscar una compra a traves del id de su cliente", authorizations = {@Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("clientId") String clientId){
        return ResponseEntity.of(purchaseService.getByClient(clientId));
    }

    @ApiOperation(value = "Guardar una compra nueva", authorizations = {@Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    @PostMapping()
    public ResponseEntity save(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Eliminar una compra a traves de su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    @DeleteMapping("/{purchaseId}")
    public ResponseEntity delete(@PathVariable("purchaseId") Long purchaseId){
        if(purchaseService.delete(purchaseId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
