package com.after_sunrise.api.kabusapi4j.entity;

import java.net.URI;

/**
 * @author takanori.takase
 * @version 0.0.0
 */
public class K4jRestException extends Exception {

    static final long serialVersionUID = 42;

    private final URI uri;

    private final int status;

    private final String content;

    private final K4jRestFailure failure;

    public K4jRestException(URI uri, int status, String content, K4jRestFailure failure) {
        super(failure.getErrorCode() + " : " + failure.getErrorMessage());
        this.uri = uri;
        this.status = status;
        this.content = content;
        this.failure = failure;
    }

    public URI getUri() {
        return uri;
    }

    /**
     * HTTP Status Code (cf: 400, 401, 403, 404, 405, 413, 415, 429, 500, ...)
     */
    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public K4jRestFailure getFailure() {
        return failure;
    }

}
