package xyz.xpcoder.common.retrofit.converter.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import okhttp3.Protocol;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FastjsonConverterFactoryTest {


    @Rule
    private MockWebServer webServer = new MockWebServer();


    @BeforeEach
    void setUp() throws IOException {

        webServer.start(8080);

        MockResponse response = new MockResponse()
                .addHeader("cookies", "cookies")
                .setBody(JSON.toJSONString(new BookResDTO(200, "操作成功", Boolean.TRUE)
                ));

        webServer.enqueue(response);

    }


    @AfterEach
    void tearDown() throws IOException {
        webServer.shutdown();
    }

    @Test
    void create() throws IOException {

        SerializeConfig serializeConfig = SerializeConfig.getGlobalInstance();
        serializeConfig.setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCase);

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(webServer.url("/"))
                .addConverterFactory(FastjsonConverterFactory.create())
                .build();

        Response<BookResDTO> resDTOResponse = retrofit.create(BookService.class)
                .book(new BookReqDTO("", 123.12))
                .execute();

        System.out.println(resDTOResponse.body().getCode());


    }

    public interface BookService{

        @POST("/")
        Call<BookResDTO> book(@Body BookReqDTO req);
    }

    public static class BookReqDTO{

        private String name;
        private Double price;

        public BookReqDTO(String name, Double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

    public static class BookResDTO{

        private Integer code;
        private String message;
        private Boolean success;

        public BookResDTO(Integer code, String message, Boolean success) {
            this.code = code;
            this.message = message;
            this.success = success;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }
    }
}