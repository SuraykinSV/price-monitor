package com.example.price_monitor.parser.teststore;

import com.example.price_monitor.exception.ProductParsingException;
import com.example.price_monitor.parser.ProductData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class TestStoreParserTest {

    private final TestStoreParser parser =
            new TestStoreParser();

    @Test
    void shouldParseProduct() throws IOException {

        try (InputStream inputStream = getClass().getResourceAsStream("/html/test-store-product.html")) {

            assertNotNull(inputStream);

            String html = new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8
            );

            ProductData product = parser.parse(html);

            assertEquals("Test Hoodie", product.name());

            assertEquals(new BigDecimal("4990.00"), product.price());

            assertEquals("RUB", product.currency());
        }
    }
    @Test
    void shouldThrowWhenPriceIsMissing() {

        String html = """
            <html>
                <body>
                    <h1 class="product-title">
                        Test Hoodie
                    </h1>
                </body>
            </html>
            """;

        assertThrows(
                ProductParsingException.class, () -> parser.parse(html));
    }
}
