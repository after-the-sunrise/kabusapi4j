package com.after_sunrise.api.kabusapi4j;

import com.after_sunrise.api.kabusapi4j.entity.K4jBoard;
import com.after_sunrise.api.kabusapi4j.entity.K4jSession;

/**
 * 時価PUSH配信
 *
 * @author takanori.takase
 * @version 0.0.0
 */
public interface K4jListener {

    interface K4jConnectionListener extends K4jListener {

        void onOpen(K4jSession session);

        void onClose(K4jSession session, int code, String reason);

        void onError(K4jSession session, Throwable error);

    }

    interface K4jHeartbeatListener extends K4jListener {

        void onPing(K4jSession session);

        void onPong(K4jSession session);

    }

    interface K4jMessageListener extends K4jListener {

        void onBoard(K4jSession session, K4jBoard message);

    }

    class K4jListenerAdapter implements K4jConnectionListener, K4jHeartbeatListener, K4jMessageListener {
        @Override
        public void onOpen(K4jSession session) {
        }

        @Override
        public void onClose(K4jSession session, int code, String reason) {
        }

        @Override
        public void onError(K4jSession session, Throwable error) {
        }

        @Override
        public void onPing(K4jSession session) {
        }

        @Override
        public void onPong(K4jSession session) {
        }

        @Override
        public void onBoard(K4jSession session, K4jBoard message) {
        }
    }

}
