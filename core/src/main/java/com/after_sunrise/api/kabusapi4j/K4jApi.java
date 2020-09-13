package com.after_sunrise.api.kabusapi4j;

import com.after_sunrise.api.kabusapi4j.K4jListener.K4jConnectionListener;
import com.after_sunrise.api.kabusapi4j.K4jListener.K4jHeartbeatListener;
import com.after_sunrise.api.kabusapi4j.K4jListener.K4jMessageListener;
import com.after_sunrise.api.kabusapi4j.entity.K4jBoard;
import com.after_sunrise.api.kabusapi4j.entity.K4jInstrument;
import com.after_sunrise.api.kabusapi4j.entity.K4jOrder;
import com.after_sunrise.api.kabusapi4j.entity.K4jPosition;
import com.after_sunrise.api.kabusapi4j.entity.K4jRestException;
import com.after_sunrise.api.kabusapi4j.entity.K4jRestFailure;
import com.after_sunrise.api.kabusapi4j.entity.K4jSession;
import com.after_sunrise.api.kabusapi4j.entity.K4jType;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jOrderCancelRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jOrderCreateRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jOrderListResponse;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jPositionResponse;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jRegisterRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jBoardRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jBoardResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jCashRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jCashResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jMarginRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jMarginResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderCancelRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderCancelResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderCreateRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderCreateResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderListRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderListResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jPositionRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jPositionResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jRegisterRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jRegisterResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jSymbolRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jSymbolResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jTokenRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jTokenResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static com.after_sunrise.api.kabusapi4j.K4jConfig.ENVIRONMENT;
import static com.after_sunrise.api.kabusapi4j.K4jConfig.PROXY;
import static com.after_sunrise.api.kabusapi4j.K4jConfig.PUSH_URL;
import static com.after_sunrise.api.kabusapi4j.K4jConfig.REST_URL;
import static com.after_sunrise.api.kabusapi4j.K4jConfig.TIMEOUT;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static java.util.Objects.requireNonNull;

/**
 * @author takanori.takase
 * @version 0.0.0
 */
public final class K4jApi {

    public static K4jApi createInstance() {
        return new K4jApi(new Properties(System.getProperties()));
    }

    public static K4jApi createInstance(Properties properties) {
        return new K4jApi(properties);
    }

    private static final int HTTP_STATUS_OK = 200;
    private static final String HTTP_TYPE_JSON = "application/json";
    private static final String HTTP_HEADER_ACCEPT = "Accept";
    private static final String HTTP_HEADER_CONTENT = "Content-Type";
    private static final String HTTP_HEADER_APIKEY = "X-API-KEY";
    private static final String EMPTY = "";

    static final Type TYPE_ORDERS = new TypeToken<List<K4jOrder>>() {
    }.getType();
    static final Type TYPE_POSITIONS = new TypeToken<List<K4jPosition>>() {
    }.getType();

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Properties properties;
    private final Gson gson;
    private final HttpClient client;

    private K4jApi(Properties prop) {

        properties = requireNonNull(prop, "Properties cannot be null.");

        logger.info("Initializing instance : environment={}", getEnvironment());

        gson = buildGson();

        client = buildClient(properties);

    }

