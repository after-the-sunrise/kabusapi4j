package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jInstrument;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * 取引余力（信用）
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jMarginRequest {

    /**
     * トークン
     */
    @Nullable
    @SerializedName("Token")
    public abstract String getToken();

    /**
     * 銘柄指定
     */
    @Nullable
    @SerializedName("Instrument")
    public abstract K4jInstrument getInstrument();

}
