package com.polysocial.utils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
 
import java.net.URI; 
import java.math.BigDecimal; 

public class OTP {
    public final String ACCOUNT_SID = "AC99e28406402fe5f415a05aac2c9c2582"; 
    public final String AUTH_TOKEN = "fdde38b48aa0aa8ae5b0fbfb5a66a332"; 
 
    public void sendOTP(String phone, String otp){ 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message.creator( 
                new com.twilio.type.PhoneNumber(phone),   
                new com.twilio.type.PhoneNumber("+14793368735"),   
                otp)      
            .create(); 
    } 
}
