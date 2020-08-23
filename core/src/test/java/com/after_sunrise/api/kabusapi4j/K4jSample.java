package com.after_sunrise.api.kabusapi4j;

import com.after_sunrise.api.kabusapi4j.entity.ImmutableK4jInstrument;
import com.after_sunrise.api.kabusapi4j.entity.K4jBoard;
import com.after_sunrise.api.kabusapi4j.entity.K4jSession;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jCashRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jMarginRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jRegisterRequest;
import com.after_sunrise.api.kabusapi4j.message.ImmutableK4jTokenRequest;
import com.after_sunrise.api.kabusapi4j.message.K4jCashResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jMarginResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jRegisterResponse;
import com.after_sunrise.api.kabusapi4j.message.K4jTokenResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jExchangeType;

/**
 * 実装サンプル
 *
 * @author takanori.takase
 * @version 0.0.0
 */
public class K4jSample {

    public static void main(String[] args) throws Exception {

        K4jApi api = K4jApi.createInstance();

        //
        // トークン発行
        //
        K4jTokenResponse token = api.token(ImmutableK4jTokenRequest.builder().apiPassword("hoge").build());
        System.out.println("Token : " + token.getToken());

        //
        // 取引余力（現物）
        //
        K4jCashResponse cash = api.walletCash(ImmutableK4jCashRequest.builder().token(token.getToken()).build());
        System.out.println("Cash : " + cash);

        //
        // 取引余力（信用）（銘柄指定）
        //
        K4jMarginResponse margin = api.walletMargin(ImmutableK4jMarginRequest.builder().token(token.getToken())
                .instrument(ImmutableK4jInstrument.builder().symbol("1000").exchange(K4jExchangeType.XTKS).build()).build());
        System.out.println("Margin : " + margin);

        //
        // PUSH 銘柄登録
        //
        K4jRegisterResponse register = api.register(ImmutableK4jRegisterRequest.builder().addSymbols(
                ImmutableK4jInstrument.builder().symbol("2000").exchange(K4jExchangeType.XTKS).build(),
                ImmutableK4jInstrument.builder().symbol("3000").exchange(K4jExchangeType.XNGO).build()
        ).token(token.getToken()).build());
        System.out.println("Register : " + register);

        //
        // PUSH 受信
        //
        CompletableFuture<K4jSession> future = api.connect(new K4jListener.K4jMessageListener() {
            @Override
            public void onBoard(K4jSession session, K4jBoard message) {
                System.out.println("Board : " + message);
            }
        });

        //
        // PUSH 待機
        //
        TimeUnit.MINUTES.sleep(3);

        //
        // PUSH 切断
        //
        future.getNow(null).close();

    }

}
