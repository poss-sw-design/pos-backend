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
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- branch table
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
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
  CONSTRAINT fk_branch_merchant FOREIGN KEY (merchant_id) REFERENCES merchant(merchant_id) ON DELETE CASCADE
);

CREATE INDEX idx_merchant_email ON merchant(email);
CREATE INDEX idx_branch_merchant ON branch(merchant_id);
