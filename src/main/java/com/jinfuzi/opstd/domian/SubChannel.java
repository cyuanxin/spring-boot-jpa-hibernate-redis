package com.jinfuzi.opstd.domian;

/**
 * Created by zhangyuanxin on 2016/3/31.
 */
public enum SubChannel {
    ACVIVATATION("bsys-opstd-activation"),
    TRADE("bsys-opstd-activation");
    private final String channelName;
    private SubChannel(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }
}
