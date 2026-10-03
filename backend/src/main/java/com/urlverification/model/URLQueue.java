package com.urlverification.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "URL_QUEUE")
public class URLQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUEUE_ID")
    private Long queueId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "URL", nullable = false, length = 2048)
    private String url;

    @Column(name = "ADDED_AT")
    private LocalDateTime addedAt;

    public URLQueue() {
    }

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}