package com.after_sunrise.api.kabusapi4j.entity;

/**
 * @author takanori.takase
 * @version 0.0.0
 */
public interface K4jSession extends AutoCloseable {

    void ping();

    void pong();

}
