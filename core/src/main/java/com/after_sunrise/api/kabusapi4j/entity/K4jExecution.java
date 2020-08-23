package com.after_sunrise.api.kabusapi4j.entity;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jExecType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jOrdType;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jRecType;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * 約定
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jExecution {

    /**
     * 通番
     */
    @Nullable
    @SerializedName("SeqNum")
    public abstract Integer getSeqNum();

    /**
     * 注文詳細番号
     */
    @Nullable
    @SerializedName("ID")
    public abstract String getId();

    /**
     * 明細種別
     */
    @Nullable
    @SerializedName("RecType")
    public abstract K4jRecType getRecType();

    /**
     * 取引所番号
     */
    @Nullable
    @SerializedName("ExchangeID")
    public abstract String getExchangeId();

    /**
     * 状態
     */
    @Nullable
    @SerializedName("State")
    public abstract K4jExecType getState();

    /**
     * 処理時刻
     */
    @Nullable
    @SerializedName("TransactTime")
    public abstract Instant getTransactTime();

    /**
     * 執行条件
     */
    @Nullable
    @SerializedName("OrdType")
    public abstract K4jOrdType getOrdType();

    /**
     * 値段
     */
    @Nullable
    @SerializedName("Price")
    public abstract BigDecimal getPrice();

    /**
     * 数量
     */
    @Nullable
    @SerializedName("Qty")
    public abstract BigDecimal getQty();

    /**
     * 約定番号
     */
    @Nullable
    @SerializedName("ExecutionID")
    public abstract String getExecutionId();

    /**
     * 約定日時
     */
    @Nullable
    @SerializedName("ExecutionDay")
    public abstract Instant getExecutionDay();

    /**
     * 受渡日
     */
    @Nullable
    @SerializedName("DelivDay")
    public abstract LocalDate getDelivDay();

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

}
