package org.dd.bre.Exception;

public class ProductVariantNotFoundException extends RuntimeException {
    public ProductVariantNotFoundException(String message) {
        super(message);
    }
}
