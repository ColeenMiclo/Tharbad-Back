DROP TABLE IF EXISTS dbusertharbad;

CREATE TABLE dbusertharbad (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

INSERT INTO dbusertharbad (username, password, role) VALUES
('admin', '$2a$10$e0c5b1f3d4c8a7e9f8b6Oe1Qz5g5Z5Z5Z5Z5Z5Z5Z5Z5Z5Z5Z5', 'ADMIN'),
('user', '$2a$10$e0c5b1f3d4c8a7e9f8b6Oe1Qz5g5Z5Z5Z5Z5Z5Z5Z5Z5Z5Z5Z', 'USER');

