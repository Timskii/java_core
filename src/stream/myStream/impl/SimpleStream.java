package stream.myStream.impl;

import stream.myStream.MyFilter;
import stream.myStream.MyMapper;
import stream.myStream.MySink;

import java.util.ArrayList;
import java.util.List;

// Главный класс, представляющий стрим
public abstract class SimpleStream<T> {
    // Ссылка на предыдущую стадию в конвейере
    final SimpleStream<?> previousStage;

    protected SimpleStream(SimpleStream<?> previousStage) {
        this.previousStage = previousStage;
    }

    public <R> SimpleStream<R> map(MyMapper<T, R> mapper) {
        return new MapStage<>(this, mapper);
    }

    public SimpleStream<T> filter(MyFilter<T> filter) {
        return new FilterStage<>(this, filter);
    }

    public List<T> collect() {
        List<T> result = new ArrayList<>();
        MySink<T> terminalSink = new TerminalSink<>(result);

        // Построение цепочки Sink-ов от конца к началу
        MySink<?> sinkChain = wrapSink(terminalSink);

        // Запуск итерации. Только здесь мы итерируемся по источнику
        @SuppressWarnings("unchecked")
        SourceStage<T> sourceStage = (SourceStage<T>) getSourceStage();

        sinkChain.begin();
        for (T element : sourceStage.source) {
            ((MySink<T>) sinkChain).accept(element);
        }
        sinkChain.end();

        return result;
    }

    // Этот метод будет находить самый первый узел (источник)
    private SimpleStream<?> getSourceStage() {
        SimpleStream<?> current = this;
        while (current.previousStage != null) {
            current = current.previousStage;
        }
        return current;
    }

    // Абстрактный метод для оборачивания Sink-ов
    protected abstract MySink<?> wrapSink(MySink<?> terminalSink);
}






