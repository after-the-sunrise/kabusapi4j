package com.after_sunrise.api.kabusapi4j.entity;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jAccountType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jCashMarginType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jDelivType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jExchangeType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jMarginTradeType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jOrdType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jSideType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jStateType;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * 注文
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jOrder {

    /**
     * 注文番号
     */
    @Nullable
    @SerializedName("ID")
    public abstract String getId();

    /**
     * 状態
     */
    @Nullable
    @SerializedName("State")
    public abstract K4jStateType getState();

    /**
     * 注文状態
     */
    @Nullable
    @SerializedName("OrderState")
    public abstract K4jStateType getOrderState();

    /**
     * 執行条件
     */
    @Nullable
    @SerializedName("OrdType")
    public abstract K4jOrdType getOrdType();

    /**
     * 受注日時
     */
    @Nullable
    @SerializedName("RecvTime")
    public abstract Instant getRecvTime();

    /**
     * 銘柄コード
     */
    @Nullable
    @SerializedName("Symbol")
    public abstract String getSymbol();

    /**
     * 銘柄名
     */
    @Nullable
    @SerializedName("SymbolName")
    public abstract String getSymbolName();

    /**
     * 市場コード
     */
    @Nullable
    @SerializedName("Exchange")
    public abstract K4jExchangeType getExchange();

    /**
     * 市場名
     */
    @Nullable
    @SerializedName("ExchangeName")
    public abstract String getExchangeName();

    /**
     * 値段
     */
    @Nullable
    @SerializedName("Price")
    public abstract BigDecimal getPrice();

    /**
     * 発注数量
     */
    @Nullable
    @SerializedName("OrderQty")
    public abstract BigDecimal getOrderQty();

    /**
     * 約定数量
     */
    @Nullable
    @SerializedName("CumQty")
    public abstract BigDecimal getCumQty();

    /**
     * 売買区分
     */
    @Nullable
    @SerializedName("Side")
    public abstract K4jSideType getSide();

    /**
     * 現物信用区分
     */
    @Nullable
    @SerializedName("CashMargin")
    public abstract K4jCashMarginType getCashMargin();

    /**
     * 口座種別
     */
    @Nullable
    @SerializedName("AccountType")
    public abstract K4jAccountType getAccountType();

    /**
     * 受渡区分
     */
    @Nullable
    @SerializedName("DelivType")
    public abstract K4jDelivType getDelivType();

    /**
     * 注文有効期限
     */
    @Nullable
    @SerializedName("ExpireDay")
    public abstract LocalDate getExpireDay();

    /**
     * 信用取引区分
     */
    @Nullable
    @SerializedName("MarginTradeType")
    public abstract K4jMarginTradeType getMarginTradeType();

    /**
     * 注文詳細
     */
    @Nullable
    @SerializedName("Details")
    public abstract List<K4jExecution> getDetails();

}
