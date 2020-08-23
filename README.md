# kabusapi4j
[![Build Status][travis-icon]][travis-page]

*kabusapi4j*は[auカブコム証券][kabu-home]が提供する[kabuステーションAPI][kabu-portal]のJavaラッパーライブラリです。

## 公式レファレンス
* [ポータルサイト][kabu-portal]
* [GitHub][kabu-github]

## 使用方法

### 実行環境
* Java Development Kit 11
* Apache Maven / Gradle Build Tool

### アーティファクト定義
```xml
<dependency>
    <groupId>com.after_sunrise.api</groupId>
    <artifactId>kabusapi4j-core</artifactId>
    <version>${latest}-SNAPSHOT</version>
</dependency>
```

### サンプル
```java
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
```

## 規約・免責事項
* このライブラリの利用者は同梱の[LICENSE][license]および規約・免責事項に同意したものとみなします。
* 過失・不具合などの有無に関わらず、利用者の自己責任にてこのライブラリを利用するものとします。
* [APIサービス提供者][kabu-home]の利用規約も併せて遵守してください。

## その他
* ライブラリ作者によるサポートや問い合わせ対応等は行っていません。
* APIの用法用量を守り、清く正しいAPIトレーディング生活をおくりましょう。

[travis-page]:https://travis-ci.org/after-the-sunrise/kabusapi4j
[travis-icon]:https://travis-ci.org/after-the-sunrise/kabusapi4j.svg?branch=master
[license]:https://github.com/after-the-sunrise/kabusapi4j/blob/master/LICENSE
[kabu-home]:https://kabu.com/
[kabu-portal]:https://kabucom.github.io/kabusapi/ptal/index.html
[kabu-github]:https://github.com/kabucom/kabusapi
