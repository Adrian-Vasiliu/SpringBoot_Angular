package org.springbootbackend.repository.JPACriteria;

import org.springbootbackend.model.Device;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class DeviceDAOImpl implements DeviceDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Device> search2(String keyword, int pageNumber, int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Device> criteriaQuery = criteriaBuilder.createQuery(Device.class);
        Root<Device> deviceRoot = criteriaQuery.from(Device.class);

        Predicate predicate1 = criteriaBuilder.like(deviceRoot.get("description"), "%" + keyword + "%");
        Predicate predicate2 = criteriaBuilder.like(deviceRoot.get("address"), "%" + keyword + "%");
        //Predicate predicate3 = criteriaBuilder.like(device.get("max_hourly_energy_consumption"), "%" + keyword + "%");
        Predicate predicate = criteriaBuilder.or(predicate1, predicate2);
        criteriaQuery.where(predicate);

//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(predicate);
//        predicates.add(criteriaBuilder.like(device.get("address"), "%" + keyword + "%"));
//        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Device> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

}
