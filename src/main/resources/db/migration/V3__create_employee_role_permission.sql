CREATE TABLE role (
  role_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE,
  description VARCHAR(255),
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE permission (
  permission_id BIGSERIAL PRIMARY KEY,

  name VARCHAR(100) NOT NULL UNIQUE,
  resource VARCHAR(255) NOT NULL,
  action   VARCHAR(255) NOT NULL,

  description VARCHAR(255),

  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

  CONSTRAINT uk_resource_action UNIQUE (resource, action)
);

CREATE TABLE role_permission (
  role_permission_id BIGSERIAL PRIMARY KEY,
  role_id BIGINT NOT NULL,
  permission_id BIGINT NOT NULL,

  CONSTRAINT fk_role_permission_role
    FOREIGN KEY (role_id) REFERENCES role(role_id),

  CONSTRAINT fk_role_permission_permission
    FOREIGN KEY (permission_id) REFERENCES permission(permission_id),

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

  status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  last_login TIMESTAMPTZ,

  CONSTRAINT fk_employee_role
    FOREIGN KEY (role_id) REFERENCES role(role_id),

  CONSTRAINT fk_employee_branch
    FOREIGN KEY (branch_id) REFERENCES branch(branch_id),

  CONSTRAINT fk_employee_merchant
    FOREIGN KEY (merchant_id) REFERENCES merchant(merchant_id)
);
