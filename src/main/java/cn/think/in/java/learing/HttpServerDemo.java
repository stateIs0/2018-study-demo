package cn.think.in.java.learing;

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
        server.createContext("/test", new TestHandler());
        server.start();
    }

    static class TestHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) {
            threadPoolExecutor.execute((() -> {
                try {
                    System.out.println(Thread.currentThread().getId());
                    String response = "hello world";
                    response = getRes();

                    //获得查询字符串(get)
                    String queryString = exchange.getRequestURI().getQuery();
                    Map<String, String> queryStringInfo = formData2Dic(queryString);
                    System.out.println(queryStringInfo);
                    //获得表单提交数据(post)
                    String postString = IOUtils.toString(exchange.getRequestBody());
                    Map<String, String> postInfo = formData2Dic(postString);

                    exchange.sendResponseHeaders(200, 0);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        }
    }

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
            FileInputStream i = new FileInputStream(new File("index.html"));
            BufferedReader br   = new BufferedReader(new InputStreamReader(i));
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
