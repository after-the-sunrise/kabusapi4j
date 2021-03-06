package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.byoa.Nullable;
import com.after_sunrise.api.kabusapi4j.entity.K4jInstrument;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.List;

/**
 * 銘柄登録
 *
 * @author takanori.takase
 * @version 0.0.0
 */
@Gson.TypeAdapters
@Value.Immutable(singleton = true)
public abstract class K4jRegisterResponse {

    /**
     * 現在登録されている銘柄のリスト
     */
    @Nullable
    @SerializedName("RegistList")
    public abstract List<K4jInstrument> getSymbols();

}
