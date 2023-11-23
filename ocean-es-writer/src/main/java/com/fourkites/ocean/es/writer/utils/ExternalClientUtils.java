package com.fourkites.ocean.es.writer.utils;

import org.springframework.http.HttpHeaders;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class ExternalClientUtils {
    private static final String MESSAGE_AUTH_ALGORITHM = "HmacSHA1";

    public static String getSharedSecretSignedUrl(
            String uri, String path, String fourkitesAppId, String fourkitesSharedSecret)
            throws NoSuchAlgorithmException, InvalidKeyException {
        DateTimeFormatter formatter          = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        ZonedDateTime     dateTime           = ZonedDateTime.now(Clock.systemUTC());
        String            timestamp          = dateTime.format(formatter);
        String            appIdWithTimestamp = fourkitesAppId + "--" + timestamp;
        Mac               hmacsha1           = Mac.getInstance(MESSAGE_AUTH_ALGORITHM);
        hmacsha1.init(new SecretKeySpec(fourkitesSharedSecret.getBytes(), MESSAGE_AUTH_ALGORITHM));
        byte[] hash      = hmacsha1.doFinal(appIdWithTimestamp.getBytes());
        String signature = Base64.getUrlEncoder().encodeToString(hash);
        String separator = "?";
        if (path.contains("?")) {
            separator = "&";
        }
        return uri + path + separator + "app_id=" + fourkitesAppId + "&timestamp=" + timestamp + "&signature=" + signature;
    }

    public static String getSignedURL(String uri, String path, String fourkitesClientId, String fourkitesSecret)
            throws NoSuchAlgorithmException, InvalidKeyException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        ZonedDateTime     dateTime  = ZonedDateTime.now(Clock.systemUTC());
        String            timestamp = dateTime.format(formatter);
        String            separator = "?";
        if (path.contains("?")) {
            separator = "&";
        }
        String pathWithClientId = path + separator + "client_id=" + fourkitesClientId + "&timestamp=" + timestamp;
        Mac    hmacsha1         = Mac.getInstance(MESSAGE_AUTH_ALGORITHM);
        hmacsha1.init(new SecretKeySpec(fourkitesSecret.getBytes(), MESSAGE_AUTH_ALGORITHM));
        byte[] hash      = hmacsha1.doFinal(pathWithClientId.getBytes());
        String signature = Base64.getUrlEncoder().encodeToString(hash);
        return uri + pathWithClientId + "&signature=" + signature;
    }

    public static HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Accept", "application/json");
        return httpHeaders;
    }

}
