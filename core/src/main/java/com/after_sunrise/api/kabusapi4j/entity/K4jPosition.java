package com.after_sunrise.api.kabusapi4j.entity;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jAccountType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jExchangeType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jMarginTradeType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jSideType;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 残高
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jPosition {

    /**
     * 約定番号
     */
    @Nullable
    @SerializedName("ExecutionID")
    public abstract String getExecutionId();

    /**
     * 口座種別
     */
    @Nullable
    @SerializedName("AccountType")
    public abstract K4jAccountType getAccountType();

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
     * 約定日（建玉日）
     */
    @Nullable
    @SerializedName("ExecutionDay")
    public abstract LocalDate getExecutionDay();

    /**
     * 値段
     */
    @Nullable
    @SerializedName("Price")
    public abstract BigDecimal getPrice();

    /**
     * 残数量
     */
    @Nullable
    @SerializedName("LeavesQty")
    public abstract BigDecimal getLeavesQty();

    /**
     * 拘束数量（保有数量）
     */
    @Nullable
    @SerializedName("HoldQty")
    public abstract BigDecimal getHoldQty();

    /**
     * 売買区分
     */
    @Nullable
    @SerializedName("Side")
    public abstract K4jSideType getSide();

    /**
     * 諸経費
     */
    @Nullable
    @SerializedName("Expenses")
    public abstract BigDecimal getExpenses();

    /**
     * 手数料
     */
    @Nullable
    @SerializedName("Commission")
    public abstract BigDecimal getCommission();

    /**
     * 手数料消費税
     */
    @Nullable
    @SerializedName("CommissionTax")
    public abstract BigDecimal getCommissionTax();

    /**
     * 返済期日
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
     * 現在値
     */
    @Nullable
    @SerializedName("CurrentPrice")
    public abstract BigDecimal getCurrentPrice();

    /**
     * 評価金額
     */
    @Nullable
    @SerializedName("Valuation")
    public abstract BigDecimal getValuation();

    /**
     * 評価損益額
     */
    @Nullable
    @SerializedName("ProfitLoss")
    public abstract BigDecimal getProfitLoss();

    /**
     * 評価損益率
     */
    @Nullable
    @SerializedName("ProfitLossRate")
    public abstract BigDecimal getProfitLossRate();

}
