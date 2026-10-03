package com.urlverification.repository;

import com.urlverification.model.URLQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface URLQueueRepository extends JpaRepository<URLQueue, Long> {

    List<URLQueue> findByUserUserId(Long userId);

}