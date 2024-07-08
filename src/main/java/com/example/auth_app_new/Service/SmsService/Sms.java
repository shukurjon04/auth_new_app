package com.example.auth_app_new.Service.SmsService;

import com.example.auth_app_new.Exeption.SmsApiServerErrorException;
import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.tomcat.util.http.fileupload.util.Closeable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Sms {

    @Value("${spring.sms.api.email}")
    private String email;
    @Value("${spring.sms.api.password}")
    private String password;

    public String getTokenFromSmsServiceApi() {
        final HttpPost httpPost = new HttpPost("https://notify.eskiz.uz/api/auth/login");
        final MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addTextBody("email", this.email);
        multipartEntityBuilder.addTextBody("email", this.password);
        multipartEntityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA);

        final HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);
        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(httpPost)) {
            return new Gson().fromJson(EntityUtils.toString(response.getEntity()),SmsApiServiceToken.class).getData().getToken();
        } catch (Exception e) {
            throw new SmsApiServerErrorException("server error please try again");
        }
    }

    public boolean SendMessage(String phoneNumber, String Message) {
        final HttpPost post = new HttpPost("https://notify.eskiz.uz/api/message/sms/send");
        post.setHeader("Authorization", "Bearer" + getTokenFromSmsServiceApi());
        final MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addTextBody("mobile_phone", phoneNumber);
        entityBuilder.addTextBody("message", Message);

        final HttpEntity httpEntity = entityBuilder.build();
        post.setEntity(httpEntity);
        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(post);
        ) {
            return true;
        } catch (Exception e) {
           throw new SmsApiServerErrorException("server error exception");
        }

    }
}
