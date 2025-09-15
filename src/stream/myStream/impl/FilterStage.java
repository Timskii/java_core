package stream.myStream.impl;

import stream.myStream.MyFilter;
import stream.myStream.MySink;
import stream.myStream.MyStream;


// Класс для операции filter
public class FilterStage<T> extends SimpleStream<T> {
    private final MyFilter<T> filter;

    public FilterStage(SimpleStream<T> previousStage, MyFilter<T> filter) {
        super(previousStage);
        this.filter = filter;
    }

    @Override
    protected MySink<?> wrapSink(MySink<?> terminalSink) {
        // Создаем наш MySink, оборачивая terminalSink
        MySink<T> nextSink = new FilterSink<>(filter, (MySink<T>) terminalSink);
        // Передаем его предыдущей стадии, которая обернёт его дальше
        return previousStage.wrapSink(nextSink);
    }
}
