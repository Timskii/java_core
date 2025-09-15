package stream.myStream.impl;

import stream.myStream.MySink;

import java.util.List;

// Терминальный приёмник, который добавляет элементы в результат
public class TerminalSink<T> implements MySink<T> {
    private final List<T> result;

    public TerminalSink(List<T> result) {
        this.result = result;
    }

    @Override
    public void begin() {}
    @Override
    public void accept(T t) { result.add(t); }
    @Override
    public void end() {}
}
