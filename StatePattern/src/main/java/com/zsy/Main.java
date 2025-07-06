package com.zsy;


import com.zsy.Enum.OrderEvent;
import com.zsy.Enum.OrderEvent1;
import com.zsy.Enum.OrderState;
import com.zsy.Enum.OrderState1;
import com.zsy.utils.OrderStateMachine;
import com.zsy.utils.OrderStateMachine1;

public class Main {
    public static void main(String[] args) {
        //extracted();
        extracted1();
    }

    private static void extracted1() {
        OrderStateMachine1 stateMachine = new OrderStateMachine1();

        // 初始状态为CREATED
        OrderState1 currentState = OrderState1.CREATED;
        System.out.println("初始状态: " + currentState.getDescription());

        // 支付操作
        currentState = stateMachine.transition(currentState, OrderEvent1.PAY);
        System.out.println("当前状态: " + currentState.getDescription());

        // 发货操作
        currentState = stateMachine.transition(currentState, OrderEvent1.SHIP);
        System.out.println("当前状态: " + currentState.getDescription());

        // 尝试取消（此时不允许）
        currentState = stateMachine.transition(currentState, OrderEvent1.CANCEL);
        System.out.println("当前状态: " + currentState.getDescription());

        // 送达操作
        currentState = stateMachine.transition(currentState, OrderEvent1.DELIVER);
        System.out.println("当前状态: " + currentState.getDescription());
    }

    private static void extracted() {
        OrderStateMachine stateMachine = new OrderStateMachine();

        // 初始状态为CREATED
        OrderState currentState = OrderState.CREATED;
        System.out.println("初始化状态为 " + currentState);

        // 支付操作
        currentState = stateMachine.transition(currentState, OrderEvent.PAY);
        System.out.println("当前状态 " + currentState);

        // 发货操作
        currentState = stateMachine.transition(currentState, OrderEvent.SHIP);
        System.out.println("当前状态 " + currentState);

        // 尝试取消（此时不允许）
        currentState = stateMachine.transition(currentState, OrderEvent.CANCEL);
        System.out.println("当前状态: " + currentState);

        // 送达操作
        currentState = stateMachine.transition(currentState, OrderEvent.DELIVER);
        System.out.println("当前状态: " + currentState);

        // 尝试退货（此时不允许）
        currentState = stateMachine.transition(currentState, OrderEvent.RETURN);
        System.out.println("最终状态: " + currentState);
    }
}