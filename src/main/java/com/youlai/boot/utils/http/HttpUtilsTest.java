package com.youlai.boot.utils.http;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gyy
 * @since 2025/3/15 21:29
 */
public class HttpUtilsTest {

    @Test
    public void testSendRequest() throws Exception {
        String urlString = "https://ai.inspirvision.cn/s/api/getAccessToken";
        String method = "GET";
        String content = null;

        Map<String, String> requestPropertyMap = new HashMap<>();
        requestPropertyMap.put("accessKey", "APPID_Z1b55c35f68Ps3a2");
        requestPropertyMap.put("accessSecret", "123-1f1ecd30e7f3e5d0f3db5a3d1f9e8110");

        String result = HttpUtils.get(urlString, requestPropertyMap);
        System.out.println(result);
    }
}
