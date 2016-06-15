package io.hbprotoss.web.interceptor;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hbprotoss on 12/15/15.
 */
public class RequestInteceptor implements ClientHttpRequestInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInteceptor.class);

    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String originBody = new String(body, "UTF-8");
        originBody += "blablabla";
        LOGGER.debug(originBody);

        final ClientHttpResponse response = execution.execute(request, originBody.getBytes());

        String respBody = IOUtils.toString(response.getBody());
        respBody += "blablabla";
        final String finalRespBody = respBody;
        return new ClientHttpResponse() {
            public HttpStatus getStatusCode() throws IOException {
                return response.getStatusCode();
            }

            public int getRawStatusCode() throws IOException {
                return response.getRawStatusCode();
            }

            public String getStatusText() throws IOException {
                return response.getStatusText();
            }

            public void close() {
                response.close();
            }

            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(finalRespBody.getBytes());
            }

            public HttpHeaders getHeaders() {
                return response.getHeaders();
            }
        };
    }
}
