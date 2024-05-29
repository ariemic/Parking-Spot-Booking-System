package com.example.parkingSystem.config;


import com.example.parkingSystem.dao.SubscriberRepository;
import com.example.parkingSystem.exceptions.SubscriberNotFoundException;
import com.example.parkingSystem.services.SubscriberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class DataSubscriberController {
    private final SubscriberRepository subscriberRepository;
    private final SubscriberService subscriberService;


    public DataSubscriberController(SubscriberRepository subscriberRepository, SubscriberService subscriberService) {
        this.subscriberRepository = subscriberRepository;
        this.subscriberService = subscriberService;
    }
    @DeleteMapping("subscribers/{carRegistration}")
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





    @DeleteMapping("subscribers/{carRegistration}")
    public ResponseEntity<String> deleteSubscriber(@PathVariable String carRegistration){
        try{
            String respone = subscriberService.deleteSubscriber(carRegistration);
            return ResponseEntity.ok(respone);
        }
        catch (SubscriberNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wystąpił nieoczekwiany błąd");
        }
    }

}
