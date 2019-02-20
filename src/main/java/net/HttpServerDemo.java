package net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.apache.commons.io.IOUtils;



/**
 *
 * @author 玄灭
 * @date 2018/11/14-下午2:31
 */
public class HttpServerDemo {

    static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

    public static void main(String[] arg) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
        server.createContext("/", new TestHandler());
        server.start();
    }

    static class TestHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) {
            threadPoolExecutor.execute((() -> {
                try {
                    System.out.println(Thread.currentThread().getId());
                    byte[] response = null;

                    //获得查询字符串(get)
                    String queryString = exchange.getRequestURI().getQuery();
                    String path = exchange.getRequestURI().getPath();
                    Map<String, String> queryStringInfo = formData2Dic(queryString);
                    if (path.equals("/")) {
                        response = getRes().getBytes();

                    } else if (path.contains("css") || path.contains("js")) {
                        response = getRes(path).getBytes();

                    } else if (path.contains("favicon.ico")) {
                        exchange.getResponseHeaders().put("Content-Type", Arrays.asList(new String[]{"application/json;charset=UTF-8"}));
                        response = JSON.toJSONBytes(getRes(path));
                    } else {
                        JSONObject j = new JSONObject();
                        j.put("userPermission", "hello-8081");

                        response = j.toJSONString().getBytes();
                    }
                    System.out.println(queryStringInfo);
                    //获得表单提交数据(post)
                    String postString = IOUtils.toString(exchange.getRequestBody());
                    Map<String, String> postInfo = formData2Dic(postString);

                    exchange.sendResponseHeaders(200, 0);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response);
                    os.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        }
    }
    ///Users/cxs/code/2018-study-demo/src/main/resource/static/static/js/manifest.041be5373e6b0742419d.js

    public static Map<String, String> formData2Dic(String formData) {
        System.out.println(formData);
        Map<String, String> result = new HashMap<>();
        if (formData == null || formData.trim().length() == 0) {
            return result;
        }
        final String[] items = formData.split("&");
        Arrays.stream(items).forEach(item -> {
            final String[] keyAndVal = item.split("=");
            if (keyAndVal.length == 2) {
                try {
                    final String key = URLDecoder.decode(keyAndVal[0], "utf8");
                    final String val = URLDecoder.decode(keyAndVal[1], "utf8");
                    result.put(key, val);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        return result;
    }

    static String getRes() {

        try {
            FileInputStream i = new FileInputStream(new File("/Users/cxs/code/2018-study-demo/src/main/resource/static/static/index.html"));
            BufferedReader br = new BufferedReader(new InputStreamReader(i));
            StringBuilder r = new StringBuilder();
            String l = null;
            while ((l = br.readLine()) != null) {
                r.append(l);
            }
            return r.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    static String getRes(String path) {

        try {
            FileInputStream i = new FileInputStream(new File("/Users/cxs/code/2018-study-demo/src/main/resource/static/static" + path));
            BufferedReader br = new BufferedReader(new InputStreamReader(i));
            StringBuilder r = new StringBuilder();
            String l = null;
            while ((l = br.readLine()) != null) {
                r.append(l);
            }
            return r.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
