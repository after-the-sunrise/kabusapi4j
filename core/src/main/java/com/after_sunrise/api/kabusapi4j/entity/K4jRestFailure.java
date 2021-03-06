package com.after_sunrise.api.kabusapi4j.entity;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * RESTエラー
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jRestFailure {

    /**
     * エラーコード
     */
    @Nullable
    @SerializedName("Code")
    public abstract Integer getErrorCode();

    /**
     * エラーメッセージ
     */
    @Nullable
    @SerializedName("Message")
    public abstract String getErrorMessage();

}
