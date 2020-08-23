package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigDecimal;

/**
 * 取引余力（信用）
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jMarginResponse {

    /**
     * 信用新規可能額
     */
    @Nullable
    @SerializedName("MarginAccountWallet")
    public abstract BigDecimal getMarginAccountWallet();

    /**
     * 保証金維持率
     */
    @Nullable
    @SerializedName("DepositkeepRate")
    public abstract BigDecimal getDepositKeepRate();

    /**
     * 委託保証金率
     */
    @Nullable
    @SerializedName("ConsignmentDepositRate")
    public abstract BigDecimal getConsignmentDepositRate();

    /**
     * 現金委託保証金率
     */
    @Nullable
    @SerializedName("CashOfConsignmentDepositRate")
    public abstract BigDecimal getCashOfConsignmentDepositRate();

}
