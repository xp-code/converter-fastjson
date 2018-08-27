package xyz.xpcoder.common.retrofit.converter.fastjson;

import com.alibaba.fastjson.JSON;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;

import java.io.IOException;

@Tag("FastjsonConverterFactoryTest")
public class FastjsonConverterFactoryTest {

    private MockWebServer webServer;


    @BeforeAll
    public void setUp() {
        webServer = new MockWebServer();

        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .addHeader("cookies","cookies")
                .setBody(JSON.toJSONString(new BookResDTO(200,"操作成功",Boolean.TRUE)
        )));

        try {
            webServer.start(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test(){

        System.out.println(webServer.getHostName()+" "+ webServer.getPort());
    }



    static class BookReqDTO{

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

    static class BookResDTO{

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