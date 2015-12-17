package org.arthan.jms.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ashamsiev on 16.12.2015
 */
public class EntityManagerProducer {

    @Produces
    @PersistenceContext
    private EntityManager entityManager;
}
