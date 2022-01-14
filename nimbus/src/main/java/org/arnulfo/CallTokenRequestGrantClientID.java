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

public class CallTokenRequestGrantClientID {

    public static final boolean useProxy = false;
    public static void main(String[] args) throws Exception {

        URI callback = new URI("https://client.com/callback");
        AuthorizationGrant grant = new ClientCredentialsGrant();

        ClientID clientID = new ClientID("cat-app-id");
        Secret clientSecret = new Secret("9c1ef67f-e74e-4fd2-a81f-0ffb9a8aefb4");

        ClientAuthentication clientAuth = new ClientSecretBasic(clientID, clientSecret);

        // The token endpoint
        URI tokenEndpoint = new URI("https://lemur-1.cloud-iam.com/auth/realms/test-oidc/protocol/openid-connect/token");

        // Make the token request
        TokenRequest request = new TokenRequest(tokenEndpoint, clientAuth, grant);
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

            System.out.println("JWT idtoken: " + (idToken == null ? "null" : idToken.serialize()));
            System.out.println("AccessToken: " + accessToken);
            System.out.println("RefreshToken: " + refreshToken);
        }
    }
}
