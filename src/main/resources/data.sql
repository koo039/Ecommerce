INSERT INTO categories (category_name)
VALUES
    ('Dresses'),
    ('Shoes'),
    ('Abayas'),
    ('Bags'),
    ('Jackets'),
    ('Hijabs'),
    ('Jilbabs'),
    ('Sportswear'),
    ('Long Skirts'),
    ('Home & Kitchen');





-- Dresses
INSERT INTO products (product_name, description, created_at, updated_at, category_id, price, original_price)
VALUES
    ('Floral Summer Dress', 'Lightweight modest summer dress with floral patterns.', NOW(), NOW(), 1, 39.99, null),
    ('Evening Maxi Dress', 'Elegant long sleeve maxi dress for events.', NOW(), NOW(), 1, 79.99, null);

INSERT INTO product_variants (sku, size, color, stock_qty, product_id,status)
VALUES
    ('DRS-001-S-BLUE', 'S', 'RED', 2, 1,'INSTOCK'),
    ('DRS-001-M-BLUE', 'M', 'RED', 0, 1,'OUTOFSTOCK'),
    ('DRS-001-L-PINK', 'L', 'PINK', 20, 1,'INSTOCK'),
    ('DRS-002-M-BLACK', 'M', 'BLACK', 0, 2,'OUTOFSTOCK'),
    ('DRS-002-L-BLACK', 'L', 'BLACK', 20, 2,'INSTOCK');

-- Shoes
INSERT INTO products (product_name, description, created_at, updated_at, category_id, price, original_price)
VALUES
    ('Running Sneakers', 'Comfortable sneakers with breathable mesh.', NOW(), NOW(), 2, 49.99, 69.99),
    ('Leather Sandals', 'Soft leather sandals with adjustable straps.', NOW(), NOW(), 2, 29.99, null);

INSERT INTO product_variants (sku, size, color, stock_qty, product_id)
VALUES
    ('SHO-001-38-WHITE', '38', 'WHITE', 50, 3),
    ('SHO-001-39-BLACK', '39', 'BLACK', 40, 3),
    ('SHO-001-40-BLUE', '40', 'BLUE', 35, 3),
    ('SHO-002-38-BROWN', '38', 'BROWN', 30, 4),
    ('SHO-002-39-BROWN', '39', 'BROWN', 20, 4);

-- Abayas
INSERT INTO products (product_name, description, created_at, updated_at, category_id, price, original_price)
VALUES
    ('Classic Black Abaya', 'Simple elegant abaya with wide sleeves.', NOW(), NOW(), 3, 45.00, 55.00),
    ('Open Front Abaya', 'Modern open front abaya with belt.', NOW(), NOW(), 3, 60.00, 75.00);

INSERT INTO product_variants (sku, size, color, stock_qty, product_id)
VALUES
    ('ABA-001-S-BLACK', 'S', 'BLACK', 40, 5),
    ('ABA-001-M-BLACK', 'M', 'BLACK', 35, 5),
    ('ABA-001-L-BLACK', 'L', 'BLACK', 20, 5),
    ('ABA-002-M-BEIGE', 'M', 'BEIGE', 25, 6),
    ('ABA-002-L-NAVY', 'L', 'NAVY', 30, 6);

-- Bags
INSERT INTO products (product_name, description, created_at, updated_at, category_id, price, original_price)
VALUES
    ('Leather Handbag', 'Premium leather handbag with metal handle.', NOW(), NOW(), 4, 120.00, 150.00),
    ('Casual Backpack', 'Durable backpack for everyday use.', NOW(), NOW(), 4, 49.99, 59.99);

INSERT INTO product_variants (sku, size, color, stock_qty, product_id)
VALUES
    ('BAG-001-STD-BLACK', 'STD', 'BLACK', 20, 7),
    ('BAG-001-STD-BROWN', 'STD', 'BROWN', 15, 7),
    ('BAG-002-STD-GREEN', 'STD', 'GREEN', 25, 8),
    ('BAG-002-STD-BLUE', 'STD', 'BLUE', 20, 8);

