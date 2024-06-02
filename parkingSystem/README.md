# Parking Spot Booking System

Skład: Wojciech Orłowski, Ariel Michalik  
Serwer baz danych: MySQL
Backend: Java/Hibernate   
Temat: System rezerwacji miejsc parkingowych  
Opis: System rozwiązuje problem braku miejsc parkingowych dla samochodów osobowych. Osoby płacące abonament dokonują wyboru parkingu, a następnie przydzielane jest im miejsce.  

## Schemat bazy danych
<img width="723" alt="obraz" src="https://github.com/ariemic/Parking-Spot-Booking-System/assets/20191221/b9a0db6e-ac15-4b1a-8ffe-29d542d9c58d">

## Entities
> Booking
```java
@Entity
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "parking_Id")
    private int parkingId;



    @Column(name="car_registration")
    private String subscriberCarRegistration;

    @Column(name = "booking_date")
    private String bookingDate;


    public int getParkingId() {
        return parkingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getSubscriberCarRegistration() {
        return subscriberCarRegistration;
    }

}

```
> Parking

```java
@Entity
@Data
@Table(name="parking")
public class Parking {

    @Id
    @Column(name="parking_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parkingId;

    @Column(name="coordinates")
    private String coordinates;
    //address like {ul.} Mazowiecka number, city
    @Column(name="address")
    @Pattern(regexp = "\\p{L}+ \\d+, \\p{L}+", message = "enter address as street number, city")
    private String address;

    @Column(name = "max_slots")
    @Min(value = 0, message = "maximum number of slots must be non-negative number")
    private int maxSlots;
    
}

```

> Subscriber

```java
@Entity
@Data
@Table(name = "subscribers")

public class Subscriber {

   @Id
   @Column(name = "car_registration", nullable = false)
   @Pattern(regexp = "\\p{L}+\\d+")
   private String carRegistration;


   @Column(name = "first_name")
   @NotBlank(message = "your name can't be empty")
   private String firstName;

   @NotBlank(message = "your surname can't be empty")
   @Column(name = "last_name")
   private String lastName;


   @Column(name = "end_date")
   private Date endDate;

   @Column(name = "main_parking")
   private Integer mainParking;

   @Column(name = "all_parkings")
   private boolean allParkings;

   public @NotBlank(message = "your name can't be empty") String getFirstName() {
      return firstName;
   }

   public @NotBlank(message = "your surname can't be empty") String getLastName() {
      return lastName;
   }


   public Integer getMainParking() {
      return mainParking;
   }

   public boolean isAllParkings() {
      return allParkings;
   }
   

}

```


## Dto - Data Transfer Objects
> Klasy potrzebne do reprezentacji endpointow, nie znajdujące się w bazie danych

> ParkingDetails - dodatkowe informacje o danym parkingu
```java
@Data
public class ParkingDetails {

    private int parkingId;
    private String coordinates;
    private String address;
    private int maxSlots;
    private int freeSlots;
    private String percentageParkingLoad;

    public ParkingDetails(Parking parking, int freeSlots) {
        this.parkingId = parking.getParkingId();
        this.coordinates = parking.getCoordinates();
        this.address = parking.getAddress();
        this.maxSlots = parking.getMaxSlots();
        this.freeSlots = freeSlots;
        this.percentageParkingLoad = calculatePercentageParkingLoad();
    }

   private String calculatePercentageParkingLoad() {
      double parkingLoad = ((double)(freeSlots) / maxSlots) * 100;
      double parkingLoad = ((double)(maxSlots - freeSlots) / maxSlots) * 100;
      return String.format("%.0f%%", parkingLoad);
   }

}

```

> ParkingSubscribers - podstawowe informację o parkingu oraz lista subskrybentów z aktywną licencją na ten parking
```java
@Data
public class ParkingSubscribers {
   private int parkingId;
   private String coordinates;
   private String address;
   private List<SubscriberDto> subscribers;
   
}

```


