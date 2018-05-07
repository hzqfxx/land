package com.test.land.landparent.admin.common;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletRequest;

public class HttpHelper {

    /**
     * 获取请求Body
     * @param request
     * @return
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedInputStream bufferedInput = null;
        try {
            inputStream = request.getInputStream();
            bufferedInput = new BufferedInputStream(inputStream);

            byte[] buffer = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                String chunk = new String(buffer, 0, bytesRead, request.getCharacterEncoding());
                sb.append(chunk);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedInput != null) {
                try {
                    bufferedInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
