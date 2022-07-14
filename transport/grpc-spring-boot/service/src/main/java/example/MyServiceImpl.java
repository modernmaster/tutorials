package example;

import domain.*;
import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sayHelloToStock(Stock stock, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + stock.getSymbol())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    public void getStock(domain.StockRequest request, StreamObserver<domain.Stock> responseObserver) {
        Stock stock = Stock.newBuilder().setPrice(123d).setSymbol("LON:JIM2").build();
        responseObserver.onNext(stock);
        responseObserver.onCompleted();
    }
}