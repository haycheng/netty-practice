package net.melody.nettypractice;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

/**
 * @author chenglx
 * @version V1.0
 * @date 2021/1/25 15:32
 * <p>
 * Date Author Description
 * ------------------------------------------------------
 * 2021/1/25 chenglx init
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    public void channelRead0(ChannelHandlerContext context, FullHttpRequest request) {
        String content = String.format("Receive http request, uri: %s, method: %s, content: %s%n", request.uri(), request.method(), request.content());
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes()));
        context.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
