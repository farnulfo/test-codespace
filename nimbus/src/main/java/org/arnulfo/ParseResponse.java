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
        AuthenticationResponse response = AuthenticationResponseParser.parse(new URI("https://client.com/callback?state=Mcew8TR4nXeLO5ea5fby98bOGNLAIVfeOKNb1KMjQyw&session_state=81c2bcc8-a5d1-4cdd-9694-7d85ecb47e5b&code=b6dbf4f8-8edd-4471-9349-68a9dfc43cdf.81c2bcc8-a5d1-4cdd-9694-7d85ecb47e5b.bcf09a2b-dfb2-4862-87d4-b5f5bea7432e"));

        State state = new State("Mcew8TR4nXeLO5ea5fby98bOGNLAIVfeOKNb1KMjQyw");

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
