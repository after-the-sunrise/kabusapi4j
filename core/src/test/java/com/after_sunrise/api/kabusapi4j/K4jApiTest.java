package com.after_sunrise.api.kabusapi4j;

import com.after_sunrise.api.kabusapi4j.entity.ImmutableK4jInstrument;
import com.after_sunrise.api.kabusapi4j.entity.K4jRestException;
import com.after_sunrise.api.kabusapi4j.entity.K4jRestFailure;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jBoardRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jCashRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jMarginRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jOrderCancelRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jOrderCreateRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jOrderListRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jPositionRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jRegisterRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jSymbolRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jTokenRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jBoardResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jCashResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jMarginResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderCancelRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderCancelResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderCreateRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderCreateResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jOrderListResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jPositionResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jRegisterRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jRegisterResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jSymbolResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jTokenRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jTokenResponse;
import com.google.gson.Gson;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jExchangeType.XTKS;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author takanori.takase
 * @version 0.0.0
 */
@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class K4jApiTest extends Application {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private K4jApi target;

    private Gson gson;

    private UndertowJaxrsServer server;

    @BeforeEach
    void setUp() {

        int port = ThreadLocalRandom.current().nextInt(32700, 32760);
        server = new UndertowJaxrsServer().deploy(this);
        server.start(Undertow.builder().addHttpListener(port, "localhost"));

        Properties properties = new Properties();
        properties.setProperty(K4jConfig.ENVIRONMENT.getKey(), getClass().getSimpleName());
        properties.setProperty(K4jConfig.REST_URL.getKey(), "http://localhost:" + port + "/test");
        properties.setProperty(K4jConfig.PUSH_URL.getKey(), "ws://localhost:" + port + "/test/push");
        target = K4jApi.createInstance(properties);
        gson = target.buildGson();

    }

    @AfterEach
    void tearDown() {
        server.stop();
    }

    @Override
    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    @POST
    @Path("/kabusapi/token")
    public Response handleToken(String body) {
        K4jTokenRequest req = gson.fromJson(body, K4jTokenRequest.class);
        return "hoge".equals(req.getApiPassword())
                ? Response.ok(ClassLoader.getSystemResourceAsStream("token.json")).build()
                : Response.status(BAD_REQUEST).entity(ClassLoader.getSystemResourceAsStream("error.json")).build();
    }

    @Test
    void testToken() throws Exception {
        K4jTokenResponse res = target.token(ImmutableK4jTokenRequest.builder().apiPassword("hoge").build());
        assertEquals(Boolean.TRUE, res.getResult());
        assertEquals("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", res.getToken());
    }

    @Test
    void testToken_Error() throws Exception {
        try {
            assertNull(target.token(ImmutableK4jTokenRequest.builder().apiPassword("piyo").build()));
            fail();
        } catch (K4jRestException e) {
            assertEquals("/test/kabusapi/token", e.getUri().getPath());
            assertEquals(400, e.getStatus());
            assertEquals(4001001, e.getFailure().getErrorCode());
            assertEquals("内部エラー", e.getFailure().getErrorMessage());
            assertEquals(e.getFailure(), gson.fromJson(e.getContent(), K4jRestFailure.class));
        }
    }

    @POST
    @Path("/kabusapi/sendorder")
    public InputStream handleSendOrder(@HeaderParam("X-API-KEY") String token, String body) {
        K4jOrderCreateRequest req = gson.fromJson(body, K4jOrderCreateRequest.class);
        assertNull(req.getToken());
        assertEquals("1000", req.getSymbol());
        assertEquals(XTKS, req.getExchange());
        assertEquals("hoge", token);
        return ClassLoader.getSystemResourceAsStream("create_response.json");
    }

    @Test
    void testSendOrder() throws Exception {
        K4jOrderCreateResponse res = target.sendOrder(
                ImmutableK4jOrderCreateRequest.builder().token("hoge").symbol("1000").exchange(XTKS).build());
        assertEquals(Boolean.TRUE, res.getResult());
        assertEquals("20200529A01N06848002", res.getOrderId());
    }

    @PUT
    @Path("/kabusapi/cancelorder")
    public InputStream handleCancelOrder(@HeaderParam("X-API-KEY") String token, String body) {
        K4jOrderCancelRequest req = gson.fromJson(body, K4jOrderCancelRequest.class);
        assertNull(req.getToken());
        assertEquals("oid", req.getOrderId());
        assertEquals("pwd", req.getPassword());
        assertEquals("hoge", token);
        return ClassLoader.getSystemResourceAsStream("create_response.json");
    }

    @Test
    void testCancelOrder() throws Exception {
        K4jOrderCancelResponse res = target.cancelOrder(
                ImmutableK4jOrderCancelRequest.builder().token("hoge").orderId("oid").password("pwd").build());
        assertEquals(Boolean.TRUE, res.getResult());
        assertEquals("20200529A01N06848002", res.getOrderId());
    }

    @GET
    @Path("/kabusapi/wallet/cash/{symbol}@{exchange}")
    public InputStream handleWalletCash(@HeaderParam("X-API-KEY") String token,
                                        @PathParam("symbol") String symbol,
                                        @PathParam("exchange") String exchange,
                                        String body) {
        assertEquals("", body);
        assertEquals("hoge", token);
        assertEquals("1000", symbol);
        assertEquals(XTKS.getId().toString(), exchange);
        return ClassLoader.getSystemResourceAsStream("wallet_cash.json");
    }

    @Test
    void testWalletCash() throws Exception {
        K4jCashResponse res = target.walletCash(ImmutableK4jCashRequest.builder().token("hoge")
                .instrument(ImmutableK4jInstrument.builder().symbol("1000").exchange(XTKS).build()).build());
        assertEquals(new BigDecimal("1.1"), res.getStockAccountWallet());
    }

    @GET
    @Path("/kabusapi/wallet/margin/{symbol}@{exchange}")
    public InputStream handleWalletMargin(@HeaderParam("X-API-KEY") String token,
                                          @PathParam("symbol") String symbol,
                                          @PathParam("exchange") String exchange,
                                          String body) {
        assertEquals("", body);
        assertEquals("hoge", token);
        assertEquals("1000", symbol);
        assertEquals(XTKS.getId().toString(), exchange);
        return ClassLoader.getSystemResourceAsStream("wallet_margin.json");
    }

    @Test
    void testWalletMargin() throws Exception {
        K4jMarginResponse res = target.walletMargin(ImmutableK4jMarginRequest.builder().token("hoge")
                .instrument(ImmutableK4jInstrument.builder().symbol("1000").exchange(XTKS).build()).build());
        assertEquals(new BigDecimal("1.1"), res.getMarginAccountWallet());
        assertEquals(new BigDecimal("2.2"), res.getDepositKeepRate());
        assertEquals(new BigDecimal("3.3"), res.getConsignmentDepositRate());
        assertEquals(new BigDecimal("4.4"), res.getCashOfConsignmentDepositRate());
    }

    @GET
    @Path("/kabusapi/board/{symbol}@{exchange}")
    public InputStream handleBoard(@HeaderParam("X-API-KEY") String token,
                                   @PathParam("symbol") String symbol,
                                   @PathParam("exchange") String exchange,
                                   String body) {
        assertEquals("", body);
        assertEquals("hoge", token);
        assertEquals("1000", symbol);
        assertEquals(XTKS.getId().toString(), exchange);
        return ClassLoader.getSystemResourceAsStream("board.json");
    }

    @Test
    void testBoard() throws Exception {
        K4jBoardResponse res = target.board(
                ImmutableK4jBoardRequest.builder().token("hoge").symbol("1000").exchange(XTKS).build());
        assertEquals("5401", res.getSymbol());
        assertEquals(XTKS, res.getExchange());
        assertEquals(new BigDecimal("2408"), res.getCurrentPrice());
        assertEquals(Instant.parse("2020-07-22T06:00:00Z"), res.getCurrentPriceTime());
    }

    @GET
    @Path("/kabusapi/symbol/{symbol}@{exchange}")
    public InputStream handleSymbol(@HeaderParam("X-API-KEY") String token,
                                    @PathParam("symbol") String symbol,
                                    @PathParam("exchange") String exchange,
                                    String body) {
        assertEquals("", body);
        assertEquals("hoge", token);
        assertEquals("1000", symbol);
        assertEquals(XTKS.getId().toString(), exchange);
        return ClassLoader.getSystemResourceAsStream("symbol.json");
    }

    @Test
    void testSymbol() throws Exception {
        K4jSymbolResponse res = target.symbol(
                ImmutableK4jSymbolRequest.builder().token("hoge").symbol("1000").exchange(XTKS).build());
        assertEquals("9433", res.getSymbol());
        assertEquals(XTKS, res.getExchange());
        assertEquals(new BigDecimal("7654484465100"), res.getTotalMarketValue());
    }

    @GET
    @Path("/kabusapi/orders")
    public InputStream handleOrders(@HeaderParam("X-API-KEY") String token, String body) {
        assertEquals("", body);
        assertEquals("hoge", token);
        return ClassLoader.getSystemResourceAsStream("orders.json");
    }

    @Test
    void testOrders() throws Exception {
        K4jOrderListResponse res = target.orders(ImmutableK4jOrderListRequest.builder().token("hoge").build());
        assertEquals(1, res.getOrders().size());
        assertEquals("20200715A02N04738436", res.getOrders().get(0).getId());
        assertEquals(XTKS, res.getOrders().get(0).getExchange());
        assertEquals(Instant.parse("2020-07-16T18:00:51.763683Z"), res.getOrders().get(0).getRecvTime());
    }

    @GET
    @Path("/kabusapi/positions")
    public InputStream handlePositions(@HeaderParam("X-API-KEY") String token, String body) {
        assertEquals("", body);
        assertEquals("hoge", token);
        return ClassLoader.getSystemResourceAsStream("positions.json");
    }

    @Test
    void testPositions() throws Exception {
        K4jPositionResponse res = target.positions(ImmutableK4jPositionRequest.builder().token("hoge").build());
        assertEquals(1, res.getPositions().size());
        assertEquals("20200715E02N04738464", res.getPositions().get(0).getExecutionId());
        assertEquals(XTKS, res.getPositions().get(0).getExchange());
        assertEquals(new BigDecimal("41.12215909090909"), res.getPositions().get(0).getProfitLossRate());
    }

    @PUT
    @Path("/kabusapi/register")
    public InputStream handleRegister(@HeaderParam("X-API-KEY") String token, String body) {
        K4jRegisterRequest req = gson.fromJson(body, K4jRegisterRequest.class);
        assertNull(req.getToken());
        assertEquals(1, req.getSymbols().size());
        assertEquals("1000", req.getSymbols().get(0).getSymbol());
        assertEquals(XTKS, req.getSymbols().get(0).getExchange());
        assertEquals("hoge", token);
        return ClassLoader.getSystemResourceAsStream("register_response.json");
    }

    @Test
    void testRegister() throws Exception {
        K4jRegisterResponse res = target.register(ImmutableK4jRegisterRequest.builder().token("hoge")
                .addSymbols(ImmutableK4jInstrument.builder().symbol("1000").exchange(XTKS).build()).build());
        assertEquals(1, res.getSymbols().size());
        assertEquals("9433", res.getSymbols().get(0).getSymbol());
        assertEquals(XTKS, res.getSymbols().get(0).getExchange());
    }

    @PUT
    @Path("/kabusapi/unregister")
    public InputStream handleUnregister(@HeaderParam("X-API-KEY") String token, String body) {
        K4jRegisterRequest req = gson.fromJson(body, K4jRegisterRequest.class);
        assertNull(req.getToken());
        assertEquals(1, req.getSymbols().size());
        assertEquals("2000", req.getSymbols().get(0).getSymbol());
        assertEquals(XTKS, req.getSymbols().get(0).getExchange());
        assertEquals("hoge", token);
        return ClassLoader.getSystemResourceAsStream("register_response.json");
    }

    @Test
    void testUnregister() throws Exception {
        K4jRegisterResponse res = target.unregister(ImmutableK4jRegisterRequest.builder().token("hoge")
                .addSymbols(ImmutableK4jInstrument.builder().symbol("2000").exchange(XTKS).build()).build());
        assertEquals(1, res.getSymbols().size());
        assertEquals("9433", res.getSymbols().get(0).getSymbol());
        assertEquals(XTKS, res.getSymbols().get(0).getExchange());
    }

    @PUT
    @Path("/kabusapi/unregister/all")
    public InputStream handleUnregisterAll(@HeaderParam("X-API-KEY") String token, String body) {
        assertEquals("", body);
        assertEquals("hoge", token);
        return ClassLoader.getSystemResourceAsStream("register_response.json");
    }

    @Test
    void testUnregisterAll() throws Exception {
        K4jRegisterResponse res = target.unregisterAll(ImmutableK4jRegisterRequest.builder().token("hoge").build());
        assertEquals(1, res.getSymbols().size());
        assertEquals("9433", res.getSymbols().get(0).getSymbol());
        assertEquals(XTKS, res.getSymbols().get(0).getExchange());
    }

    @Disabled
    @Test
    void testConnect() {
        // TODO
    }

}
