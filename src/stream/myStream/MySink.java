package stream.myStream;

public interface MySink<T> {
    void begin();
    void accept(T t);
    void end();
}
