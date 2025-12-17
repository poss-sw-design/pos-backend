CREATE TYPE business_type_enum AS ENUM ('restaurant','bar','cafe','barber','other');
CREATE TYPE merchant_status_enum AS ENUM ('active','inactive','suspended');
CREATE TYPE branch_status_enum AS ENUM ('active','inactive');

CREATE TABLE merchant (
  merchant_id BIGSERIAL PRIMARY KEY,
  business_name VARCHAR(255) NOT NULL,
  business_type business_type_enum,
  address_line1 VARCHAR(255),
  city VARCHAR(128),
  region VARCHAR(128),
  postal_code VARCHAR(32),
  phone VARCHAR(20) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  status merchant_status_enum DEFAULT 'active',
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE branch (
  branch_id BIGSERIAL PRIMARY KEY,
  merchant_id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  address_line1 VARCHAR(255),
  city VARCHAR(128),
  region VARCHAR(128),
  postal_code VARCHAR(32),
  phone VARCHAR(20),
  email VARCHAR(255),
  status branch_status_enum DEFAULT 'active',
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now(),
  CONSTRAINT fk_branch_merchant FOREIGN KEY (merchant_id) REFERENCES merchant(merchant_id) ON DELETE CASCADE
);

CREATE INDEX idx_merchant_email ON merchant(email);
CREATE INDEX idx_branch_merchant ON branch(merchant_id);

CREATE TABLE product_type (
    product_type_id BIGSERIAL PRIMARY KEY,
    product_type_name VARCHAR(255) NOT NULL
);

CREATE TABLE tax_rate (
    tax_rate_id BIGSERIAL PRIMARY KEY,
    rate NUMERIC(5,2) NOT NULL DEFAULT 0.0
);

CREATE TABLE product (
    product_id BIGSERIAL PRIMARY KEY,
    merchant_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    product_type_id BIGINT,
    tax_rate_id BIGINT,
    description TEXT,
    image_url TEXT,
    status VARCHAR(32) NOT NULL DEFAULT 'active',
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_product_merchant FOREIGN KEY (merchant_id) REFERENCES merchant(merchant_id) ON DELETE CASCADE,
    CONSTRAINT fk_product_type FOREIGN KEY (product_type_id) REFERENCES product_type(product_type_id) ON DELETE SET NULL,
    CONSTRAINT fk_tax_rate FOREIGN KEY (tax_rate_id) REFERENCES tax_rate(tax_rate_id) ON DELETE SET NULL
);

CREATE TABLE role (
  role_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE DEFAULT 'user',
  description VARCHAR(255),
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE permission (
  permission_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE DEFAULT 'read',
  resource VARCHAR(255) NOT NULL,
  action VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  CONSTRAINT uk_resource_action UNIQUE (resource, action)
);

CREATE TABLE role_permission (
  role_permission_id BIGSERIAL PRIMARY KEY,
  role_id BIGINT NOT NULL,
  permission_id BIGINT NOT NULL,
  CONSTRAINT fk_role_permission_role FOREIGN KEY (role_id) REFERENCES role(role_id),
  CONSTRAINT fk_role_permission_permission FOREIGN KEY (permission_id) REFERENCES permission(permission_id),
  CONSTRAINT uk_role_permission UNIQUE (role_id, permission_id)
);

CREATE TABLE employee (
  employee_id BIGSERIAL PRIMARY KEY,
  role_id BIGINT NOT NULL,
  branch_id BIGINT,
  merchant_id BIGINT,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  phone VARCHAR(20),
  status VARCHAR(20) NOT NULL DEFAULT 'active',
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  last_login TIMESTAMPTZ,
  CONSTRAINT fk_employee_role FOREIGN KEY (role_id) REFERENCES role(role_id),
  CONSTRAINT fk_employee_branch FOREIGN KEY (branch_id) REFERENCES branch(branch_id),
  CONSTRAINT fk_employee_merchant FOREIGN KEY (merchant_id) REFERENCES merchant(merchant_id)
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

CREATE TABLE orders (
  order_id BIGSERIAL PRIMARY KEY,
  merchant_id BIGINT NOT NULL,
  employee_id BIGINT NOT NULL,
  discount_id BIGINT,
  order_number VARCHAR(50) NOT NULL UNIQUE,
  status VARCHAR(20) NOT NULL,
  special_requests TEXT,
  order_date TIMESTAMPTZ NOT NULL,
  total_amount NUMERIC(10,2) NOT NULL,
  final_amount NUMERIC(10,2) NOT NULL,
  tax_amount NUMERIC(10,2) NOT NULL DEFAULT 0,
  CONSTRAINT fk_order_merchant FOREIGN KEY (merchant_id) REFERENCES merchant(merchant_id),
  CONSTRAINT fk_order_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
  CONSTRAINT fk_order_discount FOREIGN KEY (discount_id) REFERENCES discount(discount_id)
);

CREATE TABLE order_item (
  order_item_id BIGSERIAL PRIMARY KEY,
  order_id BIGINT NOT NULL REFERENCES orders(order_id) ON DELETE CASCADE,
  product_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  unit_price NUMERIC(10,2) NOT NULL,
  tax_rate NUMERIC(5,2) NOT NULL DEFAULT 0,
  tax_amount NUMERIC(10,2) NOT NULL DEFAULT 0,
  created_at TIMESTAMPTZ NOT NULL,
  CONSTRAINT uk_order_product UNIQUE (order_id, product_id)
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

CREATE TABLE reservation (
  reservation_id BIGSERIAL PRIMARY KEY,
  merchant_id BIGINT NOT NULL,
  employee_id BIGINT,
  customer_name VARCHAR(255) NOT NULL,
  customer_phone VARCHAR(20) NOT NULL,
  party_size INT NOT NULL CHECK (party_size >= 1),
  reservation_date DATE NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME,
  status VARCHAR(20) NOT NULL DEFAULT 'pending',
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  CONSTRAINT fk_reservation_merchant FOREIGN KEY (merchant_id) REFERENCES merchant(merchant_id) ON DELETE CASCADE,
  CONSTRAINT fk_reservation_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE SET NULL
);

CREATE INDEX idx_reservation_merchant_date ON reservation (merchant_id, reservation_date);
CREATE INDEX idx_reservation_employee ON reservation (employee_id);
