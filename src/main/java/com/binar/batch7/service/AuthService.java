package com.binar.batch7.service;


import com.binar.batch7.dto.auth.*;
import com.binar.batch7.dto.auth.*;
import com.binar.batch7.entity.User;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.security.Principal;

public interface AuthService {

    User register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    Object sendEmailOtp(EmailRequest request, String subject);

    Object confirmOtp(String otp);

    Object checkOtpValid(OtpRequest otp);

    Object resetPassword(ResetPasswordRequest request);

    User getCurrentUser(Principal principal);

    Object signWithGoogle(MultiValueMap<String, String> parameters) throws IOException;
}
