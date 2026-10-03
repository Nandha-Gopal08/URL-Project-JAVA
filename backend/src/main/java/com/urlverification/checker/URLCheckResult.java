package com.urlverification.checker;

public class URLCheckResult {

    private int statusCode;
    private boolean reachable;
    private boolean https;
    private long responseTime;
    private boolean redirected;
    private String redirectInfo;

    public URLCheckResult(
            int statusCode,
            boolean reachable,
            boolean https,
            long responseTime,
            boolean redirected,
            String redirectInfo) {

        this.statusCode = statusCode;
        this.reachable = reachable;
        this.https = https;
        this.responseTime = responseTime;
        this.redirected = redirected;
        this.redirectInfo = redirectInfo;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isReachable() {
        return reachable;
    }

    public boolean isHttps() {
        return https;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public boolean isRedirected() {
        return redirected;
    }

    public String getRedirectInfo() {
        return redirectInfo;
    }
}