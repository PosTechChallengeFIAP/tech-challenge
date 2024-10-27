package com.tech.challenge.tech_challenge.core.application.exceptions;

public class UsedProductCannotBeDeletedException extends Error{
    public UsedProductCannotBeDeletedException(String id, Exception cause) {
        super(String.format("Product with id = '%s' is in use and cannot be deleted. If you want to" +
                " avoid future uses of this product you should deactivate it.", id),cause);
    }
}
