package stream.myStream.impl;

import stream.myStream.MyMapper;
import stream.myStream.MySink;



// Класс для операции map
public class MapStage<T, R> extends SimpleStream<R> {
    private final SimpleStream<T> previousStage;
    private final MyMapper<T, R> mapper;

    public MapStage(SimpleStream<T> previousStage, MyMapper<T, R> mapper) {
        // Проблема решена! Мы передаём предыдущую стадию, а не её источник
        super(previousStage);
        this.previousStage = previousStage;
        this.mapper = mapper;
    }

    @Override
    protected MySink<?> wrapSink(MySink<?> terminalSink) {
        // Создаем наш MySink, который будет принимать тип T и передавать R
        MySink<T> nextSink = new MapSink<>(mapper, (MySink<R>) terminalSink);
        // Передаем его предыдущей стадии
        return previousStage.wrapSink(nextSink);
    }
}
