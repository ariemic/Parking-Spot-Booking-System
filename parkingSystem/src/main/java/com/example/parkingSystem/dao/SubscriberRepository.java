package com.example.parkingSystem.dao;

import com.example.parkingSystem.entity.Subscriber;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SubscriberRepository extends JpaRepository<Subscriber, String> {
}
