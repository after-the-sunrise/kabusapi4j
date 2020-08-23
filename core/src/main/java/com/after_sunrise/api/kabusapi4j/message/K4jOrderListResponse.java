package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jOrder;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.List;

/**
 * 注文約定照会
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jOrderListResponse {

    /**
     * 注文約定
     */
    @Nullable
    @SerializedName("Orders")
    public abstract List<K4jOrder> getOrders();

}