> ParkingSummary - szczegółowe informacje o parkingach
```java

public class ParkingSummary {
   private Parking parking;
   private int totalCaluclatedDays;
   private int totalParkedSum;
   private float AvarageDailyParkedSum;
   private int maxBookedSlots;
   private float AvarageDailyParkedPercentage;
   private float maxDailyBookedPercentage;

   public ParkingSummary(Parking parking) {
      this.parking = parking;
   }

   public Parking getParking() {
      return parking;
   }

   public int getTotalCaluclatedDays() {
      return totalCaluclatedDays;
   }

   public void setTotalCaluclatedDays(int totalCaluclatedDays) {
      this.totalCaluclatedDays = totalCaluclatedDays;
   }

   public int getTotalParkedSum() {
      return totalParkedSum;
   }

   public void setTotalParkedSum(int totalParkedSum) {
      this.totalParkedSum = totalParkedSum;
   }

   public float getAvarageDailyParkedSum() {
      return AvarageDailyParkedSum;
   }

   public void setAvarageDailyParkedSum(float avarageDailyParkedSum) {
      AvarageDailyParkedSum = avarageDailyParkedSum;
   }

   public int getMaxBookedSlots() {
      return maxBookedSlots;
   }

   public void setMaxBookedSlots(int maxBookedSlots) {
      this.maxBookedSlots = maxBookedSlots;
   }

   public float getAvarageDailyParkedPercentage() {
      return AvarageDailyParkedPercentage;
   }

   public void setAvarageDailyParkedPercentage(float avarageDailyParkedPercentage) {
      AvarageDailyParkedPercentage = avarageDailyParkedPercentage;
   }

   public float getMaxDailyBookedPercentage() {
      return maxDailyBookedPercentage;
   }

   public void setMaxDailyBookedPercentage(float maxDailyBookedPercentage) {
      this.maxDailyBookedPercentage = maxDailyBookedPercentage;
   }
}


```
> SubscriberDto - podstawowe informacje o subskrybencie
```java
@AllArgsConstructor
@Data
public class SubscriberDto {
   private String carRegistration;
   private String firstName;
   private String lastName;
}


```


## Dao - Data Access Objects
> Interfejsy zawierające implementacje podstawowych metod sqlowych typu select do znalezienia danego przez obiektu w repozytorium

> BookingRepository
```java
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBookingByBookingId(int id);
    List<Booking> findBookingBySubscriberCarRegistration(String CarRegistration);

}

```

> ParkingRepository
```java
public interface ParkingRepository extends JpaRepository<Parking, Integer> {
   Parking findByParkingId(int id);

}

```


> SubscriberRepository
```java
public interface SubscriberRepository extends JpaRepository<Subscriber, String> {

}


```

## Services 
>  pośredniczą między kontrolerami a repozytoriami, zarządzając operacjami biznesowymi i transakcyjnymi na danych przechowywanych w bazie danych

> BookingService
```java
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ParkingService parkingService;

    private final SubscriberService subscriberService;
    private final ParkingRepository parkingRepository;


    @Autowired
    public BookingService(BookingRepository bookingRepository, ParkingService parkingService, SubscriberService subscriberService, ParkingRepository parkingRepository) {
        this.bookingRepository = bookingRepository;
        this.parkingService = parkingService;
        this.subscriberService = subscriberService;
        this.parkingRepository = parkingRepository;
    }

    public ResponseEntity<Booking> findBookingById(int bookingId){
        try {
           Booking foundBooking = bookingRepository.findBookingByBookingId(bookingId);
            if (foundBooking != null) {
                return ResponseEntity.ok(foundBooking);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    public void saveBooking(Booking bookingToSave) throws ParkingNotFoundException, SubscriberNotFoundException{
        String carRegistration = bookingToSave.getSubscriberCarRegistration();
        int parkingId = bookingToSave.getParkingId();
        String bookingDate = bookingToSave.getBookingDate();
        if(!subscriberService.subscriberExist(carRegistration)) {
            throw new SubscriberNotFoundException();
        }

        if (!parkingService.parkingExist(parkingId)) {
            throw new ParkingNotFoundException();
        }

        Parking parking = parkingRepository.findByParkingId(parkingId);



        if (!subscriberService.hasLicense(carRegistration, parkingId)) {
            throw new IllegalStateException("Subskrybent o numerze rejestracyjnym " + carRegistration +
                    " nie posiada licencji na parking: " + parkingId);
        }


        if (!parkingService.listAvailableParkings(bookingDate).contains(parking)) {
            throw new IllegalStateException("W dniu " + bookingDate +
                    " nie mamy już miejsca na parking " + parkingId);
        }

        bookingRepository.save(bookingToSave);
    }

    
}

```



## Dostępne endpointy
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