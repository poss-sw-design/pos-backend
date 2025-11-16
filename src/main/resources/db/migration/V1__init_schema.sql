CREATE TABLE product (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       price INTEGER NOT NULL,
                       category VARCHAR(100),
                       merchant_id BIGINT NOT NULL
); -- example
