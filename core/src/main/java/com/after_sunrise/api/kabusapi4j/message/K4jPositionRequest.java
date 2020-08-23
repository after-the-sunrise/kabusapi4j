package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jType.K4jProductType;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * 残高照会
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jPositionRequest {

    /**
     * トークン
     */
    @Nullable
    @SerializedName("Token")
    public abstract String getToken();

    /**
     * 取得する商品
     */
    @Nullable
    @SerializedName("Product")
    public abstract K4jProductType getProduct();

}
