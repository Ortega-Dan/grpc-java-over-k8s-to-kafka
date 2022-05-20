package io.nuvalence.au.grpcserver;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.ForwardingServerCall.SimpleForwardingServerCall;
import io.grpc.ForwardingServerCallListener.SimpleForwardingServerCallListener;

class LoggingInterceptor implements ServerInterceptor {
    
    @Override
    public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
            final Metadata headers, ServerCallHandler<ReqT, RespT> next) {

        SimpleForwardingServerCall<ReqT, RespT> responseInterceptingServerCall = new SimpleForwardingServerCall<ReqT, RespT>(
                call) {
            @Override
            public void sendMessage(RespT message) {
                super.sendMessage(message);
                System.out.println("resp message: " + message);
            }
        };

        Listener<ReqT> nextListener = next.startCall(responseInterceptingServerCall, headers);

        SimpleForwardingServerCallListener<ReqT> reqInterceptor = new SimpleForwardingServerCallListener<ReqT>(
                nextListener) {
            @Override
            public void onMessage(ReqT message) {
                super.onMessage(message);
                System.out.println("req message: " + message);
            }
        };
        return reqInterceptor;
    }

}