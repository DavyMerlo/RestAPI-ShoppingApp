package com.davy.restapi.authetication.service;

import com.davy.restapi.authetication.request.AuthenticationRequest;
import com.davy.restapi.authetication.request.RegisterRequest;
import com.davy.restapi.authetication.response.AuthenticationResponse;
import com.davy.restapi.authetication.response.ConfirmTokenResponse;
import com.davy.restapi.authetication.response.RegisterResponse;
import com.davy.restapi.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void saveUserToken(User user, String jwtToken);
    User createDemoUser();
    ConfirmTokenResponse confirmToken(String token);
    Object getAccountStatus(Long userId);
}
