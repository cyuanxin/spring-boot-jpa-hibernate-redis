package com.jinfuzi.opstd.Service.RedisSubChannel;

import com.google.common.base.Strings;
import com.jinfuzi.opstd.Service.ActivationService;
import com.jinfuzi.opstd.exception.RedisSubException;
import com.jinfuzi.opstd.model.ActivateRecord;
import com.jinfuzi.opstd.model.base.BaseModel;
import com.jinfuzi.opstd.util.IpUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by zhangyuanxin on 2016/3/31.
 */
public class HandleActivationChannel implements IHandleChannelMsg<HandleActivationChannel.Msg, ActivateRecord> {
    private final static Logger log = LoggerFactory.getLogger(HandleActivationChannel.class);
    @Autowired
    private ActivationService activationService;
    @Override
    public void handleMsg(String rawMsg) {
        JSONObject json = new JSONObject(rawMsg);
        Msg msg = new Msg();
        msg.setFrom((String) json.get("from"));
        msg.setUid((String) json.get("uid"));
        msg.setActivateDate((String) json.get("ip"));
        msg.setActivateDate((String) json.get("activate_date"));
        msg.setDevice((String) json.get("device"));
        msg.setNetwork((String) json.get("network"));
        msg.setSource((String) json.get("source"));
        ActivateRecord activateRecord = null;
        try {
            activateRecord = validateAndConvertMsg(msg);
        } catch (RedisSubException e) {
            log.error("validateAndConvertMsg msg:" + msg + e);
        }
        activationService.saveRecordAndUpdateStas(activateRecord);
    }


    @Override
    public ActivateRecord validateAndConvertMsg(Msg msg) throws RedisSubException {
        ActivateRecord activateRecord = new ActivateRecord();
        if (Strings.isNullOrEmpty(msg.getUid())) {
            throw new RedisSubException("uid is null msg:" + msg);
        }
        if (Strings.isNullOrEmpty(msg.getActivateDate())) {
            throw new RedisSubException("activateDate is null msg:" + msg);
        }
        Date activateDate;
        try {
            activateDate = DateUtils.parseDate(msg.getActivateDate(), "yyyyMMddhhmmss");
        } catch (ParseException e) {
            throw new RedisSubException("error occurred parse activateDate");
        }

        activateRecord.setDevice(msg.getDevice());
        activateRecord.setActivateDate(activateDate);
        activateRecord.setSource(msg.getSource());
        activateRecord.setUid(Long.valueOf(msg.getUid()));
        activateRecord.setRequestNetwork(msg.getNetwork());
        activateRecord.setAddress(IpUtil.getAddressByIp(msg.getIp()));
        activateRecord.setIp(msg.getIp());
        activateRecord.setCreateDate(new Date());
        return activateRecord;
    }


    class Msg extends BaseModel {
        String from;
        String network;
        String device;
        String activateDate;
        String ip;
        String uid;
        String source;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getNetwork() {
            return network;
        }

        public void setNetwork(String network) {
            this.network = network;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getActivateDate() {
            return activateDate;
        }

        public void setActivateDate(String activateDate) {
            this.activateDate = activateDate;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

}
