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
        AuthenticationResponse response = AuthenticationResponseParser.parse(new URI("https://client.com/callback?state=VZv6j-HFdzQSqo0WWSizo_u14aZc2x5j0nJlf6R6Kn8&session_state=2e271ac4-944e-4a8d-aae2-c3a4a3fb6634&code=b8085b42-656a-427e-80ec-057bcc6cf35a.2e271ac4-944e-4a8d-aae2-c3a4a3fb6634.2b5dc2b9-7c77-44f4-8225-4972744f7ddb"));

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

    }
}