    Gson buildGson() {

        GsonBuilder builder = new GsonBuilder();

        ServiceLoader.load(TypeAdapterFactory.class).forEach(builder::registerTypeAdapterFactory);

        Map<Type, Map<String, K4jType<?>>> typeCache = new ConcurrentHashMap<>();

        return builder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, context) -> {

            int yyyyMMdd = json.getAsInt();

            return LocalDate.of(yyyyMMdd / 10000, (yyyyMMdd / 100) % 100, yyyyMMdd % 100);

        }).registerTypeAdapter(Instant.class, (JsonDeserializer<Instant>) (json, type, context) -> {

            String value = json.getAsString();

            if (value.indexOf('+') < 0) {
                return Instant.parse(value.endsWith("Z") ? value : value + "Z");
            }

            return Instant.from(ISO_OFFSET_DATE_TIME.parse(value));

        }).registerTypeAdapter(Boolean.class, (JsonDeserializer<Boolean>) (json, type, context) -> {

            String value = json.getAsString();

            if (value.isBlank()) {
                return null;
            }

            return "0".equals(value) ? Boolean.TRUE : Boolean.valueOf(value);

        }).registerTypeHierarchyAdapter(K4jType.class, (JsonDeserializer<K4jType<?>>) (json, type, context) -> {

            K4jType<?> constant = typeCache.computeIfAbsent(type, x -> {

                Map<String, K4jType<?>> map = new HashMap<>();

                Stream.of(((Class<?>) type).getEnumConstants())
                        .map(K4jType.class::cast).forEach(e -> map.putIfAbsent(Objects.toString(e.getId()), e));

                return Collections.unmodifiableMap(map);

            }).get(json.getAsString());

            if (constant == null) {
                logger.trace("Undefined constant : {} - {}", type, json);
            }

            return constant;

        }).registerTypeHierarchyAdapter(K4jType.class, (JsonSerializer<K4jType<?>>) (src, type, context) -> {

            Object v = src.getId();

            return v instanceof Number ? new JsonPrimitive((Number) v) : new JsonPrimitive(v.toString());

        }).create();

    }

    HttpClient buildClient(Properties properties) {

        Duration timeout = TIMEOUT.getDuration(properties);
        InetSocketAddress proxy = PROXY.getAddress(properties);

        logger.debug("Building HTTP client : timeout={}, proxy={}", timeout, proxy);

        HttpClient.Builder builder = HttpClient.newBuilder();
        Optional.ofNullable(timeout).ifPresent(builder::connectTimeout);
        Optional.ofNullable(proxy).map(ProxySelector::of).ifPresent(builder::proxy);
        return builder.build();

    }

    <T> T validate(HttpRequest request, HttpResponse<String> response, Type type) throws K4jRestException {

        String body = response.body();

        if (logger.isDebugEnabled()) {
            logger.debug("{} -> {} - {}", request, type, body);
        }

        if (HTTP_STATUS_OK != response.statusCode()) {

            K4jRestFailure failure = gson.fromJson(body, K4jRestFailure.class);

            throw new K4jRestException(request.uri(), response.statusCode(), response.body(), failure);

        }

        return gson.fromJson(body, type);

    }

    String toPath(String path, K4jInstrument instrument) {

        if (instrument == null) {
            return path;
        }

        String s = Objects.toString(instrument.getSymbol(), EMPTY);

        String e = instrument.getExchange() == null ? EMPTY : Objects.toString(instrument.getExchange().getId(), EMPTY);

        return path + "/" + URLEncoder.encode(s, UTF_8) + "@" + URLEncoder.encode(e, UTF_8);

    }

    public String getEnvironment() {
        return ENVIRONMENT.get(properties);
    }

    public K4jTokenResponse token(K4jTokenRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + "/kabusapi/token"))
                .header(HTTP_HEADER_CONTENT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return validate(req, res, K4jTokenResponse.class);

    }

    public K4jOrderCreateResponse sendOrder(K4jOrderCreateRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + "/kabusapi/sendorder"))
                .header(HTTP_HEADER_CONTENT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(
                        ImmutableK4jOrderCreateRequest.builder().from(request).token(null).build()))) // Clear token.
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return validate(req, res, K4jOrderCreateResponse.class);

    }

    public K4jOrderCancelResponse cancelOrder(K4jOrderCancelRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + "/kabusapi/cancelorder"))
                .header(HTTP_HEADER_CONTENT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(
                        ImmutableK4jOrderCancelRequest.builder().from(request).token(null).build()))) // Clear token.
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return validate(req, res, K4jOrderCancelResponse.class);

    }

    public K4jCashResponse walletCash(K4jCashRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + toPath("/kabusapi/wallet/cash", request.getInstrument())))
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .GET()
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return validate(req, res, K4jCashResponse.class);

    }

    public K4jMarginResponse walletMargin(K4jMarginRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + toPath("/kabusapi/wallet/margin", request.getInstrument())))
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .GET()
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return validate(req, res, K4jMarginResponse.class);

    }

    public K4jBoardResponse board(K4jBoardRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + toPath("/kabusapi/board", request)))
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .GET()
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return validate(req, res, K4jBoardResponse.class);

    }

    public K4jSymbolResponse symbol(K4jSymbolRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + toPath("/kabusapi/symbol", request)))
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .GET()
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return validate(req, res, K4jSymbolResponse.class);

    }

    public K4jOrderListResponse orders(K4jOrderListRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + "/kabusapi/orders"))
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .GET()
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        if (HTTP_STATUS_OK != res.statusCode()) {

            K4jRestFailure failure = gson.fromJson(res.body(), K4jRestFailure.class);

            throw new K4jRestException(req.uri(), res.statusCode(), res.body(), failure);

        }

        List<K4jOrder> values = Objects.requireNonNullElseGet(validate(req, res, TYPE_ORDERS), Collections::emptyList);

        return ImmutableK4jOrderListResponse.builder().addAllOrders(values).build();

    }

    public K4jPositionResponse positions(K4jPositionRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + "/kabusapi/positions"))
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .GET()
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        List<K4jPosition> values = Objects.requireNonNullElseGet(validate(req, res, TYPE_POSITIONS), Collections::emptyList);

        return ImmutableK4jPositionResponse.builder().addAllPositions(values).build();

    }

    public K4jRegisterResponse register(K4jRegisterRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + "/kabusapi/register"))
                .header(HTTP_HEADER_CONTENT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(
                        ImmutableK4jRegisterRequest.builder().from(request).token(null).build()))) // Clear token.
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return validate(req, res, K4jRegisterResponse.class);

    }

    public K4jRegisterResponse unregister(K4jRegisterRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + "/kabusapi/unregister"))
                .header(HTTP_HEADER_CONTENT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(
                        ImmutableK4jRegisterRequest.builder().from(request).token(null).build()))) // Clear token.
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return validate(req, res, K4jRegisterResponse.class);

    }

    public K4jRegisterResponse unregisterAll(K4jRegisterRequest request) throws IOException, InterruptedException, K4jRestException {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL.get(properties) + "/kabusapi/unregister/all"))
                .header(HTTP_HEADER_ACCEPT, HTTP_TYPE_JSON)
                .header(HTTP_HEADER_APIKEY, request.getToken())
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return validate(req, res, K4jRegisterResponse.class);

    }

    static class K4jSessionImpl implements K4jSession {

        private static final ByteBuffer EMPTY = ByteBuffer.allocate(0);

        private final String id = UUID.randomUUID().toString();

        private final AtomicReference<String> buffer = new AtomicReference<>();

        private volatile WebSocket socket = null;

        @Override
        public void close() {
            if (socket != null) {
                socket.abort();
            }
        }

        @Override
        public void ping() {
            if (socket != null) {
                socket.sendPing(EMPTY);
            }
        }

        @Override
        public void pong() {
            if (socket != null) {
                socket.sendPong(EMPTY);
            }
        }

    }

    public CompletableFuture<K4jSession> connect(K4jListener listener) {

        K4jSessionImpl session = new K4jSessionImpl();

        return client.newWebSocketBuilder().buildAsync(URI.create(PUSH_URL.get(properties)), new Listener() {
            @Override
            public void onOpen(WebSocket webSocket) {

                session.socket = webSocket;

                logger.debug("Socket open : {}", session.id);

                if (listener instanceof K4jConnectionListener) {
                    ((K4jConnectionListener) listener).onOpen(session);
                }

                Listener.super.onOpen(webSocket);

            }

            @Override
            public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {

                session.socket = webSocket;

                logger.debug("Socket closed : {} - {} - {}", session.id, statusCode, reason);

                if (listener instanceof K4jConnectionListener) {
                    ((K4jConnectionListener) listener).onClose(session, statusCode, reason);
                }

                return Listener.super.onClose(webSocket, statusCode, reason);

            }

            @Override
            public void onError(WebSocket webSocket, Throwable error) {

                session.socket = webSocket;

                logger.warn("Socket error : {}", session.id, error);

                if (listener instanceof K4jConnectionListener) {
                    ((K4jConnectionListener) listener).onError(session, error);
                }

                Listener.super.onError(webSocket, error);

            }

            @Override
            public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {

                session.socket = webSocket;

                logger.trace("Socket ping : {}", session.id);

                if (listener instanceof K4jHeartbeatListener) {
                    ((K4jHeartbeatListener) listener).onPing(session);
                }

                return Listener.super.onPing(webSocket, message);

            }

            @Override
            public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {

                session.socket = webSocket;

                logger.trace("Socket pong : {}", session.id);

                if (listener instanceof K4jHeartbeatListener) {
                    ((K4jHeartbeatListener) listener).onPong(session);
                }

                return Listener.super.onPong(webSocket, message);

            }

            @Override
            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {

                session.socket = webSocket;

                logger.trace("Socket text : {} - {} - {}", session.id, last, data);

                if (listener instanceof K4jMessageListener) {

                    if (last) {

                        String s = session.buffer.getAndSet(null);

                        K4jBoard message = gson.fromJson(s == null ? data.toString() : s + data, K4jBoard.class);

                        ((K4jMessageListener) listener).onBoard(session, message);

                    } else {

                        session.buffer.accumulateAndGet(data.toString(), (s1, s2) -> s1 == null ? s2 : s1 + s2);

                    }

                }

                return Listener.super.onText(webSocket, data, last);

            }

        }).thenApply(socket -> {

            session.socket = socket;

            return session;

        });

    }

}
