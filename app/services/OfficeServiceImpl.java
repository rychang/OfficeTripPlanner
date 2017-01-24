package services;

import models.Office;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private static final Logger logger = LoggerFactory.getLogger(OfficeServiceImpl.class);

    @PersistenceContext
    private EntityManager em;


    @Transactional
    public void initializeDb() {
        List<String> officeLocs = sofiOfficeLocations();
        logger.debug("In dbInitialization: officeLocs = " + officeLocs);
    }
    public List<String> sofiOfficeLocations() {
        List<String> officeLocations = new ArrayList<String>();
        officeLocations.add("Healdsburg, CA");
        officeLocations.add("Helena, MT");
        officeLocations.add("New York, NY");
        officeLocations.add("Reston, VA");
        officeLocations.add("Salt Lake City, UT");
        officeLocations.add("San Francisco, CA");
        officeLocations.add("The Woodlands, TX");
        return officeLocations;
    }

    public List<Office> getAllLocations() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> query = cb.createQuery(Office.class);
        Root<Office> rootEntry = query.from(Office.class);
        CriteriaQuery<Office> all = query.select(rootEntry);
        TypedQuery<Office> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public Office getLocation(String cityName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> query = cb.createQuery(Office.class);
        Root<Office> rootEntry = query.from(Office.class);

        if (query.where(cb.equal(rootEntry.get("name"), cityName)) == null) {
            throw new NoResultException("No entity found for query: Office location not in database");
        }
        Office location = em.createQuery(query).getSingleResult();
        return location;
    }
}
