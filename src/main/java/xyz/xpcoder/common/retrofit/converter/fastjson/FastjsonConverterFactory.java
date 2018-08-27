package xyz.xpcoder.common.retrofit.converter.fastjson;

import com.alibaba.fastjson.serializer.SerializeConfig;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * fastjson converter
 *
 * @author tiancai.zang
 * on 2018-08-27 18:33.
 */
public class FastjsonConverterFactory extends Converter.Factory {

    private final SerializeConfig serializeConfig;

    private FastjsonConverterFactory(SerializeConfig serializeConfig) {
        Objects.requireNonNull(serializeConfig);
        this.serializeConfig = serializeConfig;
    }

    public static FastjsonConverterFactory create() {
        return create(SerializeConfig.getGlobalInstance());
    }

    public static FastjsonConverterFactory create(SerializeConfig serializeConfig) {
        return new FastjsonConverterFactory(serializeConfig);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastjsonResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastjsonRequestBodyConverter<>(this.serializeConfig);
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }
}