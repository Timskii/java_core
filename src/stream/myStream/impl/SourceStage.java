package stream.myStream.impl;

import stream.myStream.MySink;

import java.util.List;

public class  SourceStage<T> extends SimpleStream<T> {
    final List<T> source;

    public SourceStage(List<T> source) {
        super(null); // Это первая стадия, у неё нет предыдущей
        this.source = source;
    }

    @Override
    protected MySink<?> wrapSink(MySink<?> terminalSink) {
        // Здесь мы просто возвращаем конечный MySink, т.к. мы в начале цепочки
        return terminalSink;
    }
}
