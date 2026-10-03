package com.urlverification.service;

import com.urlverification.checker.URLChecker;
import com.urlverification.checker.URLCheckResult;
import com.urlverification.model.URLVerification;
import com.urlverification.model.URLQueue;
import com.urlverification.model.User;
import com.urlverification.repository.URLVerificationRepository;
import com.urlverification.repository.URLQueueRepository;
import com.urlverification.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class URLVerificationService {

    private final URLChecker urlChecker;
private final URLVerificationRepository repository;
private final URLQueueRepository queueRepository;
private final UserRepository userRepository;
   public URLVerificationService(
        URLVerificationRepository repository,
        URLQueueRepository queueRepository,
        UserRepository userRepository) {

    this.repository = repository;
    this.queueRepository = queueRepository;
    this.userRepository = userRepository;
    this.urlChecker = new URLChecker();
}

    public String verifyURL(String url, String email) {

    if (url == null || url.isEmpty()) {
        return "URL cannot be empty";
    }

    User user = userRepository.findByEmail(email).orElse(null);

    if (user == null) {
        return "User not found";
    }

    URLCheckResult result = urlChecker.checkURL(url);

    List<URLVerification> previousRecords =
            repository.findByUrlContainingIgnoreCase(url);

    boolean duplicate = !previousRecords.isEmpty();

    URLVerification verification = new URLVerification();

    verification.setUser(user);
    verification.setUrl(url);

    verification.setStatusCode(result.getStatusCode());
    verification.setReachable(result.isReachable() ? 1 : 0);
    verification.setHttps(result.isHttps() ? 1 : 0);
    verification.setResponseTime(result.getResponseTime());
    verification.setRedirected(result.isRedirected() ? 1 : 0);
    verification.setRedirectInfo(result.getRedirectInfo());
    verification.setDuplicate(duplicate ? 1 : 0);
    verification.setVerifiedAt(LocalDateTime.now());

    if (result.getStatusCode() == -1) {
        verification.setMessage("URL could not be reached");
    } else {
        verification.setMessage("URL verified successfully");
    }

    repository.save(verification);

    if (result.getStatusCode() == -1) {
        return "URL could not be reached";
    }

    return "URL verified successfully. Status Code: "
            + result.getStatusCode()
            + ", Response Time: "
            + result.getResponseTime()
            + " ms";
}

public List<URLVerification> getHistory() {

    return repository.findAllByOrderByVerifiedAtDesc();
}
public List<URLVerification> searchHistory(String url) {

    return repository.findByUrlContainingIgnoreCase(url);
}
public List<URLVerification> getDuplicateURLs() {

    return repository.findByDuplicate(1);
}
public String addToQueue(String url, String email) {

    if (url == null || url.isEmpty()) {
        return "URL cannot be empty";
    }
    User user = userRepository.findByEmail(email).orElse(null);

    if (user == null) {
        return "User not found";
    }

   URLQueue queue = new URLQueue();

queue.setUser(user);
queue.setUrl(url);
    queue.setAddedAt(LocalDateTime.now());

    queueRepository.save(queue);

    return "URL added to queue successfully";
}
public List<URLQueue> getQueue() {

    return queueRepository.findAll();
}
public String processNextURL() {

    List<URLQueue> queue = queueRepository.findAll();

    if (queue.isEmpty()) {
        return "Queue is empty";
    }

    URLQueue firstURL = queue.get(0);

    String url = firstURL.getUrl();
    String email = firstURL.getUser().getEmail();

    String result = verifyURL(url, email);

    queueRepository.delete(firstURL);

    return result;
}
public String processAllURLs() {

    List<URLQueue> queue = queueRepository.findAll();

    if (queue.isEmpty()) {
        return "Queue is empty";
    }

    int count = 0;

    for (URLQueue item : queue) {

        String url = item.getUrl();
        String email = item.getUser().getEmail();

        verifyURL(url, email);

        queueRepository.delete(item);

        count++;
    }

    return count + " URLs processed successfully";
}

}
