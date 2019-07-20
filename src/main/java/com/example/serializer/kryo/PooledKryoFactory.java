package com.example.serializer.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoPool;

/**
 * Kryo 实例的创建/初始化是相当昂贵的，所以在多线程的情况下，您应该线程池化 Kryo 实例。
 * 简单的解决方案是使用 ThreadLocal 将 Kryo实例绑定到 Threads。
 */
public class PooledKryoFactory extends AbstractKryoFactory {

    private KryoPool pool;

    public PooledKryoFactory() {
        // Build pool with SoftReferences enabled (optional)
        this.pool = new KryoPool.Builder(this).softReferences().build();
    }

    @Override
    public Kryo getKryo() {
        return pool.borrow();
    }

    /**
     * 将kryo退还给pool
     * @param kryo
     */
    @Override
    public void returnKryo(Kryo kryo) {
        pool.release(kryo);
    }
}
