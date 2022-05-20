package io.nuvalence.au.grpcserver;

import io.grpc.Grpc;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

class LoggingInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {

        System.out.println("*****************");
        System.out.println(call.getAttributes().get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR));
        System.out.println(call.getAttributes().get(Grpc.TRANSPORT_ATTR_SSL_SESSION));
        System.out.println("*****************");

        return next.startCall(call, headers);
    }

}