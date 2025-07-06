package com.zsy.utils;

import com.zsy.Enum.OrderEvent;
import com.zsy.Enum.OrderState;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class OrderStateMachine {
    // 定义状态转换规则
    private final Map<OrderState, Map<OrderEvent, OrderState>> stateTransitions =
            new EnumMap<>(OrderState.class);

    public OrderStateMachine() {
        initializeTransitions();
    }

    private void initializeTransitions() {
        // 从已创建状态可以转换到已支付或已取消
        Map<OrderEvent, OrderState> createdTransitions = new HashMap<>();
        createdTransitions.put(OrderEvent.PAY, OrderState.PAID);
        createdTransitions.put(OrderEvent.CANCEL, OrderState.CANCELLED);
        stateTransitions.put(OrderState.CREATED, createdTransitions);

        // 从已支付状态可以转换到已发货或已取消
        Map<OrderEvent, OrderState> paidTransitions = new HashMap<>();
        paidTransitions.put(OrderEvent.SHIP, OrderState.SHIPPED);
        paidTransitions.put(OrderEvent.CANCEL, OrderState.CANCELLED);
        stateTransitions.put(OrderState.PAID, paidTransitions);

        // 从已发货状态可以转换到已送达或已退货
        Map<OrderEvent, OrderState> shippedTransitions = new HashMap<>();
        shippedTransitions.put(OrderEvent.DELIVER, OrderState.DELIVERED);
        shippedTransitions.put(OrderEvent.RETURN, OrderState.RETURNED);
        stateTransitions.put(OrderState.SHIPPED, shippedTransitions);

        //已退货, 已取消和已退货是终态，没有转换
    }

    /**
     * 尝试执行状态转换
     * @param currentState 当前状态
     * @param event 触发事件
     * @return 新状态，如果转换无效则返回当前状态
     */
    public OrderState transition(OrderState currentState, OrderEvent event) {
        Map<OrderEvent, OrderState> transitions = stateTransitions.get(currentState);
        if (transitions == null || !transitions.containsKey(event)) {
            System.out.println("非法转换 " + currentState + " -> " + event);
            return currentState; // 保持当前状态
        }

        OrderState newState = transitions.get(event);
        System.out.println("状态变更 " + currentState + " -> " + newState + " 通过 " + event);
        return newState;
    }
}