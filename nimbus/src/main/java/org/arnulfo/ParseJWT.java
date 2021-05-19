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
import com.nimbusds.jose.JWSObject;

public class ParseJWT {

    public static void main(String[] args) throws Exception {

        // JWT
        String string = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJTb0V1dl94UmMzaDF0Unh1VUlJZ2s4SmU1VHRlQ0h4Y29ZTnNEZUl3Ukk0In0.eyJleHAiOjE2MjE0MTU0NTIsImlhdCI6MTYyMTQxNTE1MiwiYXV0aF90aW1lIjoxNjIxNDE0OTg1LCJqdGkiOiJmZTIxZjA3Yy04NDc3LTQyNzgtOTNkYS1mNDAyNzgyM2JmN2UiLCJpc3MiOiJodHRwczovL2xlbXVyLTEuY2xvdWQtaWFtLmNvbS9hdXRoL3JlYWxtcy90ZXN0LW9pZGMiLCJhdWQiOiJjYXQtYXBwLWlkIiwic3ViIjoiMTZiYTcxZDYtYmFhYS00Mjc5LTg0NDEtZDFhMjk2MzI1NTkxIiwidHlwIjoiSUQiLCJhenAiOiJjYXQtYXBwLWlkIiwibm9uY2UiOiJCb1NUYmNpUHJpNUp2Rl8tV3BZN3cwdGR0SXdycUVDQ1FEUUktX191T0tNIiwic2Vzc2lvbl9zdGF0ZSI6IjNhYzAwMDdjLTRiOWMtNGViMS1hMjdhLTEyNWM0ODY0MjUxYyIsImF0X2hhc2giOiJBaXVnTTNTSEltUEEyVEs2UWIxOW1BIiwiYWNyIjoiMSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiRnJhbmNrIEFybnVsZm8iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJmYXJudWxmbyIsImdpdmVuX25hbWUiOiJGcmFuY2siLCJmYW1pbHlfbmFtZSI6IkFybnVsZm8iLCJlbWFpbCI6ImZyYW5jay5hcm51bGZvQGdtYWlsLmNvbSJ9.VqsWCf8fcLhT2HJScJ4Fs7etunwAyRK2u053idcXW0u0nMv7kQn-zfG0DuCW7zhFn4Y3m0FtmR4nm5xr5hif5Y31VRVZWUSevtz9gzKyG3kjiKX_nFywDx1ZWGYufrGsAga-vvFA1_5rskv-3iClbP8i5LsXtTsIpU98c1kZKPBC6xLNWUmp_monBA_VzYWzFLYUrYqHwUgQA80H5MSHl2cJ4A4_ie4O77UBJtzGZUo9TnJPf2YUVR3Ty64GHpwRCm48J_NN5j1OWAEg95ejkcH3_2chd3xcLkPjFUQ-v_XPyHMj89QqXXGp97AeBe4PZF8-_PogZnWl2xi-bBg55Q";
        JWSObject jwsObject;
        
        jwsObject = JWSObject.parse(string);
        System.out.println("JWT Payload: " + jwsObject.getPayload().toString());

        // continue with signature verification...
        //jwsObject.verify(...);
 //
   //             }


    }
}
