
CREATE TABLE IF NOT EXISTS "user" (
    "id" BIGINT AUTO_INCREMENT PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL,
    "email" VARCHAR(100) NOT NULL,
    "password" VARCHAR(100) NOT NULL,
    "status" VARCHAR(50) NOT NULL,
    "role" VARCHAR(50) NOT NULL,
    "address" VARCHAR(100) NOT NULL,
    "registered_at" DATETIME,
    "unregistered_at" DATETIME,
    "last_login_at" DATETIME
);

CREATE TABLE IF NOT EXISTS "product" (
    "id" BIGINT AUTO_INCREMENT PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    "barcode" VARCHAR(200),
    "photo_url" VARCHAR(200),
    "cost" DECIMAL(12,4),
    "price" DECIMAL(12,4),
    "created_at" DATETIME,
    "updated_at" DATETIME,
    "create_by" BIGINT,
    "update_by" BIGINT
);

CREATE TABLE IF NOT EXISTS "attribution" (
    "id" BIGINT AUTO_INCREMENT PRIMARY KEY,
    "attribution_type" VARCHAR(20) NOT NULL,
    "name" VARCHAR(100) NOT NULL,
    "value" VARCHAR(100),
    "order" INT,
    "created_at" DATETIME,
    "updated_at" DATETIME,
    "create_by" BIGINT,
    "update_by" BIGINT
);

CREATE TABLE IF NOT EXISTS "transaction" (
    "id" BIGINT AUTO_INCREMENT PRIMARY KEY,
    "location_id" BIGINT,
    "partner_id" BIGINT,
    "quantity" INT,
    "memo" VARCHAR(200),
    "status" VARCHAR(50),
    "type" VARCHAR(50),
    "created_at" DATETIME,
    "updated_at" DATETIME,
    "create_by" BIGINT,
    "update_by" BIGINT
);
CREATE TABLE IF NOT EXISTS "partner" (
    "id" BIGINT AUTO_INCREMENT PRIMARY KEY,
    "partner_type" VARCHAR(20) NOT NULL,
    "name" VARCHAR(50) NOT NULL,
    "phone_number" VARCHAR(100),
    "email" VARCHAR(100),
    "address" VARCHAR(50),
    "created_at" DATETIME,
    "updated_at" DATETIME,
    "create_by" BIGINT,
    "update_by" BIGINT
);

CREATE TABLE IF NOT EXISTS "location" (
    "id" BIGINT AUTO_INCREMENT PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL,
    "memo" VARCHAR(200),
    "created_at" DATETIME,
    "updated_at" DATETIME,
    "create_by" BIGINT,
    "update_by" BIGINT
);
