CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        product VARCHAR(255) NOT NULL,
                        quantity INT NOT NULL,
                        price NUMERIC(10, 2) NOT NULL
);