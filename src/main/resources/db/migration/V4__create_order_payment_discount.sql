CREATE TABLE orders (
  order_id BIGSERIAL PRIMARY KEY,
  merchant_id BIGINT NOT NULL,
  employee_id BIGINT NOT NULL,
  discount_id BIGINT,
  order_number VARCHAR(50) NOT NULL UNIQUE,
  status VARCHAR(20) NOT NULL,
  special_requests TEXT,
  order_date TIMESTAMPTZ NOT NULL,
  total_amount INT NOT NULL,
  final_amount INT NOT NULL
);

CREATE TABLE order_item (
  order_item_id BIGSERIAL PRIMARY KEY,
  order_id BIGINT NOT NULL REFERENCES orders(order_id) ON DELETE CASCADE,
  product_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  unit_price INT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  CONSTRAINT uk_order_product UNIQUE (order_id, product_id)
);

CREATE TABLE discount (
  discount_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  type VARCHAR(30) NOT NULL,
  value_type VARCHAR(30) NOT NULL,
  value NUMERIC(10,2) NOT NULL,
  minimum_order_value NUMERIC(10,2) DEFAULT 0,
  status VARCHAR(20) NOT NULL,
  current_uses INT DEFAULT 0,
  max_uses INT,
  start_time TIMESTAMPTZ NOT NULL,
  end_time TIMESTAMPTZ,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE payment (
  payment_id BIGSERIAL PRIMARY KEY,
  order_id BIGINT NOT NULL UNIQUE REFERENCES orders(order_id) ON DELETE CASCADE,
  merchant_id BIGINT NOT NULL,
  payment_method VARCHAR(20) NOT NULL,
  status VARCHAR(20) NOT NULL,
  split BOOLEAN DEFAULT FALSE,
  tip_amount NUMERIC(10,2) DEFAULT 0,
  created_at TIMESTAMPTZ NOT NULL,
  processed_at TIMESTAMPTZ
);

CREATE TABLE refund (
  refund_id BIGSERIAL PRIMARY KEY,
  order_id BIGINT NOT NULL UNIQUE REFERENCES orders(order_id) ON DELETE CASCADE,
  amount NUMERIC(12,2) NOT NULL,
  reason TEXT NOT NULL,
  refund_type VARCHAR(20) NOT NULL,
  status VARCHAR(20) NOT NULL,
  processed_by BIGINT,
  approved_by BIGINT,
  created_at TIMESTAMPTZ NOT NULL,
  processed_at TIMESTAMPTZ
);
