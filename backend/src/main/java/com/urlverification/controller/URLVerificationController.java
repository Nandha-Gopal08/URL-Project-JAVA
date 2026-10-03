package com.urlverification.controller;

import com.urlverification.model.URLQueue;
import com.urlverification.model.URLVerification;
import com.urlverification.service.URLVerificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/urls")
public class URLVerificationController {

    private final URLVerificationService service;

    public URLVerificationController(URLVerificationService service) {
        this.service = service;
    }

    @PostMapping("/verify")
    public String verifyURL(
            @RequestParam String url,
            @RequestParam String email) {

        return service.verifyURL(url, email);
    }

    @GetMapping("/history")
    public List<URLVerification> getHistory() {

        return service.getHistory();
    }

    @GetMapping("/history/search")
    public List<URLVerification> searchHistory(
            @RequestParam String url) {

        return service.searchHistory(url);
    }

    @GetMapping("/duplicates")
    public List<URLVerification> getDuplicateURLs() {

        return service.getDuplicateURLs();
    }

    @PostMapping("/queue")
    public String addToQueue(
            @RequestParam String url,
            @RequestParam String email) {

        return service.addToQueue(url, email);
    }

    @GetMapping("/queue")
    public List<URLQueue> getQueue() {

        return service.getQueue();
    }

    @PostMapping("/queue/process-next")
    public String processNextURL() {

        return service.processNextURL();
    }

    @PostMapping("/queue/process-all")
    public String processAllURLs() {

        return service.processAllURLs();
    }
}