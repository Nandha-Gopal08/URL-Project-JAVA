package com.urlverification.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "URL_VERIFICATIONS")
public class URLVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VERIFICATION_ID")
    private Long verificationId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "URL", nullable = false, length = 2048)
    private String url;

    @Column(name = "STATUS_CODE")
    private Integer statusCode;

    @Column(name = "REACHABLE")
    private Integer reachable;

    @Column(name = "HTTPS")
    private Integer https;

    @Column(name = "RESPONSE_TIME")
    private Long responseTime;

    @Column(name = "REDIRECTED")
    private Integer redirected;

    @Column(name = "REDIRECT_INFO", length = 2048)
    private String redirectInfo;

    @Column(name = "DUPLICATE")
    private Integer duplicate;

    @Column(name = "MESSAGE", length = 500)
    private String message;

    @Column(name = "VERIFIED_AT")
    private LocalDateTime verifiedAt;

    public URLVerification() {
    }

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
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

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getReachable() {
        return reachable;
    }

    public void setReachable(Integer reachable) {
        this.reachable = reachable;
    }

    public Integer getHttps() {
        return https;
    }

    public void setHttps(Integer https) {
        this.https = https;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getRedirected() {
        return redirected;
    }

    public void setRedirected(Integer redirected) {
        this.redirected = redirected;
    }

    public String getRedirectInfo() {
        return redirectInfo;
    }

    public void setRedirectInfo(String redirectInfo) {
        this.redirectInfo = redirectInfo;
    }

    public Integer getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(Integer duplicate) {
        this.duplicate = duplicate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}