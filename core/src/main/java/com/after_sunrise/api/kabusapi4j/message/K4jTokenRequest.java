package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * 認証
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jTokenRequest {

    /**
     * APIパスワード
     */
    @Nullable
    @SerializedName("APIPassword")
    public abstract String getApiPassword();

}
