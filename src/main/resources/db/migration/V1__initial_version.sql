CREATE TABLE IF NOT EXISTS MethodTime(
    id SERIAL PRIMARY KEY,
    method_name VARCHAR(255) NOT NULL,
    class_name VARCHAR(255) NOT NULL,
    call_count INTEGER NOT NULL,
    work_time BIGINT  NOT NULL
);