package stream.myStream.impl;

import stream.myStream.MyMapper;
import stream.myStream.MySink;

 // Реализация "приёмника" для маппера
 public class MapSink<T, R> implements MySink<T> {
    private final MyMapper<T, R> mapper;
    private final MySink<R> nextSink;

    public MapSink(MyMapper<T, R> mapper, MySink<R> nextSink) {
        this.mapper = mapper;
        this.nextSink = nextSink;
    }

    @Override
    public void begin() { nextSink.begin(); }
    @Override
    public void accept(T t) {
        R mappedElement = mapper.apply(t);
        nextSink.accept(mappedElement);
    }
    @Override
    public void end() { nextSink.end(); }
}