-- Jackets
INSERT INTO products (product_name, description, created_at, updated_at, category_id, price, original_price)
VALUES
    ('Denim Jacket', 'Classic blue denim jacket.', NOW(), NOW(), 5, 55.00, 69.99),
    ('Long Coat', 'Winter coat with modest cut.', NOW(), NOW(), 5, 89.99, 109.99);

INSERT INTO product_variants (sku, size, color, stock_qty, product_id)
VALUES
    ('JCK-001-S-BLUE', 'S', 'BLUE', 15, 9),
    ('JCK-001-M-BLUE', 'M', 'BLUE', 10, 9),
    ('JCK-002-M-GREY', 'M', 'GREY', 12, 10),
    ('JCK-002-L-BLACK', 'L', 'BLACK', 8, 10);

-- Hijabs
INSERT INTO products (product_name, description, created_at, updated_at, category_id, price, original_price)
VALUES
    ('Chiffon Hijab', 'Light chiffon hijab, easy to style.', NOW(), NOW(), 6, 12.99, null),
    ('Jersey Hijab', 'Stretchable jersey hijab for daily wear.', NOW(), NOW(), 6, 14.99, 19.99);

INSERT INTO product_variants (sku, size, color, stock_qty, product_id)
VALUES
    ('HJB-001-STD-BEIGE', 'STD', 'BEIGE', 100, 11),
    ('HJB-001-STD-BLACK', 'STD', 'BLACK', 80, 11),
    ('HJB-002-STD-GREEN', 'STD', 'GREEN', 60, 12),
    ('HJB-002-STD-BLUE', 'STD', 'BLUE', 70, 12);

-- Jilbabs
INSERT INTO products (product_name, description, created_at, updated_at, category_id, price, original_price)
VALUES
    ('One Piece Jilbab', 'Full coverage one-piece jilbab.', NOW(), NOW(), 7, 50.00, 65.00),
    ('Two Piece Jilbab', 'Two-piece jilbab with khimar and skirt.', NOW(), NOW(), 7, 55.00, 70.00);

INSERT INTO product_variants (sku, size, color, stock_qty, product_id)
VALUES
    ('JLB-001-M-NAVY', 'M', 'NAVY', 30, 13),
    ('JLB-001-L-BLACK', 'L', 'BLACK', 25, 13),
    ('JLB-002-M-GREY', 'M', 'GREY', 20, 14),
    ('JLB-002-L-BROWN', 'L', 'BROWN', 18, 14);

-- Sportswear
INSERT INTO products (product_name, description, created_at, updated_at, category_id, price, original_price)
VALUES
    ('Modest Tracksuit', 'Comfortable full-length tracksuit for women.', NOW(), NOW(), 8, 39.99, 49.99),
    ('Long Sleeve Sports Top', 'Breathable modest sports top.', NOW(), NOW(), 8, 24.99, 29.99);

INSERT INTO product_variants (sku, size, color, stock_qty, product_id)
VALUES
    ('SPT-001-S-BLACK', 'S', 'BLACK', 20, 15),
    ('SPT-001-M-GREY', 'M', 'GREY', 15, 15),
    ('SPT-002-M-WHITE', 'M', 'WHITE', 30, 16),
    ('SPT-002-L-BLUE', 'L', 'BLUE', 25, 16);

-- Long Skirts
INSERT INTO products (product_name, description, created_at, updated_at, category_id, price, original_price)
VALUES
    ('Pleated Long Skirt', 'Elegant pleated skirt for modest look.', NOW(), NOW(), 9, 29.99, 39.99),
    ('Denim Maxi Skirt', 'Casual denim maxi skirt.', NOW(), NOW(), 9, 34.99, 44.99);

INSERT INTO product_variants (sku, size, color, stock_qty, product_id)
VALUES
    ('SKT-001-S-BEIGE', 'S', 'BEIGE', 22, 17),
    ('SKT-001-M-BEIGE', 'M', 'BEIGE', 18, 17),
    ('SKT-002-M-BLUE', 'M', 'BLUE', 15, 18),
    ('SKT-002-L-BLUE', 'L', 'BLUE', 12, 18);

