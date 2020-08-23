package com.after_sunrise.api.kabusapi4j.entity;

/**
 * @author takanori.takase
 * @version 0.0.0
 */
public interface K4jType<T> {

    T getId();

    /**
     * エラーレスポンス
     */
    enum K4jErrorType implements K4jType<Integer> {

        /**
         * 内部エラー
         */
        INTERNAL_ERROR(4001001),

        /**
         * トリガキー生成エラー
         */
        TRIGGER_KEY_CREATION_ERROR(4001002),

        /**
         * エラー：Uターン
         */
        U_TURN_ERROR(4001003),

        /**
         * トリガ生成エラー
         */
        TRIGGER_CREATION_ERROR(4001004),

        /**
         * パラメータ変換エラー
         */
        PARAMETER_CONVERSION_ERROR(4001005),

        /**
         * API実行回数エラー
         */
        API_EXECUTION_RATE_ERROR(4001006),

        /**
         * ログイン認証エラー
         */
        LOGIN_AUTHENTICATION_ERROR(4001007),

        /**
         * API利用不可
         */
        API_STATUS(4001008),

        /**
         * APIキー不一致
         */
        API_KEY_MISMATCH(4001009),

        /**
         * クエリ文字列不正
         */
        INVALID_QUERY_STRING(4001010),

        /**
         * リクエスト文字列不正
         */
        INVALID_REQUEST_STRING(4001011),

        /**
         * リクエスト不正
         */
        INVALID_REQUEST(4001012),

        /**
         * トークン取得失敗
         */
        TOKEN_FETCH_FAILURE(4001013),

        /**
         * 許可されていないHTTPメソッド
         */
        UNAUTHORIZED_HTTP_METHOD(4001014),

        /**
         * ContentLength超過
         */
        CONTENT_LENGTH_EXCEEDED(4001015),

        /**
         * サポートされていないメディアタイプ
         */
        UNSUPPORTED_MEDIA_TYPE(4001016),

        /**
         * ローカル以外からのリクエストは許可されない
         */
        REMOTE_ACCESS_PROHIBITED(4001017),

        /**
         * 銘柄が登録できませんでした
         */
        INSTRUMENT_ADDITION_FAILURE(4001018),

        /**
         * 一部の銘柄が登録できませんでした
         */
        INSTRUMENT_ADDITION_PARTIAL_FAILURE(4001019),

        /**
         * 銘柄が解除できませんでした
         */
        INSTRUMENT_REMOVAL_FAILURE(4001020),

        /**
         * 一部の銘柄が解除できませんでした
         */
        INSTRUMENT_REMOVAL_PARTIAL_FAILURE(4001021),

        /**
         * 銘柄が見つからない
         */
        INSTRUMENT_NOT_FOUND(4002001),

        /**
         * 執行条件エラー
         */
        ORDER_CONDITION_ERROR_2(4002002),

        /**
         * 執行条件エラー
         */
        ORDER_CONDITION_ERROR_3(4002003),

        /**
         * トリガチェックエラー - 詳細はkabuSログファイルを確認してください
         */
        TRIGGER_CHECK_ERROR(4002004),

        /**
         * 商品エラー
         */
        PRODUCT_ERROR(4002005),

        /**
         * レジスト数エラー
         */
        REGISTRATION_COUNT_ERROR(4002006),

        /**
         * パラメータ不正：AccountType - 詳細はkabuSログファイルを確認してください
         */
        INVALID_ACCOUNT_TYPE(4002007),

        /**
         * パラメータ不正：Side - 詳細はkabuSログファイルを確認してください
         */
        INVALID_SIDE(4002008),

        /**
         * パラメータ不正：CashMargin - 詳細はkabuSログファイルを確認してください
         */
        INVALID_CASH_MARGIN(4002009),

        /**
         * パラメータ不正：DelivType - 詳細はkabuSログファイルを確認してください
         */
        INVALID_DELIV_TYPE_1(4002010),

        /**
         * パラメータ不正：FundType - 詳細はkabuSログファイルを確認してください
         */
        INVALID_FUND_TYPE(4002011),

