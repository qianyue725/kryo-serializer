package com.example.serializer.kryo;

import com.esotericsoftware.kryo.Kryo;

/**
 * 由于kryo对象是线程不安全的，当有多个netty的channel同时连接时，各channel是可能工作在不同的线程上的
 * （netty中一个IO线程可以对应多个channel，而一个channel只能对应一个线程，详细可以参考netty线程模型）
 * ，若共用同一个kryo对象会出现并发问题，因此用ThreadLocal在每个线程保留一个各自的kryo对象，
 * 保证不会大量创建kryo对象的同时避免了并发问题
 */
public class ThreadLocalKryoFactory extends AbstractKryoFactory {

    private final ThreadLocal<Kryo> holder = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            return create();
        }
    };


    @Override
    public void returnKryo(Kryo kryo) {
        // do nothing
    }

    @Override
    public Kryo getKryo() {
        return holder.get();
    }
}
