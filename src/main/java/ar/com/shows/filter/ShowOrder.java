package ar.com.shows.filter;

import java.util.List;

public class ShowOrder {

    OrderType orderType;
    String orderBy;

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isAsc() {
        return this.orderType == OrderType.asc;
    }

    public boolean isDesc() {
        return this.orderType == OrderType.desc;
    }

    public String[] getOrderEntity() {
        return this.orderBy.split("\\.");
    }

    enum OrderType { asc, desc}

}

