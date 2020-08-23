package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigDecimal;

/**
 * 取引余力（現物）
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jCashResponse {

    /**
     * 現物買付可能額
     */
    @Nullable
    @SerializedName("StockAccountWallet")
    public abstract BigDecimal getStockAccountWallet();

}
