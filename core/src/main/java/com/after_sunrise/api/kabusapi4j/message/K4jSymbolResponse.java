package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jBicCategoryType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jExchangeType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jPriceRangeGroupType;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 銘柄情報
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jSymbolResponse {

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
     * 銘柄略称
     */
    @Nullable
    @SerializedName("DisplayName")
    public abstract String getDisplayName();

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
     * 業種コード名
     */
    @Nullable
    @SerializedName("BisCategory")
    public abstract K4jBicCategoryType getBisCategory();

    /**
     * 時価総額
     */
    @Nullable
    @SerializedName("TotalMarketValue")
    public abstract BigDecimal getTotalMarketValue();

    /**
     * 発行済み株式数（千株）
     */
    @Nullable
    @SerializedName("TotalStocks")
    public abstract BigDecimal getTotalStocks();

    /**
     * 売買単位
     */
    @Nullable
    @SerializedName("TradingUnit")
    public abstract BigDecimal getTradingUnit();

    /**
     * 決算期日
     */
    @Nullable
    @SerializedName("FiscalYearEndBasic")
    public abstract LocalDate getFiscalYearEndBasic();

    /**
     * 呼値グループ
     */
    @Nullable
    @SerializedName("PriceRangeGroup")
    public abstract K4jPriceRangeGroupType getPriceRangeGroup();

    /**
     * 一般信用買建フラグ
     */
    @Nullable
    @SerializedName("KCMarginBuy")
    public abstract Boolean getKCMarginBuy();

    /**
     * 一般信用売建フラグ
     */
    @Nullable
    @SerializedName("KCMarginSell")
    public abstract Boolean getKCMarginSell();

    /**
     * 制度信用買建フラグ
     */
    @Nullable
    @SerializedName("MarginBuy")
    public abstract Boolean getMarginBuy();

    /**
     * 制度信用売建フラグ
     */
    @Nullable
    @SerializedName("MarginSell")
    public abstract Boolean getMarginSell();

    /**
     * 値幅上限
     */
    @Nullable
    @SerializedName("UpperLimit")
    public abstract BigDecimal getUpperLimit();

    /**
     * 値幅下限
     */
    @Nullable
    @SerializedName("LowerLimit")
    public abstract BigDecimal getLowerLimit();

}
