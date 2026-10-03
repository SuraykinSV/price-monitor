CREATE TABLE items (
    id UUID PRIMARY KEY,

    url TEXT NOT NULL UNIQUE,

    store VARCHAR(50) NOT NULL,

    name VARCHAR(500) NOT NULL,

    current_price NUMERIC(19, 2) NOT NULL,

    currency VARCHAR(3) NOT NULL,

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    last_checked_at TIMESTAMPTZ NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_items_current_price
       CHECK (current_price >= 0)
);

CREATE TABLE price_history (
    id BIGSERIAL PRIMARY KEY,

    item_id UUID NOT NULL,

    price NUMERIC(19, 2) NOT NULL,

    checked_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_price_history_item
       FOREIGN KEY (item_id)
           REFERENCES items(id),

    CONSTRAINT chk_price_history_price
       CHECK (price >= 0)
);

CREATE INDEX idx_price_history_item_checked_at
    ON price_history(item_id, checked_at DESC);

