CREATE TABLE notifications (
    id BIGSERIAL PRIMARY KEY,
    product VARCHAR(255),
    quantity INTEGER,
    price DOUBLE PRECISION,
    status VARCHAR(50)
);
