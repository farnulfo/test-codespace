package org.arnulfo;

import java.net.*;
import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.openid.connect.sdk.*;
import com.nimbusds.oauth2.sdk.id.*;

public class ParseResponse {
    public static void main(String[] args) throws Exception {

        // When the call back URI is invoked the response parameters
        // will be encoded in the query string, parse them
        // https://client.com/callback?
        // state=6SK5S15Lwdp3Pem_55m-ayudGwno0eglKq6ZEWaykG8
        // &code=eemeuWi9reingee0
        AuthenticationResponse response = AuthenticationResponseParser.parse(new URI("https://client.com/callback?state=pTwkwam0kfmuPx93QObcyhclvIMVH0W7Gs5FnFWl5vM&session_state=4cea719f-fe5f-4174-920b-ea7371209c56&code=9ad3eabd-6624-40a5-abb1-a6a56c49f488.4cea719f-fe5f-4174-920b-ea7371209c56.bcf09a2b-dfb2-4862-87d4-b5f5bea7432e"));

        State state = new State("pTwkwam0kfmuPx93QObcyhclvIMVH0W7Gs5FnFWl5vM");

        // Check the state
        if (!response.getState().equals(state)) {
            System.err.println("Unexpected authentication response");
            return;
        }

        if (response instanceof AuthenticationErrorResponse) {
            // The OpenID provider returned an error
            System.err.println(response.toErrorResponse().getErrorObject());
            return;
        }

        // Retrieve the authorisation code, to use it later at the token endpoint
        AuthorizationCode code = response.toSuccessResponse().getAuthorizationCode();
        System.out.println("AuthorizationCode:" + response.toSuccessResponse().getAuthorizationCode());

    }
}
