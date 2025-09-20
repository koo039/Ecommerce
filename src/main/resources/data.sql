INSERT INTO roles (id, role_name) VALUES
                                 (1, 'ROLE_USER'),
                                 (2, 'ROLE_ADMIN');
INSERT INTO users (first_name, last_name, username, email, phone, password_hash, created_at, updated_at, role_id)
VALUES
    ( 'John', 'Doe', 'user1', 'user1@example.com', '+21360000001', 'hashedPassword1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ( 'Jane', 'Smith', 'user2', 'user2@example.com', '+21360000002', 'hashedPassword2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ( 'Ali', 'Karim', 'user3', 'user3@example.com', '+21360000003', 'hashedPassword3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ( 'Sara', 'Amar', 'user4', 'user4@example.com', '+21360000004', 'hashedPassword4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO categories (category_name)
VALUES
    ('Dresses'),
    ('Shoes'),
    ('Accessories'),
    ('Bags'),
    ('Jackets'),
    ('Watches'),
    ('Beauty'),
    ('Sportswear'),
    ('Electronics'),
    ('Home & Kitchen');

INSERT INTO products (product_name, description, created_at, updated_at, category_id)
VALUES
    ('Summer Dress', 'Light cotton summer dress with floral pattern.', NOW(), NOW(), 1),
    ('Evening Gown', 'Elegant gown for formal events.', NOW(), NOW(), 1),
    ('Running Shoes', 'Comfortable running shoes with breathable mesh.', NOW(), NOW(), 2),
    ('Leather Boots', 'High-quality leather boots for winter.', NOW(), NOW(), 2),
    ('Smartwatch', 'Fitness tracking smartwatch with heart rate monitor.', NOW(), NOW(), 6),
    ('Luxury Watch', 'Premium watch with sapphire glass.', NOW(), NOW(), 6),
    ('Backpack', 'Durable backpack for daily use.', NOW(), NOW(), 4),
    ('Handbag', 'Stylish handbag made of genuine leather.', NOW(), NOW(), 4),
    ('Sports Jacket', 'Lightweight jacket suitable for outdoor activities.', NOW(), NOW(), 5),
    ('Casual Jacket', 'Trendy denim jacket.', NOW(), NOW(), 5),
    ('Blender', 'High-speed blender for smoothies.', NOW(), NOW(), 10),
    ('Coffee Maker', 'Automatic coffee machine with grinder.', NOW(), NOW(), 10),
    ('Lipstick Set', 'Collection of matte lipsticks.', NOW(), NOW(), 7),
    ('Perfume', 'Luxury fragrance for women.', NOW(), NOW(), 7),
    ('Laptop', 'Lightweight laptop with SSD storage.', NOW(), NOW(), 9),
    ('Smartphone', 'Latest smartphone with OLED display.', NOW(), NOW(), 9);

INSERT INTO product_variants (sku, size, color, price, stock_qty, product_id)
VALUES
    -- Variants for Summer Dress (product_id = 1)
    ('DRS-001-S-RED', 'S', 'RED', 39.99, 50, 1),
    ('DRS-001-M-RED', 'M', 'RED', 39.99, 60, 1),
    ('DRS-001-L-BLUE', 'L', 'BLUE', 42.99, 40, 1),

    -- Variants for Evening Gown (product_id = 2)
    ('GWN-002-M-BLACK', 'M', 'BLACK', 89.99, 25, 2),
    ('GWN-002-L-BLACK', 'L', 'BLACK', 89.99, 20, 2),

    -- Variants for Running Shoes (product_id = 3)
    ('SHO-003-40-WHITE', 'M', 'WHITE', 59.99, 80, 3),
    ('SHO-003-41-BLACK', 'M', 'BLACK', 59.99, 70, 3),
    ('SHO-003-42-BLUE', 'L', 'BLUE', 64.99, 65, 3),

    -- Variants for Leather Boots (product_id = 4)
    ('BOO-004-41-BROWN', 'M', 'BROWN', 120.00, 30, 4),
    ('BOO-004-42-BLACK', 'L', 'BLACK', 125.00, 25, 4),

    -- Variants for Smartwatch (product_id = 5)
    ('SWT-005-STD-BLACK', 'M', 'BLACK', 199.99, 100, 5),
    ('SWT-005-STD-SILVER', 'M', 'WHITE', 209.99, 90, 5),

    -- Variants for Luxury Watch (product_id = 6)
    ('LWX-006-STD-GOLD', 'M', 'RED', 899.99, 15, 6),
    ('LWX-006-STD-SILVER', 'M', 'WHITE', 799.99, 20, 6),

    -- Variants for Backpack (product_id = 7)
    ('BKP-007-S-BLACK', 'M', 'BLACK', 49.99, 120, 7),
    ('BKP-007-L-GREEN', 'L', 'GREEN', 59.99, 100, 7);

INSERT INTO reviews (user_id,comment, rate, created_at, updated_at, product_id) VALUES
-- Reviews for Summer Dress (product_id = 1)
(1,'Great quality, fits perfectly!', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(2,'Color is nice but size runs small.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(3,'Not bad, but delivery was late.', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),

-- Reviews for Evening Gown (product_id = 2)
(2,'Excellent value for the price!', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(2,'Material feels cheap, disappointed.', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(2,'Looks beautiful, perfect for the event.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),

-- Reviews for Running Shoes (product_id = 3)
(3,'Very comfortable for jogging.', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
(3,'Sole started wearing out quickly.', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
(3,'Breathable and lightweight, love them.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),

-- Reviews for Leather Boots (product_id = 4)
(4,'Excellent craftsmanship, worth the price.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
(4,'A bit tight at first but stretches out.', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),

-- Reviews for Smartwatch (product_id = 5)
(1,'Amazing features, battery lasts long.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
(1,'Difficult to connect with phone at first.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),

-- Reviews for Backpack (product_id = 7)
(2,'Spacious and durable, great for travel.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7),
(2,'Zipper broke after a month, disappointed.', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7),

-- Reviews for Laptop (product_id = 15)
(3,'Super fast and lightweight, perfect for work.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15),
(4,'Overheats a bit when gaming.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15);

INSERT INTO carts (created_at, updated_at,user_id) VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,1), -- Cart for User 1
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,1), -- Cart for User 2
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,1), -- Cart for User 3
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,1), -- Another cart for User 1 (e.g. old cart)
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,1), -- Cart for User 4
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,1), -- Cart for User 5
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,1), -- Another cart for User 2
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,1); -- Cart for User 6

INSERT INTO cart_items (quantity, cart_id, variant_id) VALUES
                                                           -- Cart 1 (User 1’s active cart)
   (2, 1, 1),  -- 2x Summer Dress (variant 1)
   (1, 1, 6),  -- 1x Running Shoes (variant 6)

   -- Cart 2 (User 2’s cart)
   (3, 2, 7),  -- 3x Running Shoes (variant 7)
   (1, 2, 11), -- 1x Smartwatch (variant 11)

   -- Cart 3 (User 3’s cart)
   (1, 3, 9),  -- 1x Leather Boots (variant 9)
   (2, 3, 15), -- 2x Backpack (variant 15)

   -- Cart 4 (User 1’s old cart)
   (1, 4, 4),  -- 1x Evening Gown (variant 4)
   (2, 4, 2),  -- 2x Summer Dress (variant 2)

   -- Cart 5 (User 4’s cart)
   (1, 5, 13), -- 1x Luxury Watch (variant 13)

   -- Cart 6 (User 5’s cart)
   (2, 6, 8),  -- 2x Running Shoes (variant 8)
   (1, 6, 10), -- 1x Leather Boots (variant 10)

   -- Cart 7 (User 2’s old cart)
   (1, 7, 3),  -- 1x Summer Dress (variant 3)
   (1, 7, 5),  -- 1x Evening Gown (variant 5)

   -- Cart 8 (User 6’s cart)
   (1, 8, 12), -- 1x Smartwatch (variant 12)
   (2, 8, 16); -- 2x Backpack (variant 16)


INSERT INTO wishlists (created_at, updated_at, user_id, product_id) VALUES
                                                                        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
                                                                        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 9),
                                                                        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 3),
                                                                        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 1),
                                                                        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 4);
INSERT INTO images (url, variant_id) VALUES
('https://images.unsplash.com/photo-1655994688032-67a1b6474cf3', 1),
('https://images.unsplash.com/photo-1621167478060-296b5ef19e5b', 2),
('https://plus.unsplash.com/premium_photo-1661494087536-cdba32f3a2cc', 3),
('https://images.unsplash.com/photo-1723813196516-fc9dff5f8c0d', 4),
('https://images.unsplash.com/photo-1723813196654-ddb9003a77f6', 5),
('https://picsum.photos/seed/product6/600/400', 6),
('https://picsum.photos/seed/product7/600/400', 7),
('https://picsum.photos/seed/product8/600/400', 8),
('https://picsum.photos/seed/product9/600/400', 9),
('https://picsum.photos/seed/product10/600/400', 10),
('https://picsum.photos/seed/product11/600/400', 11),
('https://picsum.photos/seed/product12/600/400', 12),
('https://picsum.photos/seed/product13/600/400', 13),
('https://picsum.photos/seed/product14/600/400', 14),
('https://picsum.photos/seed/product15/600/400', 15),
('https://picsum.photos/seed/product16/600/400', 16);


