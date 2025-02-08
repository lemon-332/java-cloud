package com.hjj.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjj.pay.model.PayLog;

public interface PayLogService extends IService<PayLog> {
    void add(PayLog payLog);
}
