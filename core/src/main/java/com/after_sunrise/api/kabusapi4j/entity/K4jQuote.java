package com.after_sunrise.api.kabusapi4j.entity;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jSignType;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 気配
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jQuote {

    /**
     * 時刻
     */
    @Nullable
    @SerializedName("Time")
    public abstract Instant getTime();

    /**
     * 気配フラグ
     */
    @Nullable
    @SerializedName("Sign")
    public abstract K4jSignType getSign();

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

}
