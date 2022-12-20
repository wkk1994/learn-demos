package com.wkk.lean.demo.object.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wangkunkun
 * @date 2022/12/17 22:27
 */
public class ClientObjectFactory extends BasePooledObjectFactory<Client> {

    private static AtomicInteger number = new AtomicInteger(0);
    @Override
    public Client create() throws Exception {
        return new Client(number.incrementAndGet());
    }

    @Override
    public PooledObject<Client> wrap(Client client) {
        return new DefaultPooledObject<>(client);
    }
}
