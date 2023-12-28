package com.food.ordering.system.domain.event;

public final class EmptyEvent implements DomainEvent<Void> {
    public final static EmptyEvent INSTANCE = new EmptyEvent();

    private EmptyEvent() {
    }

    @Override
    public void fire() {

    }
}
