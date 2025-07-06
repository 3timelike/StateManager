package com.zsy.Enum;

public enum OrderState1 {
    CREATED(0, "已创建"),
    PAID(1, "已支付"),
    SHIPPED(2, "已发货"),
    DELIVERED(3, "已送达"),
    CANCELLED(4, "已取消"),
    RETURNED(5, "已退货");

    private final int code;
    private final String description;

    OrderState1(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OrderState1 getByCode(int code) {
        for (OrderState1 state : values()) {
            if (state.code == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("无效的状态编码: " + code);
    }
}