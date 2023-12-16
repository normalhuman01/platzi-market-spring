package com.platzi.market.domain.repository;

import com.platzi.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<Purchase> getPurchase(Long purchaseId);
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
    void delete(Long purchaseId);
}
