package org.example.demo.consumer;

import io.grpc.examples.helloworld.DubboGreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import java.util.Date;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Task implements CommandLineRunner {
    @DubboReference
    private DubboGreeterGrpc.IGreeter iGreeter;

    @Override
    public void run(String... args) throws Exception {
        HelloReply reply = iGreeter.sayHello(HelloRequest.newBuilder().setName("world!").build());
        System.out.println("Receive result ======> " + reply.getMessage());

        new Thread(()-> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(new Date() + " Receive result ======> " + iGreeter.sayHello(HelloRequest.newBuilder().setName("world!").build()).getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
