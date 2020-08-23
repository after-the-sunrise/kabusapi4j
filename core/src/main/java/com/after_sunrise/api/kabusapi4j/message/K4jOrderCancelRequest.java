package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * 注文取消
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jOrderCancelRequest {

    /**
     * トークン
     */
    @Nullable
    @SerializedName("Token")
    public abstract String getToken();

    /**
     * 注文パスワード
     */
    @Nullable
    @SerializedName("Password")
    public abstract String getPassword();

    /**
     * 注文番号
     */
    @Nullable
    @SerializedName("OrderId")
    public abstract String getOrderId();

}
