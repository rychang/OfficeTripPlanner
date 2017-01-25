package services;

import jdk.nashorn.internal.runtime.ECMAException;
import models.Foo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class FooServiceImpl implements FooService {
    private static final Logger logger = LoggerFactory.getLogger(FooServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(Foo trip) {
        if (trip == null) {
            throw new IllegalArgumentException();
        }
        try {
            em.persist(trip);
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.debug("Trip to location {} for the duration of {} to {} and id {} saved to db.", trip.getRandomNumber(), trip.getDate1(), trip.getDate2());
    }

}
