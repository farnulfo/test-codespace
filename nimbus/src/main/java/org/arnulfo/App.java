package org.arnulfo;

import java.net.*;
import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.openid.connect.sdk.*;
import com.nimbusds.oauth2.sdk.id.*;

public class App {
    public static void main(String[] args) throws Exception {
        // The client ID provisioned by the OpenID provider when
        // the client was registered
        // ClientID clientID = new ClientID("my-client");
        //ClientID clientID = new ClientID("000123");
        ClientID clientID = new ClientID("cat-app-id");

        // The client callback URL
        URI callback = new URI("https://client.com/callback");
        //URI callback = new URI("https://demo.c2id.com/oidc-client/cb");

        // Generate random state string to securely pair the callback to this request
        State state = new State();

        // Generate nonce for the ID token
        Nonce nonce = new Nonce();

        // String authEndpoint = "https://c2id.com/login";
        // Keycloak on cloud-iam.com
        String authEndpoint = "https://lemur-1.cloud-iam.com/auth/realms/test-oidc/protocol/openid-connect/auth";
        // Connect2id
        //String authEndpoint = "https://demo.c2id.com/c2id-login";

        Scope scope = new Scope();
        scope.add("openid");
        scope.add("my-securities");

        // Compose the OpenID authentication request (for the code flow)
        AuthenticationRequest request = new AuthenticationRequest.Builder(new ResponseType("code"), scope,
                clientID, callback).endpointURI(new URI(authEndpoint)).state(state).nonce(nonce).build();

        // The URI to send the user-user browser to the OpenID provider
        // E.g.
        // https://c2id.com/login?
        // client_id=123
        // &response_type=code
        // &scope=openid
        // &redirect_uri=https%3A%2F%2Fclient.com%2Fcallback
        // &state=6SK5S15Lwdp3Pem_55m-ayudGwno0eglKq6ZEWaykG8
        // &nonce=d_Y4LmbzpNHTkzTKJv6v59-OmqB_F2kNr8CbL-R2xWI
        System.out.println("URI: " + request.toURI());

        System.out.println("State :" + state);
    }
}
