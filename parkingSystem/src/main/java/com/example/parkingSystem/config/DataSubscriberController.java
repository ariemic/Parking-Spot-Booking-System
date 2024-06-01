package com.example.parkingSystem.config;


import com.example.parkingSystem.dao.SubscriberRepository;
import com.example.parkingSystem.entity.Subscriber;
import com.example.parkingSystem.exceptions.SubscriberNotFoundException;
import com.example.parkingSystem.services.SubscriberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/subscribers")
public class DataSubscriberController {
    private final SubscriberRepository subscriberRepository;
    private final SubscriberService subscriberService;


    public DataSubscriberController(SubscriberRepository subscriberRepository, SubscriberService subscriberService) {
        this.subscriberRepository = subscriberRepository;
        this.subscriberService = subscriberService;
    }
    @DeleteMapping("/{carRegistration}")
    public ResponseEntity<String> deleteSubscriber(@PathVariable String carRegistration){
        try{
            String response = subscriberService.deleteSubscriber(carRegistration);
            return ResponseEntity.ok(response);
        }
        catch (SubscriberNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wystąpił nieoczekwiany błąd");
        }
    }

    @GetMapping("/{carRegistration}")
    public ResponseEntity<?> getSubscriber(@PathVariable String carRegistration){
        try{
            Subscriber subscriber = subscriberService.getSubscriber(carRegistration);
            return ResponseEntity.ok(subscriber);
        }
        catch (SubscriberNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wystąpił nieoczekwiany błąd");
        }
    }


    @PutMapping("/{carRegistration}")
    public ResponseEntity<?> putSubscriber(@PathVariable String carRegistration, @RequestBody Subscriber updatedSubscriber){
        try{
            Subscriber subscriber = subscriberService.putSubscriber(carRegistration, updatedSubscriber);
            return ResponseEntity.ok(subscriber);
        }catch (SubscriberNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wystąpił nieoczekwiany błąd");
        }
    }


    @PatchMapping("/{carRegistration}")
    public ResponseEntity<?> patchSubscriber(@PathVariable String carRegistration, @RequestBody Subscriber updatedSubscriber){
        try{
            Subscriber subscriber = subscriberService.patchSubscriber(carRegistration, updatedSubscriber);
            return ResponseEntity.ok(subscriber);
        }catch (SubscriberNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wystąpił nieoczekwiany błąd");
        }
    }
}
