
-- Merchant
INSERT INTO merchant (merchant_id, business_name, business_type, address_line1, city, region, postal_code, phone, email, status)
VALUES
(1, 'Happy Cafe', 'cafe', '123 Coffee St', 'Vilnius', 'Vilnius County', '01001', '+37061234567', 'happycafe@example.com', 'active'),
(2, 'Fine Dine', 'restaurant', '456 Food Rd', 'Kaunas', 'Kaunas County', '44001', '+37069876543', 'finedine@example.com', 'active'),
(3, 'Beauty Salon', 'barber', '789 Style Ave', 'Vilnius', 'Vilnius County', '01002', '+37061239876', 'beautysalon@example.com', 'active');

-- Branch
INSERT INTO branch (branch_id, merchant_id, name, address_line1, city, region, postal_code, phone, email, status)
VALUES
(1, 1, 'Happy Cafe Downtown', '123 Coffee St', 'Vilnius', 'Vilnius County', '01001', '+37061234567', 'downtown@example.com', 'active'),
(2, 2, 'Fine Dine Main', '456 Food Rd', 'Kaunas', 'Kaunas County', '44001', '+37069876543', 'mainbranch@example.com', 'active');

-- Role
INSERT INTO role (role_id, name)
VALUES
(1, 'admin'),
(2, 'barista'),
(3, 'waiter');

-- Employee
INSERT INTO employee (employee_id, role_id, branch_id, merchant_id, email, password, first_name, last_name, phone, status)
VALUES
(1, 1, 1, 1, 'alice@example.com', 'password123', 'Alice', 'Kim', '010-1111-1111', 'active'),
(2, 2, 1, 1, 'bob@example.com', 'password123', 'Bob', 'Lee', '010-2222-2222', 'active'),
(3, 3, 2, 2, 'charlie@example.com', 'password123', 'Charlie', 'Park', '010-3333-3333', 'inactive'),
(4, 1, NULL, 3, 'diana@example.com', 'password123', 'Diana', 'Choi', '010-4444-4444', 'active');

-- Product
INSERT INTO product (product_id, merchant_id, name, price, status)
VALUES
(1, 1, 'Cappuccino', 3.50, 'active'),
(2, 1, 'Latte', 4.00, 'active'),
(3, 2, 'Steak', 15.00, 'active');

-- Discount
INSERT INTO discount (name, type, value_type, value, minimum_order_value, status, current_uses, max_uses, start_time, end_time, created_at, updated_at)
VALUES
('Morning Promo', 'order_discount', 'percentage', 10.00, 0, 'active', 0, NULL, now(), NULL, now(), now()),
('Dinner Special', 'order_discount', 'percentage', 20.00, 0, 'active', 0, NULL, now(), NULL, now(), now());

-- Orders
INSERT INTO orders (merchant_id, employee_id, total_amount, final_amount, status, order_number, order_date)
VALUES
(1, 1, 7.50, 7.00, 'completed', 'ORD001', NOW()),
(2, 2, 15.00, 15.00, 'open', 'ORD002', NOW());

-- Order Items
INSERT INTO order_item (order_item_id, order_id, product_id, quantity, unit_price, created_at)
VALUES
(1, 1, 1, 1, 3.50, NOW()),
(2, 1, 2, 1, 4.00, NOW()),
(3, 2, 3, 1, 15.00, NOW());

-- Payment
INSERT INTO payment (order_id, merchant_id, payment_method, status, split, tip_amount, created_at, processed_at)
VALUES
(1, 1, 'card', 'completed', FALSE, 0, NOW(), NOW()),
(2, 2, 'cash', 'completed', FALSE, 0, NOW(), NOW());

-- Reservation
INSERT INTO reservation (merchant_id, employee_id, customer_name, customer_phone, party_size, reservation_date, start_time, end_time, status, created_at, updated_at)
VALUES
(1, 2, 'John Doe', '+37061230001', 2, '2025-12-15', '10:00', '11:00', 'pending', NOW(), NOW()),
(1, 1, 'Jane Smith', '+37061230002', 4, '2025-12-15', '11:30', '12:30', 'pending', NOW(), NOW()),
(2, 3, 'Michael Brown', '+37061230003', 3, '2025-12-16', '18:00', '19:30', 'confirmed', NOW(), NOW()),
(3, 4, 'Emily White', '+37061230004', 1, '2025-12-17', '14:00', '14:30', 'pending', NOW(), NOW());
