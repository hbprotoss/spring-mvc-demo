package io.hbprotoss.web.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.hbprotoss.web.SpringfoxJsonToGsonAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import springfox.documentation.spring.web.json.Json;

/**
 * Created by hbprotoss on 6/27/16.
 * springfox兼容GSON的patch
 */
@Configuration
public class GsonHttpMessageConverterConfig {
    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson());
        return converter;
    }

    private Gson gson() {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter());
        return builder.create();
    }
}
