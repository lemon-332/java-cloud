package com.hjj.pay.mq;

import com.alibaba.fastjson.JSON;
import com.hjj.pay.model.PayLog;
import com.hjj.pay.service.PayLogService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RocketMQTransactionListener(txProducerGroup = "rocket")
public class TransactionalListener implements RocketMQLocalTransactionListener {

    private final PayLogService payLogService;

    public TransactionalListener(PayLogService payLogService) {
        this.payLogService = payLogService;
    }

    /**
     * 当向RocketMQ的Broker发送half消息成功之后，调用该方法
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        try {
            // 本地事务控制
            String result = new String((byte[]) message.getPayload());
            PayLog payLog = JSON.parseObject(result, PayLog.class);
            payLogService.add(payLog);
            // 本地事务控制
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }

    /**
     * 超时回查
     *
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}
