-- 1. Merchant
INSERT INTO merchant (merchant_id, business_name, business_type, address_line1, city, region, postal_code, phone, email, status)
VALUES
(1, 'Happy Cafe', 'cafe', '123 Coffee St', 'Vilnius', 'Vilnius County', '01001', '+37061234567', 'happycafe@example.com', 'active'),
(2, 'Fine Dine', 'restaurant', '456 Food Rd', 'Kaunas', 'Kaunas County', '44001', '+37069876543', 'finedine@example.com', 'active'),
(3, 'Beauty Salon', 'barber', '789 Style Ave', 'Vilnius', 'Vilnius County', '01002', '+37061239876', 'beautysalon@example.com', 'active');

-- 2. Branch
INSERT INTO branch (branch_id, merchant_id, name, address_line1, city, region, postal_code, phone, email, status)
VALUES
(1, 1, 'Happy Cafe Downtown', '123 Coffee St', 'Vilnius', 'Vilnius County', '01001', '+37061234567', 'downtown@example.com', 'active'),
(2, 2, 'Fine Dine Main', '456 Food Rd', 'Kaunas', 'Kaunas County', '44001', '+37069876543', 'mainbranch@example.com', 'active');

-- 3. Role
INSERT INTO role (role_id, name)
VALUES
(1, 'admin'),
(2, 'barista'),
(3, 'waiter');

-- 4. Employee
INSERT INTO employee (employee_id, role_id, branch_id, merchant_id, email, password, first_name, last_name, phone, status)
VALUES
(1, 1, 1, 1, 'alice@example.com', 'password123', 'Alice', 'Kim', '010-1111-1111', 'active'),
(2, 2, 1, 1, 'bob@example.com', 'password123', 'Bob', 'Lee', '010-2222-2222', 'active'),
(3, 3, 2, 2, 'charlie@example.com', 'password123', 'Charlie', 'Park', '010-3333-3333', 'inactive'),
(4, 1, NULL, 3, 'diana@example.com', 'password123', 'Diana', 'Choi', '010-4444-4444', 'active');

-- 5. Product
INSERT INTO product (product_id, merchant_id, name, price, status)
VALUES
(1, 1, 'Cappuccino', 3.5, 'active'),
(2, 1, 'Latte', 4.0, 'active'),
(3, 2, 'Steak', 15.0, 'active');

-- 6. Discount
INSERT INTO discount (name, type, value_type, value, minimum_order_value, status, current_uses, max_uses, start_time, end_time, created_at, updated_at)
VALUES
('Morning Promo', 'order_discount', 'percentage', 0.1, 0, 'active', 0, NULL, now(), NULL, now(), now()),
('Dinner Special', 'order_discount', 'percentage', 0.2, 0, 'active', 0, NULL, now(), NULL, now(), now());

-- 7. Orders
INSERT INTO orders (order_id, merchant_id, employee_id, total_amount, final_amount, status, order_number, order_date)
VALUES
(1, 1, 1, 8, 7, 'completed', 'ORD001', NOW()),
(2, 2, 2, 15, 15, 'pending', 'ORD002', NOW());

-- 8. Order items
INSERT INTO order_item (order_item_id, order_id, product_id, quantity, unit_price, created_at)
VALUES
(1, 1, 1, 1, 350, NOW()),
(2, 1, 2, 1, 400, NOW()),
(3, 2, 3, 1, 1500, NOW());

-- 9. Payment
INSERT INTO payment (payment_id, order_id, merchant_id, payment_method, status, split, tip_amount, created_at, processed_at)
VALUES
(1, 1, 1, 'card', 'completed', FALSE, 0, NOW(), NOW()),
(2, 2, 1, 'cash', 'completed', FALSE, 0, NOW(), NOW());
