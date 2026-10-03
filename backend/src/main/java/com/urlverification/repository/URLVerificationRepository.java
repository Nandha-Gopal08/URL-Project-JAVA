package com.urlverification.repository;

import com.urlverification.model.URLVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface URLVerificationRepository extends JpaRepository<URLVerification, Long> {

    List<URLVerification> findByUserUserId(Long userId);

    List<URLVerification> findByUrlContainingIgnoreCase(String url);

    List<URLVerification> findAllByOrderByVerifiedAtDesc();
    List<URLVerification> findByDuplicate(Integer duplicate);
}