package com.zsy.Enum;

public enum OrderEvent1 {
    // 事件编码, 事件描述
    PAY(1, "已支付"),
    SHIP(2, "已发货"),
    DELIVER(3, "已送达"),
    CANCEL(4, "已取消"),
    RETURN(5, "已退货");

    private final int code;
    private final String description;

    // 构造函数
    OrderEvent1(int code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getter方法
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 通过code获取枚举实例
    public static OrderEvent1 getByCode(int code) {
        for (OrderEvent1 event : values()) {
            if (event.code == code) {
                return event;
            }
        }
        throw new IllegalArgumentException("无效的事件编码: " + code);
    }

    // 通过description获取枚举实例
    public static OrderEvent1 getByDescription(String description) {
        for (OrderEvent1 event : values()) {
            if (event.description.equals(description)) {
                return event;
            }
        }
        throw new IllegalArgumentException("无效的事件描述: " + description);
    }
}
