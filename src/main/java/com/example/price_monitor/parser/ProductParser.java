package com.example.price_monitor.parser;

public interface ProductParser {

    boolean supports(String url);

    ProductData parse(String html);

    String getStore();
}