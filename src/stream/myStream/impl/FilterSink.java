package stream.myStream.impl;

import stream.myStream.MyFilter;
import stream.myStream.MySink;


// Реализация "приёмника" для фильтра
public class FilterSink<T> implements MySink<T> {
    private final MyFilter<T> filter;
    private final MySink<T> nextSink;

    public FilterSink(MyFilter<T> filter, MySink<T> nextSink) {
        this.filter = filter;
        this.nextSink = nextSink;
    }

    @Override
    public void begin() { nextSink.begin(); }
    @Override
    public void accept(T t) {
        if (filter.test(t)) {
            nextSink.accept(t);
        }
    }
    @Override
    public void end() { nextSink.end(); }
}
