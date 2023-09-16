package org.example.demo.provider;

import io.grpc.examples.helloworld.DubboGreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author xmh
 * @version V1.0
 * @description: 服务提供者
 * @date: 2023/9/16 14:07
 **/
@DubboService
public class GrpcGreeterImpl extends DubboGreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
