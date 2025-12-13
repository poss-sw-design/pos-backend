CREATE TABLE product_type (
    product_type_id BIGSERIAL PRIMARY KEY,
    product_type_name VARCHAR(255) NOT NULL
);

CREATE TABLE tax_rate (
    tax_rate_id BIGSERIAL PRIMARY KEY,
    rate DOUBLE PRECISION NOT NULL DEFAULT 0.0
);

CREATE TABLE product (
    product_id BIGSERIAL PRIMARY KEY,
    merchant_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    product_type_id BIGINT,
    tax_rate_id BIGINT,
    description TEXT,
    image_url TEXT,
    status VARCHAR(32) NOT NULL DEFAULT 'active',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_merchant FOREIGN KEY (merchant_id)
        REFERENCES merchant (merchant_id) ON DELETE CASCADE,

    CONSTRAINT fk_product_type FOREIGN KEY (product_type_id)
        REFERENCES product_type (product_type_id) ON DELETE SET NULL,

    CONSTRAINT fk_tax_rate FOREIGN KEY (tax_rate_id)
        REFERENCES tax_rate (tax_rate_id) ON DELETE SET NULL
);
