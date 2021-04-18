package com.jay2u8809.codesamples.common;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public interface CommonHttpsConnection {

    default String httpsUrlConnection (String url) throws IOException {

        return this.basicHttpsUrlConnection(url, REQUEST_METHOD.GET, null, null);
    }

    default String httpsUrlConnection (String url, String clientId, String clientSecret) throws IOException {

        return this.basicHttpsUrlConnection(url, REQUEST_METHOD.GET, clientId, clientSecret);
    }

    default String httpsUrlConnection (String url, REQUEST_METHOD requestMethod, String clientId, String clientSecret) throws IOException {

        return this.basicHttpsUrlConnection(url, requestMethod, clientId, clientSecret);
    }

    default String basicHttpsUrlConnection(String urlStr, REQUEST_METHOD requestMethod,  String clientId, String clientSecret) throws IOException{

        URL url = new URL(urlStr);
        HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
        https.setRequestProperty("Content-Type", "application/json");
        https.setRequestMethod(requestMethod.name());
        if (clientId != null && clientSecret != null) {
            https.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            https.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
        }
        https.connect();

        InputStreamReader in = new InputStreamReader(https.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(in);

        // receive Api result(Address geocoding Info)
        String line;
        StringBuilder result = new StringBuilder();
        while((line = br.readLine()) != null) {
            result.append(line).append("\n");
        }

        br.close();
        in.close();
        https.disconnect();

        return result.length() == 0 ? null : result.toString();
    }

    enum REQUEST_METHOD {
        GET,
        POST,
        PUT,
        DELETE
    }
}
