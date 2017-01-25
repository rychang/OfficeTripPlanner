package services;

import models.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class FooServiceImpl implements FooService {
    private static final Logger logger = LoggerFactory.getLogger(FooServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Foo trip) {
        if (trip == null) {
            throw new IllegalArgumentException();
        }
        em.persist(trip);
        logger.debug("Trip to location {} for the duration of {} to {} and id {} saved to db.", trip.getRandomNumber(), trip.getDate1(), trip.getDate2());
    }

}
