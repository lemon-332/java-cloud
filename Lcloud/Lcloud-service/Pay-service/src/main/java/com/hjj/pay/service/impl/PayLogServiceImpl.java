package com.hjj.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjj.pay.mapper.PayLogMapper;
import com.hjj.pay.model.PayLog;
import com.hjj.pay.service.PayLogService;
import org.springframework.stereotype.Service;

@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {
    @Override
    public void add(PayLog payLog) {
        baseMapper.insert(payLog);
    }
}
