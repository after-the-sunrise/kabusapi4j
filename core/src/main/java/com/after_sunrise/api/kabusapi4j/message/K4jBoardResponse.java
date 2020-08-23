package com.after_sunrise.api.kabusapi4j.message;

import com.after_sunrise.api.kabusapi4j.entity.K4jBoard;
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
public abstract class K4jBoardResponse extends K4jBoard {
}
