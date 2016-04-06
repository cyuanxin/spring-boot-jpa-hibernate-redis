package com.jinfuzi.opstd.controller;

import com.jinfuzi.opstd.Service.ActivationService;
import com.jinfuzi.opstd.model.ActivateRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhangyuanxin on 2016/4/1.
 */
@RestController
@RequestMapping("activation")
public class ActivationController {
    @Autowired
    private ActivationService activationService;

    @RequestMapping(value = "getTodayCount", method = RequestMethod.GET)
    public Long getTodayCount() {
        return activationService.getTodayCount();
    }

    @RequestMapping(value = "getTotalCount", method = RequestMethod.GET)
    public Long getTotalCount() {
        return activationService.getTotalCount();
    }

    @RequestMapping(value = "/{num}", method = RequestMethod.GET)
    public List<ActivateRecord> getRecentActivation(@PathVariable Integer num) {
        return activationService.getRecentActivation(num);
    }
}
