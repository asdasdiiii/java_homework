package com.homework.homework.week_03.io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;

import java.net.InetSocketAddress;

public class HeaderHttpRequestFilter implements HttpRequestFilter , IpFilterRule {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("mao", "soul");

    }

    @Override
    public boolean matches(InetSocketAddress remoteAddress) {

        String ip = remoteAddress.getHostString();
        if(ip.contains("127.0.0.1")||ip.contains("localhost")){
            if(8088==(remoteAddress.getPort())){
                return true;
            }
            return false;
        }else{
            return false;
        }


        //ip区间过滤
//        long ipLong = ipToNumber(ip);
//
//        IpRange ipRange = new IpRange("192.168.1.1", "192.168.1.100");
//
//        long ipStart = ipToNumber(ipRange.getIpStart());
//        long ipEnd = ipToNumber(ipRange.getIpEnd());
//        if (ipLong >= ipStart && ipLong <= ipEnd) {
//            return true;
//        }
//        return false;

    }

    @Override
    public IpFilterRuleType ruleType() {
        return IpFilterRuleType.REJECT;
    }
    public Long ipToNumber(String ip) {
        Long ipLong = 0L;
        String[] ipNumbers = ip.split("\\.");
        for (String ipNumber : ipNumbers) {
            ipLong = ipLong << 8 | Integer.parseInt(ipNumber);
        }
        return ipLong;
    }
}
