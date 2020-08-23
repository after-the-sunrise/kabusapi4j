package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jInstrument;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * 時価情報・板情報
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jBoardRequest extends K4jInstrument {

    /**
     * トークン
     */
    @Nullable
    @SerializedName("Token")
    public abstract String getToken();

}
