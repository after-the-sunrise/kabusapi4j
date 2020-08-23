package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jHolding;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jAccountType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jCashMarginType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jClosePositionOrderType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jDelivType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jExchangeType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jFrontOrderType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jFundType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jMarginTradeType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jSecurityType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jSideType;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 注文発注
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jOrderCreateRequest {

    /**
     * トークン
     */
    @Nullable
    @SerializedName("Token")
    public abstract String getToken();

    /**
     * 注文パスワード
     */
    @Nullable
    @SerializedName("Password")
    public abstract String getPassword();

    /**
     * 銘柄コード
     */
    @Nullable
    @SerializedName("Symbol")
    public abstract String getSymbol();

    /**
     * 市場コード
     */
    @Nullable
    @SerializedName("Exchange")
    public abstract K4jExchangeType getExchange();

    /**
     * 商品種別
     */
    @Nullable
    @SerializedName("SecurityType")
    public abstract K4jSecurityType getSecurityType();

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
     * 信用取引区分
     */
    @Nullable
    @SerializedName("MarginTradeType")
    public abstract K4jMarginTradeType getMarginTradeType();

    /**
     * 受渡区分
     */
    @Nullable
    @SerializedName("DelivType")
    public abstract K4jDelivType getDelivType();

    /**
     * 資産区分
     */
    @Nullable
    @SerializedName("FundType")
    public abstract K4jFundType getFundType();

    /**
     * 口座種別
     */
    @Nullable
    @SerializedName("AccountType")
    public abstract K4jAccountType getAccountType();

    /**
     * 注文数量
     */
    @Nullable
    @SerializedName("Qty")
    public abstract BigDecimal getQty();

    /**
     * 決済順序
     */
    @Nullable
    @SerializedName("ClosePositionOrder")
    public abstract K4jClosePositionOrderType getClosePositionOrder();

    /**
     * 返済建玉指定
     */
    @Nullable
    @SerializedName("ClosePositions")
    public abstract List<K4jHolding> getClosePositions();

    /**
     * 注文価格
     */
    @Nullable
    @SerializedName("Price")
    public abstract BigDecimal getPrice();

    /**
     * 注文有効期限
     */
    @Nullable
    @SerializedName("ExpireDay")
    public abstract LocalDate getExpireDay();

    /**
     * 執行条件
     */
    @Nullable
    @SerializedName("FrontOrderType")
    public abstract K4jFrontOrderType getFrontOrderType();

}
