package com.after_sunrise.api.kabusapi4j.entity;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jCurrentPriceChangeStatusType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jCurrentPriceStatusType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jExchangeType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jSignType;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 時価情報・板情報
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jBoard {

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
     * 市場名称
     */
    @Nullable
    @SerializedName("ExchangeName")
    public abstract String getExchangeName();

    /**
     * 現値
     */
    @Nullable
    @SerializedName("CurrentPrice")
    public abstract BigDecimal getCurrentPrice();

    /**
     * 現値時刻
     */
    @Nullable
    @SerializedName("CurrentPriceTime")
    public abstract Instant getCurrentPriceTime();

    /**
     * 現値前値比較
     */
    @Nullable
    @SerializedName("CurrentPriceChangeStatus")
    public abstract K4jCurrentPriceChangeStatusType getCurrentPriceChangeStatus();

    /**
     * 現値ステータス
     */
    @Nullable
    @SerializedName("CurrentPriceStatus")
    public abstract K4jCurrentPriceStatusType getCurrentPriceStatus();

    /**
     * 計算用現値
     */
    @Nullable
    @SerializedName("CalcPrice")
    public abstract BigDecimal getCalcPrice();

    /**
     * 前日終値
     */
    @Nullable
    @SerializedName("PreviousClose")
    public abstract BigDecimal getPreviousClose();

    /**
     * 前日終値日付
     */
    @Nullable
    @SerializedName("PreviousCloseTime")
    public abstract Instant getPreviousCloseTime();

    /**
     * 前日比
     */
    @Nullable
    @SerializedName("ChangePreviousClose")
    public abstract BigDecimal getChangePreviousClose();

    /**
     * 騰落率
     */
    @Nullable
    @SerializedName("ChangePreviousClosePer")
    public abstract BigDecimal getChangePreviousClosePer();

    /**
     * 始値
     */
    @Nullable
    @SerializedName("OpeningPrice")
    public abstract BigDecimal getOpeningPrice();

    /**
     * 始値時刻
     */
    @Nullable
    @SerializedName("OpeningPriceTime")
    public abstract Instant getOpeningPriceTime();

    /**
     * 高値
     */
    @Nullable
    @SerializedName("HighPrice")
    public abstract BigDecimal getHighPrice();

    /**
     * 高値時刻
     */
    @Nullable
    @SerializedName("HighPriceTime")
    public abstract Instant getHighPriceTime();

    /**
     * 安値
     */
    @Nullable
    @SerializedName("LowPrice")
    public abstract BigDecimal getLowPrice();

    /**
     * 安値時刻
     */
    @Nullable
    @SerializedName("LowPriceTime")
    public abstract Instant getLowPriceTime();

    /**
     * 売買高
     */
    @Nullable
    @SerializedName("TradingVolume")
    public abstract BigDecimal getTradingVolume();

    /**
     * 売買高時刻
     */
    @Nullable
    @SerializedName("TradingVolumeTime")
    public abstract Instant getTradingVolumeTime();

    /**
     * 売買高加重平均価格（VWAP）
     */
    @Nullable
    @SerializedName("VWAP")
    public abstract BigDecimal getVwap();

    /**
     * 売買代金
     */
    @Nullable
    @SerializedName("TradingValue")
    public abstract BigDecimal getTradingValue();

    /**
     * 最良売気配数量
     */
    @Nullable
    @SerializedName("BidQty")
    public abstract BigDecimal getBidQty();

    /**
     * 最良売気配値段
     */
    @Nullable
    @SerializedName("BidPrice")
    public abstract BigDecimal getBidPrice();

    /**
     * 最良売気配時刻
     */
    @Nullable
    @SerializedName("BidTime")
    public abstract Instant getBidTime();

    /**
     * 最良売気配フラグ
     */
    @Nullable
    @SerializedName("BidSign")
    public abstract K4jSignType getBidSign();

    /**
     * 売成行数量
     */
    @Nullable
    @SerializedName("MarketOrderSellQty")
    public abstract BigDecimal getMarketOrderSellQty();

    /**
     * 売気配数量1本目
     */
    @Nullable
    @SerializedName("Sell1")
    public abstract K4jQuote getSell1();

    /**
     * 売気配数量2本目
     */
    @Nullable
    @SerializedName("Sell2")
    public abstract K4jQuote getSell2();

    /**
     * 売気配数量3本目
     */
    @Nullable
    @SerializedName("Sell3")
    public abstract K4jQuote getSell3();

    /**
     * 売気配数量4本目
     */
    @Nullable
    @SerializedName("Sell4")
    public abstract K4jQuote getSell4();

    /**
     * 売気配数量5本目
     */
    @Nullable
    @SerializedName("Sell5")
    public abstract K4jQuote getSell5();

    /**
     * 売気配数量6本目
     */
    @Nullable
    @SerializedName("Sell6")
    public abstract K4jQuote getSell6();

    /**
     * 売気配数量7本目
     */
    @Nullable
    @SerializedName("Sell7")
    public abstract K4jQuote getSell7();

    /**
     * 売気配数量8本目
     */
    @Nullable
    @SerializedName("Sell8")
    public abstract K4jQuote getSell8();

    /**
     * 売気配数量9本目
     */
    @Nullable
    @SerializedName("Sell9")
    public abstract K4jQuote getSell9();

    /**
     * 売気配数量10本目
     */
    @Nullable
    @SerializedName("Sell10")
    public abstract K4jQuote getSell10();

    /**
     * 最良買気配数量
     */
    @Nullable
    @SerializedName("AskQty")
    public abstract BigDecimal getAskQty();

    /**
     * 最良買気配値段
     */
    @Nullable
    @SerializedName("AskPrice")
    public abstract BigDecimal getAskPrice();

    /**
     * 最良買気配時刻
     */
    @Nullable
    @SerializedName("AskTime")
    public abstract Instant getAskTime();

    /**
     * 最良買気配フラグ
     */
    @Nullable
    @SerializedName("AskSign")
    public abstract K4jSignType getAskSign();

    /**
     * 買成行数量
     */
    @Nullable
    @SerializedName("MarketOrderBuyQty")
    public abstract BigDecimal getMarketOrderBuyQty();

    /**
     * 買気配数量1本目
     */
    @Nullable
    @SerializedName("Buy1")
    public abstract K4jQuote getBuy1();

    /**
     * 買気配数量2本目
     */
    @Nullable
    @SerializedName("Buy2")
    public abstract K4jQuote getBuy2();

    /**
     * 買気配数量3本目
     */
    @Nullable
    @SerializedName("Buy3")
    public abstract K4jQuote getBuy3();

    /**
     * 買気配数量4本目
     */
    @Nullable
    @SerializedName("Buy4")
    public abstract K4jQuote getBuy4();

    /**
     * 買気配数量5本目
     */
    @Nullable
    @SerializedName("Buy5")
    public abstract K4jQuote getBuy5();

    /**
     * 買気配数量6本目
     */
    @Nullable
    @SerializedName("Buy6")
    public abstract K4jQuote getBuy6();

    /**
     * 買気配数量7本目
     */
    @Nullable
    @SerializedName("Buy7")
    public abstract K4jQuote getBuy7();

    /**
     * 買気配数量8本目
     */
    @Nullable
    @SerializedName("Buy8")
    public abstract K4jQuote getBuy8();

    /**
     * 買気配数量9本目
     */
    @Nullable
    @SerializedName("Buy9")
    public abstract K4jQuote getBuy9();

    /**
     * 買気配数量10本目
     */
    @Nullable
    @SerializedName("Buy10")
    public abstract K4jQuote getBuy10();

    /**
     * OVER気配数量
     */
    @Nullable
    @SerializedName("OverSellQty")
    public abstract BigDecimal getOverSellQty();

    /**
     * UNDER気配数量
     */
    @Nullable
    @SerializedName("UnderBuyQty")
    public abstract BigDecimal getUnderBuyQty();

    /**
     * 時価総額
     */
    @Nullable
    @SerializedName("TotalMarketValue")
    public abstract BigDecimal getTotalMarketValue();

}
