use `parking-system`;


SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE bookings;
TRUNCATE TABLE parking;
TRUNCATE TABLE subscribers;
SET FOREIGN_KEY_CHECKS=1;


INSERT INTO parking (coordinates, address, max_slots)
VALUES
    ('52.22, 21.012', 'Mazowiecka 1, Warszawa', 40),
    ('50.06, 19.94', 'ul. Grodzka 10, Kraków', 10),
    ('50.06, 18.94', 'ul. Kawiory 10, Kraków', 8),
    ('51.10, 17.03', 'Rynek 2, Wrocław', 15),
    ('53.13, 23.16', 'ul. Lipowa 15, Białystok', 30),
    ('54.35, 18.64', 'Dluga 5, Gdańsk', 20);

INSERT INTO subscribers (car_registration, first_name, last_name, end_date, main_parking, all_parkings)
VALUES
    ('ABC123', 'Jan', 'Kowalski', '2024-12-31', 1, TRUE),
    ('DEF456', 'Anna', 'Nowak', '2024-11-30', 2, FALSE),
    ('GHI789', 'Piotr', 'Wiśniewski', '2024-10-31', 3, TRUE),
    ('JKL101', 'Maria', 'Wójcik', '2024-09-30', 4, FALSE),
    ('MNO112', 'Krzysztof', 'Kowalczyk', '2024-08-31', 5, TRUE),
    ('PQR131', 'Zofia', 'Kamińska', '2024-07-31', 6, FALSE),
    ('STU415', 'Tomasz', 'Lewandowski', '2024-06-30', 1, TRUE),
    ('VWX161', 'Katarzyna', 'Zielińska', '2024-06-30', 2, FALSE),
    ('YZA718', 'Paweł', 'Szymański', '2024-07-30', 3, TRUE),
    ('BCD192', 'Magdalena', 'Woźniak', '2024-08-31', 4, FALSE),
    ('EFG213', 'Michał', 'Dąbrowski', '2024-08-29', 5, TRUE),
    ('HIJ324', 'Agnieszka', 'Kozłowska', '2024-09-30', 6, FALSE),
    ('KLM435', 'Rafał', 'Jankowski', '2024-12-30', 1, TRUE),
    ('NOP546', 'Joanna', 'Wiśniewska', '2024-11-29', 2, FALSE),
    ('QRS657', 'Maciej', 'Wróblewski', '2024-10-28', 3, TRUE),
    ('TUV768', 'Ewa', 'Kamiński', '2024-09-27', 4, FALSE),
    ('WXY879', 'Adam', 'Zieliński', '2024-08-26', 5, TRUE),
    ('ZAB980', 'Magda', 'Szymczak', '2024-07-25', 6, FALSE),
    ('BCD101', 'Marek', 'Lewandowski', '2024-06-24', 1, TRUE),
    ('DEF212', 'Izabela', 'Kamińska', '2024-09-23', 2, FALSE),
    ('GHI323', 'Karol', 'Kozłowski', '2024-08-22', 3, TRUE),
    ('JKL434', 'Patrycja', 'Jankowska', '2024-09-21', 4, FALSE),
    ('MNO545', 'Dariusz', 'Kowal', '2024-09-20', 5, TRUE),
    ('PQR656', 'Aleksandra', 'Mazur', '2024-09-19', 6, FALSE),
    ('STU767', 'Wojciech', 'Nowicki', '2024-12-18', 1, TRUE),
    ('VWX878', 'Natalia', 'Kwiatkowska', '2024-11-17', 2, FALSE),
    ('YZA989', 'Andrzej', 'Wysocki', '2024-10-16', 3, TRUE),
    ('BCD100', 'Alicja', 'Głowacka', '2024-09-15', 4, FALSE),
    ('EFG211', 'Roman', 'Król', '2024-08-14', 5, TRUE),
    ('HIJ322', 'Agnieszka', 'Adamska', '2024-07-13', 6, FALSE),
    ('KLM433', 'Jerzy', 'Pawlak', '2024-06-12', 1, TRUE),
    ('NOP544', 'Łukasz', 'Kaczmarek', '2024-11-11', 2, FALSE),
    ('QRS655', 'Renata', 'Borkowska', '2024-11-10', 3, TRUE),
    ('TUV766', 'Mariusz', 'Piotrowski', '2024-06-09', 4, FALSE),
    ('WXY877', 'Sylwia', 'Sikorska', '2024-08-08', 5, TRUE),
    ('ZAB988', 'Wiesław', 'Duda', '2024-08-07', 6, FALSE),
    ('BCD109', 'Martyna', 'Lis', '2024-12-06', 1, TRUE),
    ('DEF210', 'Patryk', 'Kurowski', '2024-11-05', 2, FALSE),
    ('GHI321', 'Urszula', 'Kowalewska', '2024-10-04', 3, TRUE),
    ('JKL432', 'Marcin', 'Wilk', '2024-09-03', 4, FALSE),
    ('MNO543', 'Iwona', 'Chmielewska', '2024-08-02', 5, TRUE),
    ('PQR654', 'Sebastian', 'Urbaniak', '2024-07-01', 6, FALSE);

