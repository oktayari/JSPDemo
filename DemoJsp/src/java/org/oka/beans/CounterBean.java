package org.oka.beans;

import javax.ejb.Singleton;
import javax.ejb.ConcurrencyManagement;
import static javax.ejb.ConcurrencyManagementType.CONTAINER;
import javax.ejb.Lock;
import static javax.ejb.LockType.WRITE;


@Singleton
@ConcurrencyManagement(CONTAINER)
public class CounterBean {

    private int hits = 1;
  
    @Lock(WRITE)
    public int getHits() {
        return hits++;
    }
}
