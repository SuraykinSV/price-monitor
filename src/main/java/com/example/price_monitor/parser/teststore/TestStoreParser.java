package com.example.price_monitor.parser.teststore;

import com.example.price_monitor.exception.ProductParsingException;
import com.example.price_monitor.parser.ProductData;
import com.example.price_monitor.parser.ProductParser;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class TestStoreParser implements ProductParser {

    private static final String STORE = "TEST_STORE";

    @Override
    public boolean supports(String url) {
        return url != null && url.contains("test-store.local");
    }

    @Override
    public ProductData parse(String html) {

        Document document = Jsoup.parse(html);

        Element nameElement =
                document.selectFirst(".product-title");

        Element priceElement =
                document.selectFirst(".product-price");

        if (nameElement == null) {
            throw new ProductParsingException("Product name not found");
        }

        if (priceElement == null) {
            throw new ProductParsingException("Product price not found");
        }

        String name = nameElement.text().trim();

        String rawPrice = priceElement
                .text()
                .replace("\u00A0", "")
                .replace(" ", "")
                .trim();

        String currency = priceElement.attr("data-currency");

        try {
            BigDecimal price = new BigDecimal(rawPrice);
            log.debug("Product parsed successfully, store={}, name={}, price={}", STORE, name, price);
            return new ProductData(name, price, currency);
        } catch (NumberFormatException exception) {
            throw new ProductParsingException("Unable to parse product price: " + rawPrice, exception);
        }
    }

    @Override
    public String getStore() {
        return STORE;
    }
}