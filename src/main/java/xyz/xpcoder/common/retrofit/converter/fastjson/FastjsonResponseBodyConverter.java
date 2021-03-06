package xyz.xpcoder.common.retrofit.converter.fastjson;

import com.alibaba.fastjson.JSON;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * fastjson response
 *
 * @author tiancai.zang
 * on 2018-08-27 18:34.
 */
final class FastjsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;

    public FastjsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody t) throws IOException {
        return JSON.parseObject(t.byteStream(), type);
    }
}