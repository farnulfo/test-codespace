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

public class CallTokenRequest {

    public static final boolean useProxy = false;
    public static void main(String[] args) throws Exception {
        // Construct the code grant from the code obtained from the authz endpoint
        // and the original callback URI used at the authz endpoint
        AuthorizationCode code = new AuthorizationCode(
                "9ad3eabd-6624-40a5-abb1-a6a56c49f488.4cea719f-fe5f-4174-920b-ea7371209c56.bcf09a2b-dfb2-4862-87d4-b5f5bea7432e");

        URI callback = new URI("https://client.com/callback");
        //URI callback = new URI("https://demo.c2id.com/oidc-client/cb");
        AuthorizationGrant codeGrant = new AuthorizationCodeGrant(code, callback);

        // The credentials to authenticate the client at the token endpoint
        //ClientID clientID = new ClientID("my-client");
        //Secret clientSecret = new Secret("1a3666ed-9d52-44c8-b874-bea93971ca1d");

        //ClientID clientID = new ClientID("000123");
        //Secret clientSecret = new Secret("rlC_8s3oBayCynAO_7UKt34hbEwiiTKx0l7zRcrFY3A");

        ClientID clientID = new ClientID("cat-app-id");
        Secret clientSecret = new Secret("9c1ef67f-e74e-4fd2-a81f-0ffb9a8aefb4");

        ClientAuthentication clientAuth = new ClientSecretBasic(clientID, clientSecret);

        // The token endpoint
        URI tokenEndpoint = new URI("https://lemur-1.cloud-iam.com/auth/realms/test-oidc/protocol/openid-connect/token");
        //URI tokenEndpoint = new URI("https://demo.c2id.com/token");

        // Make the token request
        TokenRequest request = new TokenRequest(tokenEndpoint, clientAuth, codeGrant);
        HTTPRequest httpRequest = request.toHTTPRequest();

        if (useProxy) {
            InetSocketAddress proxyAddressAndPort = new InetSocketAddress("localhost", 5050); 
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddressAndPort);
            httpRequest.setProxy(proxy); 
        }

        TokenResponse tokenResponse = OIDCTokenResponseParser.parse(httpRequest.send());

        if (!tokenResponse.indicatesSuccess()) {
            // We got an error response...
            TokenErrorResponse errorResponse = tokenResponse.toErrorResponse();
            System.out.println("TokenErrorResponse:" + errorResponse);
            System.out.println("TokenErrorResponse Headers:" + errorResponse.toHTTPResponse().	getHeaderMap());
            System.out.println("TokenErrorResponse Content:" + errorResponse.toHTTPResponse().getContent());
        } else {
            OIDCTokenResponse successResponse = (OIDCTokenResponse) tokenResponse.toSuccessResponse();

            // Get the ID and access token, the server may also return a refresh token
            JWT idToken = successResponse.getOIDCTokens().getIDToken();
            AccessToken accessToken = successResponse.getOIDCTokens().getAccessToken();
            RefreshToken refreshToken = successResponse.getOIDCTokens().getRefreshToken();

            System.out.println("JWT idtoken:" + idToken.serialize());
            System.out.println("AccessToken:" + accessToken);
            System.out.println("RefreshToken" + refreshToken);
        }
    }
}
