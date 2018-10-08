package ru.nntu.vst.gorbatovskii.factory.events;

import java.util.Comparator;
import java.util.LinkedList;

public class EventBusImpl implements EventBus {

    private LinkedList<EventWrapper> eventQueue;
    private double currentTime;

    public EventBusImpl() {
        eventQueue = new LinkedList<>();
        currentTime = 0;
    }

    @Override
    public void registerEvent(Event event) {
        eventQueue.add(new EventWrapper(event, event.getDuration() + currentTime));
        eventQueue.sort(new Comparator<EventWrapper>() {
            @Override
            public int compare(EventWrapper event1, EventWrapper event2) {
                return Double.compare(event1.getEventTime(), event2.getEventTime());
            }
        });
    }

    @Override
    public boolean hasNext() {
        return !eventQueue.isEmpty();
    }

    @Override
    public void executeNext() {
        EventWrapper wrapper = eventQueue.pop();
        currentTime = wrapper.getEventTime();
        System.out.println(wrapper);
        wrapper.getEvent().handle();
    }

    private class EventWrapper {
        private Event event;
        private double eventTime;

        private EventWrapper(Event event, double eventTime) {
            this.event = event;
            this.eventTime = eventTime;
        }

        private Event getEvent() {
            return event;
        }

        private double getEventTime() {
            return eventTime;
        }

        @Override
        public String toString() {
            return "EventWrapper{[" + String.format("%.2f", eventTime) + "], " + event + "}";
        }
    }
}
