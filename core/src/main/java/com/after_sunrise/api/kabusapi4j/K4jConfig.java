package com.after_sunrise.api.kabusapi4j;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author takanori.takase
 * @version 0.0.0
 */
public enum K4jConfig {

    ENVIRONMENT("default"),

    TIMEOUT(TimeUnit.MINUTES.toMillis(1)),

    PROXY(null),

    REST_URL("http://localhost"),

    PUSH_URL("ws://localhost:18080"),

    ;

    private final String key;

    private final String val;

    K4jConfig(Object value) {
        this.key = "kabusapi4j." + name().toLowerCase();
        this.val = Objects.toString(value, null);
    }

    public String getKey() {
        return key;
    }

    public String getVal() {
        return val;
    }

    public String get(Properties properties) {
        return properties.getProperty(key, val);
    }

    public Integer getInteger(Properties properties) {

        String value = get(properties);

        return value == null ? null : Integer.valueOf(value);

    }

    public Long getLong(Properties properties) {

        String value = get(properties);

        return value == null ? null : Long.valueOf(value);

    }

    public Duration getDuration(Properties properties) {

        Long value = getLong(properties);

        return value == null ? null : Duration.ofMillis(value);

    }

    public URL getUrl(Properties properties) throws IllegalArgumentException {

        try {

            String value = get(properties);

            return value == null ? null : new URL(value);

        } catch (MalformedURLException e) {

            throw new IllegalArgumentException(e);

        }

    }

    public InetSocketAddress getAddress(Properties properties) throws IllegalArgumentException {

        URL value = getUrl(properties);

        return value == null ? null : new InetSocketAddress(value.getHost(), value.getPort());

    }

}
