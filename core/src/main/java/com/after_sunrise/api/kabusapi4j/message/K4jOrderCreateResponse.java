package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * 注文発注
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jOrderCreateResponse {

    /**
     * 結果コード
     */
    @Nullable
    @SerializedName("Result")
    public abstract Boolean getResult();

    /**
     * 受付注文番号
     */
    @Nullable
    @SerializedName("OrderId")
    public abstract String getOrderId();

}
