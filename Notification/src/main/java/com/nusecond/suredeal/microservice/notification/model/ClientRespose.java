package com.nusecond.suredeal.microservice.notification.model;

import java.io.Serializable;

public class ClientRespose{

    /**
     *
     */
    private String username;
    private String userId;
    private String sessionId;
    private String Status;
    private String message;
    private String password;
    private String email;
    private String token;
    private String id;
    private String mode;
    private String otpRequestId;
    private String mobile;

    @Override
    public String toString() {
        return "ClientRespose [username=" + username + ", userId=" + userId + ", sessionId=" + sessionId + ", Status="
                + Status + "]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getOtpRequestId() {
        return otpRequestId;
    }

    public void setOtpRequestId(String otpRequestId) {
        this.otpRequestId = otpRequestId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
