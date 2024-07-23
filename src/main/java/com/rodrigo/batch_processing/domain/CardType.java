package com.rodrigo.batch_processing.domain;

public class CardType {

    private Integer product;
    private Integer variant;
    private String description;

    public CardType() {
    }

    public CardType(Integer product, Integer variant, String description) {
        this.product = product;
        this.variant = variant;
        this.description = description;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getVariant() {
        return variant;
    }

    public void setVariant(Integer variant) {
        this.variant = variant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
