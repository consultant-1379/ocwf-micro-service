/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2018
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.orchestration.solutiondevelopment.assurance.camunda.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.WebApiEnmLoginResponse;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.WebApiVmResponse;

public class Utils {
	

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class.getName());

    // Default parameter values
    public static final String DEFAULT_ACKS = "all";
    public static final int DEFAULT_RETRIES = 0;
    public static final int DEFAULT_BATCH_SIZE = 16384;
    public static final int DEFAULT_LINGER_TIME = 1;
    public static final long DEFAULT_BUFFER_MEMORY = 33554432;
    public static final boolean DEFAULT_ENABLE_AUTO_COMMIT = true;
    public static final int DEFAULT_AUTO_COMMIT_TIME = 1000;
    public static final int DEFAULT_SESSION_TIMEOUT = 30000;
    public static final int DEFAULT_CONSUMER_POLL_TIME = 100;
    public static final String DEFAULT_KEY_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String DEFAULT_VALUE_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String DEFAULT_KEY_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    public static final String DEFAULT_VALUE_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    
    private Utils() {
        throw new IllegalStateException("Utility class");
      }

    public static WebApiVmResponse sendPost(final String url, final String tenantId, final String authHeader, final String requestBody) {

        try {
            final HttpPost post = new HttpPost(url);

            // add header
            post.setHeader("Content-Type", "application/json");
            post.setHeader("tenantId", tenantId);
            post.setHeader("Authorization", authHeader);

            if (requestBody != null) {
                //final String jsonString = " \"bootSource\": {" + "\"imageName\":\"" + imageName + "\"}";

                final StringEntity requestEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
                post.setEntity(requestEntity);
            }

            final SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();

            final HttpClient client = HttpClientBuilder.create().setSSLContext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

            LOGGER.info("######Sending 'POST' request to URL : " + url);

            /*
             * for (final Header header : post.getAllHeaders()) { LOGGER.info("post header:: " + header.getName() + ":" + header.getValue()); }
             */

            final HttpResponse response = client.execute(post);

            final WebApiVmResponse webApiVmResponse = new WebApiVmResponse();

            final int responseCode = response.getStatusLine().getStatusCode();

            LOGGER.info("######Response Code : " + responseCode);
            if (responseCode != 200) {

                webApiVmResponse.setError(true);
                webApiVmResponse.setStatusCode(responseCode + "");
                return webApiVmResponse;
            }

            final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            final StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            LOGGER.info("#### Response Body::" + result.toString());

            webApiVmResponse.setError(false);
            webApiVmResponse.setStatusCode(responseCode + "");
            webApiVmResponse.setResponseBody(result.toString());

            return webApiVmResponse;

        } catch (final Exception ex) {

            LOGGER.info(ex.getMessage());

            final WebApiVmResponse webApiVmResponse = new WebApiVmResponse();
            webApiVmResponse.setError(true);
            webApiVmResponse.setStatusCode("1");
            webApiVmResponse.setMessage(ex.getMessage());
            return webApiVmResponse;
        }

    }

    public static WebApiEnmLoginResponse sendLoginEnmPost(final String url, final String username, final String password) {

        try {
            final HttpPost post = new HttpPost(url);

            // add header
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");

            final List<NameValuePair> form = new ArrayList<>();
            form.add(new BasicNameValuePair("IDToken1", username));
            form.add(new BasicNameValuePair("IDToken2", password));
            final UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
            post.setEntity(entity);

            final SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();

            final CookieStore httpCookieStore = new BasicCookieStore();

            //

            final HttpClient client = HttpClientBuilder.create().setSSLContext(sslContext).setRedirectStrategy(new LaxRedirectStrategy())
                    .setDefaultCookieStore(httpCookieStore).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

            LOGGER.info("######Sending 'POST' request to URL : " + url);

            /*
             * for (final Header header : post.getAllHeaders()) { LOGGER.info("post header:: " + header.getName() + ":" + header.getValue()); }
             */

            final HttpResponse response = client.execute(post);

            final WebApiEnmLoginResponse webApiVmResponse = new WebApiEnmLoginResponse();

            final int responseCode = response.getStatusLine().getStatusCode();

            LOGGER.info("######Response Code : " + responseCode);
            if (responseCode != 200) {

                webApiVmResponse.setError(true);
                webApiVmResponse.setStatusCode(responseCode + "");
                return webApiVmResponse;
            }

            final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            final StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            //LOGGER.info("#### Response Body::" + result.toString());

            webApiVmResponse.setError(false);
            webApiVmResponse.setStatusCode(responseCode + "");
            webApiVmResponse.setResponseBody(result.toString());

            final List<Cookie> cookies = httpCookieStore.getCookies();

            if (cookies != null && cookies.size() > 0) {
                for (int i = 0; i < cookies.size(); i++) {
                    if (cookies.get(i).getName().equals("iPlanetDirectoryPro")) {
                        webApiVmResponse.setToken(cookies.get(i).getValue());
                        break;
                    }
                }
            }

            return webApiVmResponse;

        } catch (final Exception ex) {

            LOGGER.info(ex.getMessage());

            final WebApiEnmLoginResponse webApiVmResponse = new WebApiEnmLoginResponse();
            webApiVmResponse.setError(true);
            webApiVmResponse.setStatusCode("1");
            webApiVmResponse.setMessage(ex.getMessage());
            return webApiVmResponse;
        }

    }

    public static WebApiEnmLoginResponse sendScaleVnfPost(final String url, final String token, final String jsonBody, final String domain) {

        try {
            final HttpPost post = new HttpPost(url);

            // add header
            post.setHeader("Content-Type", "application/json");

            if (jsonBody != null) {

                final StringEntity requestEntity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
                post.setEntity(requestEntity);
            }

            final SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();

            final CookieStore httpCookieStore = new BasicCookieStore();
            final BasicClientCookie cookie = new BasicClientCookie("iPlanetDirectoryPro", token);
            cookie.setDomain(domain);
            cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "true");
            httpCookieStore.addCookie(cookie);

            final HttpClient client = HttpClientBuilder.create().setSSLContext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .setDefaultCookieStore(httpCookieStore).build();

            LOGGER.info("######Sending 'POST' request to URL : " + url);

            /*
             * for (final Header header : post.getAllHeaders()) { LOGGER.info("post header:: " + header.getName() + ":" + header.getValue()); }
             */

            final HttpResponse response = client.execute(post);

            final WebApiEnmLoginResponse webApiVmResponse = new WebApiEnmLoginResponse();

            final int responseCode = response.getStatusLine().getStatusCode();

            LOGGER.info("######Response Code : " + responseCode);
            if (responseCode != 202) {

                webApiVmResponse.setError(true);
                webApiVmResponse.setStatusCode(responseCode + "");
                return webApiVmResponse;
            }

            final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            final StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            LOGGER.info("#### Response Body::" + result.toString());

            webApiVmResponse.setError(false);
            webApiVmResponse.setStatusCode(responseCode + "");
            webApiVmResponse.setResponseBody(result.toString());

            return webApiVmResponse;

        } catch (final Exception ex) {
            LOGGER.info(ex.getMessage());

            final WebApiEnmLoginResponse webApiVmResponse = new WebApiEnmLoginResponse();
            webApiVmResponse.setError(true);
            webApiVmResponse.setStatusCode("1");
            webApiVmResponse.setMessage(ex.getMessage());
            return webApiVmResponse;
        }

    }

    public static WebApiVmResponse sendGet(final String url, final String tenantId, final String authHeader) {

        try {
            final HttpGet get = new HttpGet(url);

            // add header
            get.setHeader("Content-Type", "application/json");
            get.setHeader("tenantId", tenantId);
            get.setHeader("Authorization", authHeader);

            final SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();

            final HttpClient client = HttpClientBuilder.create().setSSLContext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

            LOGGER.info("######Sending 'GET' request to URL : " + url);

            /*
             * for (final Header header : post.getAllHeaders()) { LOGGER.info("post header:: " + header.getName() + ":" + header.getValue()); }
             */

            final HttpResponse response = client.execute(get);

            final WebApiVmResponse webApiVmResponse = new WebApiVmResponse();

            final int responseCode = response.getStatusLine().getStatusCode();

            LOGGER.info("######Response Code : " + responseCode);
            if (responseCode != 200) {

                webApiVmResponse.setError(true);
                webApiVmResponse.setStatusCode(responseCode + "");
                return webApiVmResponse;
            }

            final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            final StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            LOGGER.info("#### Response Body::" + result.toString());

            webApiVmResponse.setError(false);
            webApiVmResponse.setStatusCode(responseCode + "");
            webApiVmResponse.setResponseBody(result.toString());

            return webApiVmResponse;

        } catch (final Exception ex) {

            LOGGER.info(ex.getMessage());

            final WebApiVmResponse webApiVmResponse = new WebApiVmResponse();
            webApiVmResponse.setError(true);
            webApiVmResponse.setStatusCode("1");
            webApiVmResponse.setMessage(ex.getMessage());
            return webApiVmResponse;
        }

    }

    public static Long getLongFromString(final String value) {

        try {
            return Long.parseLong(value);
        } catch (final Exception ex) {
            return null;
        }
    }
}
