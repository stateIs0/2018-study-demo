package cn.think.in.java.learing;

import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
/**
 *
 *
 * @author 玄灭
 * @date 2018/10/1-上午9:13
 */
public class Sleep {

    public static void sleep(int s) {
        try {
            Thread.sleep(1000 * s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create("http://foo.com/"))
//            .version(HttpClient.Version.HTTP_2)
//            .build();
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//            .thenApply(HttpResponse::body)
//            .thenAccept(System.out::println)
//            .join();


    }


}
