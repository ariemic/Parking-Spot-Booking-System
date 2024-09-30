use `parking-system`;

CREATE TABLE IF NOT EXISTS bookings (
                                        booking_id INT AUTO_INCREMENT PRIMARY KEY,
                                        parking_Id INT NOT NULL,
                                        car_registration VARCHAR(255) NOT NULL,
    booking_date VARCHAR(255) NOT NULL,
    CONSTRAINT fk_parking FOREIGN KEY (parking_Id) REFERENCES parking(parking_Id),
    CONSTRAINT fk_subscriber FOREIGN KEY (car_registration) REFERENCES subscribers(car_registration)
    );

CREATE TABLE IF NOT EXISTS parking (
                                       parking_Id INT AUTO_INCREMENT PRIMARY KEY,
                                       coordinates VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    max_slots INT CHECK (max_slots >= 0)
    );

CREATE TABLE IF NOT EXISTS subscribers (
    car_registration VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    end_date DATE,
    main_parking INT,
    all_parkings BOOLEAN,
    CONSTRAINT fk_main_parking FOREIGN KEY (main_parking) REFERENCES parking(parking_Id)
    );
