package com.youlai.boot.utils.http;

import lombok.Getter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author gyy
 * @since 2025/3/15 21:26
 */
public class HttpUtils {
    private static final int CONNECT_TIMEOUT = 30_000;
    private static final int SOCKET_TIMEOUT = 30_000;
    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 ApacheHttpClient";

    private static CloseableHttpClient createHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        return HttpClientBuilder.create()
                .setUserAgent(DEFAULT_USER_AGENT)
                .setDefaultRequestConfig(config)
                .build();
    }

    /**
     * 执行HTTP请求
     */
    public static String executeRequest(HttpRequestBase request) {
        try (CloseableHttpClient httpClient = createHttpClient()) {
            HttpResponse response = httpClient.execute(request);
            return handleResponse(response);
        } catch (IOException e) {
            throw new HttpException("HTTP request failed", e);
        }
    }

    /**
     * GET请求
     */
    public static String get(String url, Map<String, String> params) {
        String actualUrl = buildUrlWithParams(url, params);
        HttpGet httpGet = new HttpGet(actualUrl);
        return executeRequest(httpGet);
    }

    /**
     * POST表单请求
     */
    public static String postForm(String url, Map<String, String> formData) {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<>();
        if (formData != null) {
            formData.forEach((k, v) -> params.add(new BasicNameValuePair(k, v)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
        return executeRequest(httpPost);
    }

    /**
     * POST JSON请求
     */
    public static String postJson(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return executeRequest(httpPost);
    }

    /**
     * 文件上传 (multipart/form-data)
     */
    public static String uploadFile(String url, Map<String, String> formData, String fileFieldName, File file) {
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        // 添加文件
        if (file != null && file.exists()) {
            builder.addPart(fileFieldName, new FileBody(file));
        }

        // 添加表单字段
        if (formData != null) {
            formData.forEach((k, v) ->
                    builder.addPart(k, new StringBody(v, ContentType.TEXT_PLAIN)));
        }

        httpPost.setEntity(builder.build());
        return executeRequest(httpPost);
    }

    /**
     * 处理响应
     */
    private static String handleResponse(HttpResponse response) {
        try {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new HttpException("Error reading response", e);
        }
    }

    /**
     * 构建带参数的URL
     */
    private static String buildUrlWithParams(String url, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        StringBuilder query = new StringBuilder();
        params.forEach((k, v) -> {
            if (!query.isEmpty()) {
                query.append('&');
            }
            query.append(URLEncoder.encode(k, StandardCharsets.UTF_8))
                    .append('=')
                    .append(URLEncoder.encode(v, StandardCharsets.UTF_8));
        });

        return url.contains("?") ? url + "&" + query : url + "?" + query;
    }

    /**
     * 自定义HTTP异常
     */
    @Getter
    public static class HttpException extends RuntimeException {
        private final int statusCode;

        public HttpException(String message) {
            this(message, 0);
        }

        public HttpException(String message, int statusCode) {
            super(message);
            this.statusCode = statusCode;
        }

        public HttpException(String message, Throwable cause) {
            super(message, cause);
            this.statusCode = 0;
        }

    }
}