        /**
         * パラメータ不正：FrontOrderType - 詳細はkabuSログファイルを確認してください
         */
        INVALID_FRONT_ORDER_TYPE(4002012),

        /**
         * パラメータ不正：MarginTradeType - 詳細はkabuSログファイルを確認してください
         */
        INVALID_MARGIN_TRADE_TYPE(4002013),

        /**
         * パラメータ不正：TimeInForce - 詳細はkabuSログファイルを確認してください
         */
        INVALID_TIME_IN_FORCE(4002014),

        /**
         * パラメータ不正：返済順指定と返済指定は同時に設定できない
         */
        INVALID_CLOSE_COMBINATION(4002015),

        /**
         * パラメータ不正：DelivType - 詳細はkabuSログファイルを確認してください
         */
        INVALID_DELIV_TYPE_2(4002016),

        /**
         * パラメータ不正：値段指定エラー
         */
        INVALID_PRICE(4002017),

        /**
         * パラメータ不正：IOC対象銘柄ではない - 詳細はkabuSログファイルを確認してください
         */
        INVALID_IOC_INSTRUMENT(4002018),

        /**
         * ワンショット：金額エラー
         */
        ONESHOT_NOTIONAL_ERROR(4003001),

        /**
         * ワンショット：数量エラー
         */
        ONESHOT_QUANTITY_ERROR(4003002),

        /**
         * 該当注文なしエラー
         */
        ORDER_NOT_FOUND(4004001),

        /**
         * 取消不可
         */
        CANCEL_FAILURE(4004002),

        ;

        private final Integer id;

