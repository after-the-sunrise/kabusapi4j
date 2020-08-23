package com.after_sunrise.api.kabusapi4j.entity;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigDecimal;

/**
 * 返済建玉指定
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jHolding {

    /**
     * 返済建玉ID
     */
    @Nullable
    @SerializedName("HoldID")
    public abstract String getHoldId();

    /**
     * 返済建玉数量
     */
    @Nullable
    @SerializedName("Qty")
    public abstract BigDecimal getQty();

}
