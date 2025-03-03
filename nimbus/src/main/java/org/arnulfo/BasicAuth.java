package org.arnulfo;

import java.net.*;
import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.openid.connect.sdk.*;
import com.nimbusds.oauth2.sdk.id.*;
import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.*;
import com.nimbusds.oauth2.sdk.http.*;
import com.nimbusds.oauth2.sdk.id.*;
import com.nimbusds.oauth2.sdk.token.*;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jose.util.Base64;
import java.nio.charset.Charset;

public class BasicAuth {

    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    public static void main(String[] args) throws Exception {
        String clientid_clientsecret = "ddddddd:ddddsdfsdfsfdsdfsdfdd=";
        System.out.println("Base64 version nimbus " + Base64.encode(clientid_clientsecret.getBytes(UTF8_CHARSET)));
        System.out.println("Base64 version JDK " + 
            java.util.Base64.getEncoder().encodeToString(clientid_clientsecret.getBytes(java.nio.charset.Charset.forName("UTF-8"))));
    }
}