        K4jErrorType(int id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 市場コード
     */
    enum K4jExchangeType implements K4jType<Integer> {

        /**
         * 東証
         */
        XTKS(1),

        /**
         * 名証
         */
        XNGO(3),

        /**
         * 福証
         */
        XFKA(5),

        /**
         * 札証
         */
        XSAP(6);

        private final Integer id;

        K4jExchangeType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 商品種別
     */
    enum K4jSecurityType implements K4jType<Integer> {

        /**
         * 株式
         */
        STOCK(1);

        private final Integer id;

        K4jSecurityType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 売買区分
     */
    enum K4jSideType implements K4jType<Integer> {

        /**
         * 売
         */
        SELL(1),

        /**
         * 買
         */
        BUY(2);

        private final Integer id;

        K4jSideType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 現物信用区分
     */
    enum K4jCashMarginType implements K4jType<Integer> {

        /**
         * 現物
         */
        CASH(1),

        /**
         * 信用新規
         */
        MARGIN_OPEN(2),

        /**
         * 信用返済
         */
        MARGIN_CLOSE(3);

        private final Integer id;

        K4jCashMarginType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 信用取引区分
     */
    enum K4jMarginTradeType implements K4jType<Integer> {

        /**
         * 制度信用
         */
        STANDARDIZED(1),

        /**
         * 一般信用
         */
        NEGOTIABLE(2),

        /**
         * 一般信用（売短）
         */
        NEGOTIABLE_SHORT(3);

        private final Integer id;

        K4jMarginTradeType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 受渡区分
     */
    enum K4jDelivType implements K4jType<Integer> {

        /**
         * 指定なし
         */
        NONE(0),

        /**
         * 自動振替
         */
        TRANSFER(1),

        /**
         * お預り金
         */
        DEPOSIT(2);

        private final Integer id;

        K4jDelivType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 資産区分
     */
    enum K4jFundType implements K4jType<String> {

        /**
         * 　指定なし
         */
        NONE("  "),

        /**
         * 　保護
         */
        PROTECT("02"),

        /**
         * 　信用代用
         */
        CREDIT("AA"),

        /**
         * 　証拠金代用
         */
        DEPOSIT("BB"),

        /**
         * 　信用取引
         */
        MARGIN("11");

        private final String id;

        K4jFundType(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }

    }

    /**
     * 口座種別
     */
    enum K4jAccountType implements K4jType<Integer> {

        /**
         * 一般
         */
        STANDARD(2),

        /**
         * 特定
         */
        SPECIFIC(4),

        /**
         * 法人
         */
        CORPORATE(12);

        private final Integer id;

        K4jAccountType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 決済順序
     */
    enum K4jClosePositionOrderType implements K4jType<Integer> {

        /**
         * 日付（古い順）、損益（高い順）
         */
        DATE_ASC_PL_DSC(0),

        /**
         * 日付（古い順）、損益（低い順）
         */
        DATE_ASC_PL_ASC(1),

        /**
         * 日付（新しい順）、損益（高い順
         */
        DATE_DSC_PL_DSC(2),

        /**
         * 日付（新しい順）、損益（低い順）
         */
        DATE_DSC_PL_ASC(3),

        /**
         * 損益（高い順）、日付（古い順）
         */
        PL_DSC_DATE_ASC(4),

        /**
         * 損益（高い順）、日付（新しい順）
         */
        PL_DSC_DATE_DSC(5),

        /**
         * 損益（低い順）、日付（古い順）
         */
        PL_ASC_DATE_ASC(6),

        /**
         * 損益（低い順）、日付（新しい順
         */
        PL_ASC_DATE_DSC(7);

        private final Integer id;

        K4jClosePositionOrderType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }


    /**
     * 口座種別
     */
    enum K4jFrontOrderType implements K4jType<Integer> {

        /**
         * 成行
         */
        MARKET(10),

        /**
         * 寄成（前場）
         */
        MARKET_ON_AM_OPEN(13),

        /**
         * 寄成（後場）
         */
        MARKETON_PM_OPEN(14),

        /**
         * 引成（前場）
         */
        MARKET_ON_AM_CLOSE(15),

        /**
         * 引成（後場）
         */
        MARKET_ON_PM_CLOSE(16),

        /**
         * IOC成行
         */
        MARKET_IOC(17),

        /**
         * 指値
         */
        LIMIT(20),

        /**
         * 寄指（前場）
         */
        LIMIT_ON_AM_OPEN(21),

        /**
         * 寄指（後場）
         */
        LIMIT_ON_PM_OPEN(22),

        /**
         * 引指（前場）
         */
        LIMIT_ON_AM_CLOSE(23),

        /**
         * 引指（後場）
         */
        LIMIT_ON_PM_CLOSE(24),

        /**
         * 不成（前場）
         */
        FUNARI_AM(25),

        /**
         * 不成（後場）
         */
        FUNARI_PM(26),

        /**
         * IOC指値
         */
        LIMIT_IOC(27);

        private final Integer id;

        K4jFrontOrderType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 現値前値比較
     */
    enum K4jCurrentPriceChangeStatusType implements K4jType<String> {

        /**
         * 事象なし
         */
        NONE("0000"),

        /**
         * 変わらず
         */
        SAME("0056"),

        /**
         * UP
         */
        UP("0057"),

        /**
         * DOWN
         */
        DOWN("0058"),

        /**
         * 中断板寄り後の初値
         */
        RESUME("0059"),

        /**
         * ザラバ引け
         */
        ZARABA_CLOSE("0060"),

        /**
         * 板寄り引け
         */
        OPEN_CLOSE("0061"),

        /**
         * 中断引け
         */
        SUSPEND_CLOSE("0062"),

        /**
         * ダウン引け
         */
        DOWN_CLOSE("0063"),

        /**
         * 逆転終値
         */
        REVERSED_CLOSE("0064"),

        /**
         * 特別気配引け
         */
        SQ_CLOSE("0066"),

        /**
         * 一時留保引け
         */
        RESERVE_CLOSE("0067"),

        /**
         * 売買停止引け
         */
        HALT_CLOSE("0068"),

        /**
         * サーキットブレーカ引け
         */
        CB_CLOSE("0069"),

        /**
         * ダイナミックサーキットブレーカ引け
         */
        DYNAMIC_CB_CLOSE("0431");

        private final String id;

        K4jCurrentPriceChangeStatusType(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }

    }

    /**
     * 現値ステータス
     */
    enum K4jCurrentPriceStatusType implements K4jType<Integer> {

        /**
         * 現値
         */
        LAST(1),

        /**
         * 不連続歩み
         */
        TICK(2),

        /**
         * 板寄せ
         */
        AUCTION(3),

        /**
         * システム障害
         */
        SYSTEM_FAILURE(4),

        /**
         * 中断
         */
        SUSPEND(5),

        /**
         * 売買停止
         */
        HALT(6),

        /**
         * 売買停止・システム停止解除
         */
        HALT_END(7),

        /**
         * 終値
         */
        CLOSE(8),

        /**
         * システム停止
         */
        SYSTEM_SUSPEND(9),

        /**
         * 概算値
         */
        ESTIMATE(10),

        /**
         * 参考値
         */
        REFERENCE(11),

        /**
         * サーキットブレイク実施中
         */
        CIRCUIT_BREAK(12),

        /**
         * システム障害解除
         */
        SYSTEM_FAILURE_RECOVERY(13),

        /**
         * サーキットブレイク解除
         */
        CIRCUIT_BREAK_END(14),

        /**
         * 中断解除
         */
        SUSPEND_RESUME(15),

        /**
         * 一時留保中
         */
        PENDING(16),

        /**
         * 一時留保解除
         */
        PENDING_END(17),

        /**
         * ファイル障害
         */
        FILE_FAILURE(18),

        /**
         * ファイル障害解除
         */
        FILE_FAILURE_RECOVERY(19),

        /**
         * Spread/Strategy
         */
        SPREAD_STRATEGY(20),

        /**
         * ダイナミックサーキットブレイク発動
         */
        DYNAMIC_CB(21),

        /**
         * ダイナミックサーキットブレイク解除
         */
        DYNAMIC_CB_END(22),

        /**
         * 板寄せ約定
         */
        AUCTION_MATCH(23);

        private final Integer id;

        K4jCurrentPriceStatusType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 最良気配フラグ
     */
    enum K4jSignType implements K4jType<String> {

        /**
         * 事象なし
         */
        NONE("0000"),

        /**
         * 一般気配
         */
        GENERAL("0101"),

        /**
         * 特別気配
         */
        SPECIAL("0102"),

        /**
         * 注意気配
         */
        WARNING("0103"),

        /**
         * 寄前気配
         */
        PRE_OPEN("0107"),

        /**
         * 停止前特別気配
         */
        PRE_SQ("0108"),

        /**
         * 引け後気配
         */
        POST_CLOSE("0109"),

        /**
         * 寄前気配約定成立ポイントなし
         */
        PRE_OPEN_NO_MATCH("0116"),

        /**
         * 寄前気配約定成立ポイントあり
         */
        PRE_OPEN_WITH_MATCH("0117"),

        /**
         * 連続約定気配
         */
        SEQUENCE("0118"),

        /**
         * 停止前の連続約定気配
         */
        SEQUENCE_SUSPEND("0119"),

        /**
         * 買い上がり売り下がり中
         */
        PUMP_DUMP("0120");

        private final String id;

        K4jSignType(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }

    }

    /**
     * 業種コード名
     */
    enum K4jBicCategoryType implements K4jType<String> {

        /**
         * 水産・農林業
         */
        AGRICULTURE("0050"),

        /**
         * 鉱業
         */
        MINING("1050"),

        /**
         * 建設業
         */
        CONSTRUCTION("2050"),

        /**
         * 食料品
         */
        GLOCERY("3050"),

        /**
         * 繊維製品
         */
        TEXTILE("3100"),

        /**
         * パルプ・紙
         */
        PAPER("3150"),

        /**
         * 化学
         */
        CHEMICAL("3200"),

        /**
         * 医薬品
         */
        PHARMACEUTICAL("3250"),

        /**
         * 石油・石炭製品
         */
        OIL("3300"),

        /**
         * ゴム製品
         */
        RUBBER("3350"),

        /**
         * ガラス・土石製品
         */
        GLASS("3400"),

        /**
         * 鉄鋼
         */
        STEEL("3450"),

        /**
         * 非鉄金属
         */
        NONFERROUS_METAL("3500"),

        /**
         * 金属製品
         */
        METAL_PRODUCT("3550"),

        /**
         * 機械
         */
        MACHINERY("3600"),

        /**
         * 電気機器
         */
        ELECTRONIC_EQUIPMENT("3650"),

        /**
         * 輸送用機器
         */
        TRANSPORTATION_EQUIPMENT("3700"),

        /**
         * 精密機器
         */
        PRECISION_EQUIPMENT("3750"),

        /**
         * その他製品
         */
        PRODUCT("3800"),

        /**
         * 電気・ガス業
         */
        ELECTRICITY_GAS("4050"),

        /**
         * 陸運業
         */
        GROUND_TRANSPORTATION("5050"),

        /**
         * 海運業
         */
        SEA_TRANSPORTATION("5100"),

        /**
         * 空運業
         */
        AIR_TRANSPORTATION("5150"),

        /**
         * 倉庫・運輸関連業
         */
        WAREHOUSE("5200"),

        /**
         * 情報・通信業
         */
        ICT("5250"),

        /**
         * 卸売業
         */
        WHOLESALE("6050"),

        /**
         * 小売業
         */
        RETAIL("6100"),

        /**
         * 銀行業
         */
        BANKING("7050"),

        /**
         * 証券、商品先物取引業
         */
        SECURITIES("7100"),

        /**
         * 保険業
         */
        INSURANCE("7150"),

        /**
         * その他金融業
         */
        FINANCE("7200"),

        /**
         * 不動産業
         */
        REAL_ESTATE("8050"),

        /**
         * サービス業
         */
        SERVICE("9050"),

        /**
         * その他
         */
        OTHER("9999");

        private final String id;

        K4jBicCategoryType(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }

    }

    /**
     * 呼値グループ
     */
    enum K4jPriceRangeGroupType implements K4jType<String> {

        /**
         * 整数
         */
        INTEGER("10000"),

        /**
         * 小数
         */
        DECIMAL("10003");

        private final String id;

        K4jPriceRangeGroupType(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }

    }

    /**
     * 商品
     */
    enum K4jProductType implements K4jType<Integer> {

        /**
         * すべて
         */
        ALL(0),

        /**
         * 現物
         */
        CASH(1),

        /**
         * 信用
         */
        MARGIN(2);

        private final Integer id;

        K4jProductType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 状態 / 注文状態
     */
    enum K4jStateType implements K4jType<Integer> {

        /**
         * 待機（発注待機）
         */
        WAITING(1),

        /**
         * 処理中（発注送信中）
         */
        PROCESSING(2),

        /**
         * 処理済（発注済・訂正済）
         */
        PROCESSED(3),

        /**
         * 訂正取消送信中
         */
        REPLACING(4),

        /**
         * 終了（発注エラー・取消済・全約定・失効・期限切れ）
         */
        TERMINATED(5);

        private final Integer id;

        K4jStateType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 執行条件
     */
    enum K4jOrdType implements K4jType<Integer> {

        /**
         * ザラバ
         */
        ZARABA(1),

        /**
         * 寄り
         */
        OPEN(2),

        /**
         * 引け
         */
        CLOSE(3),

        /**
         * 不成
         */
        FUNARI(4);

        private final Integer id;

        K4jOrdType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 明細種別
     */
    enum K4jRecType implements K4jType<Integer> {

        /**
         * 受付
         */
        ACCEPT(1),

        /**
         * 繰越
         */
        CARRY(2),

        /**
         * 期限切れ
         */
        EXPIRE(3),

        /**
         * 発注
         */
        CREATE(4),

        /**
         * 訂正
         */
        REPLACE(5),

        /**
         * 取消
         */
        CANCEL(6),

        /**
         * 失効
         */
        TERMINATE(7),

        /**
         * 約定
         */
        EXECUTE(8);

        private final Integer id;

        K4jRecType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

    /**
     * 状態 / 約定状態
     */
    enum K4jExecType implements K4jType<Integer> {

        /**
         * 待機（発注待機）
         */
        WAITING(1),

        /**
         * 処理中（発注送信中・訂正送信中・取消送信中）
         */
        PROCESSING(2),

        /**
         * 処理済（発注済・訂正済・取消済・全約定・期限切れ）
         */
        PROCESSED(3),

        /**
         * エラー
         */
        ERROR(4),

        /**
         * 削除済み
         */
        DELETED(5);

        private final Integer id;

        K4jExecType(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getId() {
            return id;
        }

    }

}
