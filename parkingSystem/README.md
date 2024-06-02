# Parking Spot Booking System

Skład: Wojciech Orłowski, Ariel Michalik  
Serwer baz danych: MySQL
Backend: Java/Hibernate   
Temat: System rezerwacji miejsc parkingowych  
Opis: System rozwiązuje problem braku miejsc parkingowych dla samochodów osobowych. Osoby płacące abonament dokonują wyboru parkingu, a następnie przydzielane jest im miejsce.  

## Schemat bazy danych
<img width="723" alt="obraz" src="https://github.com/ariemic/Parking-Spot-Booking-System/assets/20191221/b9a0db6e-ac15-4b1a-8ffe-29d542d9c58d">



## Endpointy
1. http://localhost:8080/subscribers wyspisuje w postaci JSON wszystkich subskrybentów w bazie \
   > wyświela wszystkich subksrybentów 

   <img width="616" alt="obraz" src="https://github.com/ariemic/Parking-Spot-Booking-System/assets/20191221/1e2e0283-232e-4436-b284-6c4c11a9e020">
2. http://localhost:8080/parkings wypisuje w postaci JSON wszystkich \
   > wyświetla wszystkie dostępne parkingi 

   <img width="664" alt="obraz" src="https://github.com/ariemic/Parking-Spot-Booking-System/assets/20191221/34c1183b-d8f5-4f68-9aaf-4590de5dbf72">
   
3. http://localhost:8080/getAllAvailableParkings/RRRR-MM-DD \
   > wyświetla parkingi na, które jeszcze jest miejsce na dany dzień

   <img width="398" alt="obraz" src="https://github.com/ariemic/Parking-Spot-Booking-System/assets/20191221/8abd4bb7-6851-4cda-8860-61814ab15bfd">
   
4. 