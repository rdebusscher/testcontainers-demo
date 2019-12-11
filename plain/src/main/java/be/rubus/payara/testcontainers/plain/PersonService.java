package be.rubus.payara.testcontainers.plain;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class PersonService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public double getAverageAge() {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList().stream()
                .mapToDouble(Person::getAge)
                .average()
                .orElse(Double.NaN);
    }
}
