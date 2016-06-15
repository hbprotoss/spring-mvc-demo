package io.hbprotoss.web.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * Created by hbprotoss on 11/13/15.
 */
public class JsonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletResponse newResponse = response;

        if (request instanceof HttpServletRequest) {
            newResponse = new JsonResponseWrapper((HttpServletResponse) response);
        }

        chain.doFilter(request, newResponse);

        if (((JsonResponseWrapper)newResponse).getStatus() != 200) {
            response.getWriter().write(newResponse.toString());
            return;
        }

        if (newResponse instanceof JsonResponseWrapper) {
            String text = newResponse.toString();
            if (text != null) {
//                text = text.toUpperCase();
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setStatus(200);
                apiResponse.setMessage("ok");
                apiResponse.setData(text);
                response.getWriter().write(JSON.toJSONString(apiResponse, SerializerFeature.BrowserCompatible));
            }
        }
    }

    @Override
    public void destroy() {

    }
}

class ApiResponse {
    private int status;
    private String message;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

class JsonResponseWrapper extends HttpServletResponseWrapper {
    private int status;
    private ByteArrayOutputStream output;
    private FilterServletOutputStream filterOutput;
    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response
     * @throws IllegalArgumentException if the response is null
     */
    public JsonResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (filterOutput == null) {
            filterOutput = new FilterServletOutputStream(output);
        }
        return filterOutput;
    }

    public String toString() {
        return new String(output.toByteArray());
    }
}

class FilterServletOutputStream extends ServletOutputStream {

    DataOutputStream output;
    public FilterServletOutputStream(OutputStream output) {
        this.output = new DataOutputStream(output);
    }

    @Override
    public void write(int arg0) throws IOException {
        output.write(arg0);
    }

    @Override
    public void write(byte[] arg0, int arg1, int arg2) throws IOException {
        output.write(arg0, arg1, arg2);
    }

    @Override
    public void write(byte[] arg0) throws IOException {
        output.write(arg0);
    }
}