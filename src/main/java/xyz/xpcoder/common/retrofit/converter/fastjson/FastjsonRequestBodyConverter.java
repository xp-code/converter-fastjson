package xyz.xpcoder.common.retrofit.converter.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

import java.io.IOException;

/**
 * fastjson response
 *
 * @author tiancai.zang
 * on 2018-08-27 18:34.
 */
public class FastjsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private SerializeConfig serializeConfig;

    public FastjsonRequestBodyConverter(SerializeConfig serializeConfig) {
        this.serializeConfig = serializeConfig;
    }

    @Override
    public RequestBody convert(T t) throws IOException {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(t, serializeConfig));
    }
}