-- Home & Kitchen
INSERT INTO products (product_name, description, created_at, updated_at, category_id, price, original_price)
VALUES
    ('Ceramic Tea Set', 'Elegant tea set for guests.', NOW(), NOW(), 10, 49.99, 59.99),
    ('Prayer Mat', 'Soft prayer mat with geometric design.', NOW(), NOW(), 10, 19.99, 25.00);

INSERT INTO product_variants (sku, size, color, stock_qty, product_id)
VALUES
    ('HMK-001-STD-WHITE', 'STD', 'WHITE', 10, 19),
    ('HMK-001-STD-GOLD', 'STD', 'GOLD', 8, 19),
    ('HMK-002-STD-GREEN', 'STD', 'GREEN', 40, 20),
    ('HMK-002-STD-BLUE', 'STD', 'BLUE', 35, 20);









INSERT INTO images (url, variant_id) VALUES
('https://images.unsplash.com/photo-1655994688032-67a1b6474cf3', 1),
('https://images.unsplash.com/photo-1621167478060-296b5ef19e5b', 1),
('https://images.unsplash.com/photo-1655994688032-67a1b6474cf3', 2),
('https://images.unsplash.com/photo-1621167478060-296b5ef19e5b', 2),
('https://images.unsplash.com/photo-1609741873312-7ce5ae7c56b4?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',3),

('https://plus.unsplash.com/premium_photo-1661494087536-cdba32f3a2cc', 4),

('https://images.unsplash.com/photo-1730454809551-58c6afadec4c?q=80&w=686&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 28),
('https://images.unsplash.com/photo-1618407961072-5afd4ea27e41?q=80&w=721&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 30),

('https://images.unsplash.com/photo-1619253341026-74c609e6ce50?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 6),

('https://images.unsplash.com/photo-1659080546824-36c72edd5ef9?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTV8fENoaWZmb24lMjBIaWphYnxlbnwwfHwwfHx8MA%3D%3D', 24),
('https://images.unsplash.com/photo-1625987306773-8b9e554b25e2?q=80&w=765&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 26),

('https://images.unsplash.com/photo-1741783895531-ccc860eb946a?q=80&w=1025&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 9),
('https://picsum.photos/seed/product10/600/400', 10),
('https://images.unsplash.com/photo-1752794966299-1fd0ccade152?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Q2xhc3NpYyUyMEJsYWNrJTIwQWJheWF8ZW58MHx8MHx8fDA%3D', 11),

('https://images.unsplash.com/photo-1559278079-0bbb5e183b3d?q=80&w=627&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 32),
('https://images.unsplash.com/photo-1615387087938-312a574f825f?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 34),

('https://images.unsplash.com/photo-1517841905240-472988babdf9?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 20),
('https://plus.unsplash.com/premium_photo-1674719144570-0728faf14f96?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 22),

('https://images.unsplash.com/photo-1728487235101-664d87965931?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8T3BlbiUyMEZyb250JTIwQWJheWF8ZW58MHx8MHx8fDA%3D', 14),

('https://images.unsplash.com/photo-1548036328-c9fa89d128fa?q=80&w=1169&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 16),
('https://images.unsplash.com/photo-1652370626085-b90be216dbc0?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 16),
('https://images.unsplash.com/photo-1583623733237-4d5764a9dc82?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 17),
('https://images.unsplash.com/photo-1583623733245-34494135202c?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 17),
('https://plus.unsplash.com/premium_photo-1679483562579-023de24ab10f?q=80&w=627&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',18),
('https://images.unsplash.com/photo-1551607939-46fc8ac00815?q=80&w=715&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',36),
('https://plus.unsplash.com/premium_photo-1671379102281-7225f3d3d97d?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',38),
('https://images.unsplash.com/photo-1721373489867-b95a7b3fe16c?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Q2VyYW1pYyUyMFRlYSUyMFNldHxlbnwwfHwwfHx8MA%3D%3D',41),
('https://images.unsplash.com/photo-1743427158645-f89ff519b508?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8UHJheWVyJTIwTWF0fGVufDB8fDB8fHww',43);


UPDATE product_variants
SET status = 'INSTOCK'
WHERE status IS NULL;
