package com.zsy.utils;

import com.zsy.Enum.OrderEvent1;
import com.zsy.Enum.OrderState1;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class OrderStateMachine1 {
    private final Map<OrderState1, Map<OrderEvent1, OrderState1>> stateTransitions = new EnumMap<>(OrderState1.class);

    public OrderStateMachine1() {
        initializeTransitions();
    }

    private void initializeTransitions() {
        // 使用带描述的枚举
        addTransition(OrderState1.CREATED, OrderEvent1.PAY, OrderState1.PAID);
        addTransition(OrderState1.CREATED, OrderEvent1.CANCEL, OrderState1.CANCELLED);
        addTransition(OrderState1.PAID, OrderEvent1.SHIP, OrderState1.SHIPPED);
        addTransition(OrderState1.PAID, OrderEvent1.CANCEL, OrderState1.CANCELLED);
        addTransition(OrderState1.SHIPPED, OrderEvent1.DELIVER, OrderState1.DELIVERED);
        addTransition(OrderState1.SHIPPED, OrderEvent1.RETURN, OrderState1.RETURNED);
    }

    private void addTransition(OrderState1 source, OrderEvent1 event, OrderState1 target) {
        stateTransitions.computeIfAbsent(source, k -> new HashMap<>())
                .put(event, target);
    }

    public OrderState1 transition(OrderState1 currentState, OrderEvent1 event) {
        Map<OrderEvent1, OrderState1> transitions = stateTransitions.get(currentState);
        if (transitions == null || !transitions.containsKey(event)) {
            System.out.println("无效转换: " + currentState.getDescription() +
                    " 不能通过 " + event.getDescription() + " 转换");
            return currentState;
        }

        OrderState1 newState = transitions.get(event);
        System.out.println("状态变更: " + currentState.getDescription() +
                " -> " + newState.getDescription() +
                " (通过: " + event.getDescription() + ")");
        return newState;
    }
}
