package com.rodrigo.batch_processing.domain;

public class CardType {

    private Long id;
    private Integer product;
    private Integer account;
    private String type;

    public CardType() {
    }

    public CardType(Long id, Integer product, Integer account, String type) {
        this.id = id;
        this.product = product;
        this.account = account;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
