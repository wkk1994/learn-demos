package com.wkk.lean.demo.object.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author Wangkunkun
 * @date 2022/12/17 22:39
 */
public class ClientObjectPool extends GenericObjectPool<Client> {


    public ClientObjectPool(PooledObjectFactory<Client> factory, GenericObjectPoolConfig<Client> config) {
        super(factory, config);
    }
}
