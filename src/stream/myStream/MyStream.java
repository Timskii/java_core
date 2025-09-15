package stream.myStream;

import java.util.List;

public interface MyStream<T> {
    <R> MyStream<R> map(MyMapper<T, R> mapper);
    MyStream<T> filter (MyFilter<T> filter);
    List<T> collect();

}
