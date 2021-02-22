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
        AuthenticationResponse response = AuthenticationResponseParser.parse(new URI("https://client.com/callback?state=kxTqnjmo-IrkxFa6_K3unDxh-kPkWOTLLNAK3j3l8S0&session_state=51f47978-3a5e-41ca-93ce-11447393c294&code=f605bc89-8b90-4e7d-8652-1cef79d9e7c8.51f47978-3a5e-41ca-93ce-11447393c294.2b5dc2b9-7c77-44f4-8225-4972744f7ddb"));

        State state = new State("kxTqnjmo-IrkxFa6_K3unDxh-kPkWOTLLNAK3j3l8S0");

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
        System.out.println(response.toSuccessResponse().getAuthorizationCode());

    }
}
