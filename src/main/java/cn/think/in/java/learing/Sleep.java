package cn.think.in.java.learing;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
public class Sleep {

    private int hello;

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

        ExecutorService es = new ThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>());

        for (int i = 0; i < 4; i++) {
            System.out.println(i + " i ");
            Thread.sleep(100);
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }


}
