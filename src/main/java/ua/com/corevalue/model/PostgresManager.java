package ua.com.corevalue.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import ua.com.corevalue.model.entity.EmployeeData;
import ua.com.corevalue.service.HibernateUtil;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class PostgresManager implements DatabaseManager {

    @Override
    public EmployeeData findEmployeeByEmail(String email) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        EmployeeData data = null;

        try {
            tx = session.beginTransaction();

            data = (EmployeeData) session.createQuery("FROM EmployeeData emp WHERE emp.email=:email")
                    .setParameter("email", email)
                    .uniqueResult();

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return data;
    }

    @Override
    public void saveEmployee(EmployeeData newEmployee) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();
            session.save(newEmployee);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<EmployeeData> getSubordinatesByManagerEmail(String email) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<EmployeeData> resultList = null;

        try {
            tx = session.beginTransaction();

            resultList = session.createNativeQuery("WITH RECURSIVE temp1(EMPLOYEE_ID,FIRST_NAME, LAST_NAME, MANAGER_ID, EMAIL, LEVEL) AS (" +
                    "  SELECT emp.employee_id, emp.first_name, emp.last_name, emp.manager_id, emp.email, 1" +
                    "  FROM employees emp WHERE emp.email = :userEmail " +
                    "  UNION" +
                    "    SELECT emp2.employee_id, emp2.first_name, emp2.last_name, emp2.manager_id, emp2.email, LEVEL + 1 " +
                    "  FROM employees emp2 INNER JOIN temp1 ON (temp1.EMPLOYEE_ID = emp2.manager_id))" +
                    " SELECT  * FROM temp1 WHERE LEVEL > 1 ORDER BY LEVEL", EmployeeData.class)
                    .setParameter("userEmail", email)
                    .getResultList();

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return resultList;
    }

    @Override
    public EmployeeData getCEOByEmployeeEmail(String email) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        EmployeeData ceo = null;

        try {
            tx = session.beginTransaction();

            ceo = session.createNativeQuery("WITH RECURSIVE temp1(EMPLOYEE_ID,FIRST_NAME, LAST_NAME, MANAGER_ID, EMAIL, LEVEL) AS (" +
                    "  SELECT emp.employee_id, emp.first_name, emp.last_name, emp.manager_id, emp.email, 1" +
                    "  FROM employees emp WHERE emp.email = :userEmail " +
                    "  UNION" +
                    "    SELECT emp2.employee_id, emp2.first_name, emp2.last_name, emp2.manager_id, emp2.email, LEVEL + 1 " +
                    "  FROM employees emp2 INNER JOIN temp1 ON (temp1.MANAGER_ID = emp2.employee_id))" +
                    " SELECT  * FROM temp1 ORDER BY LEVEL DESC LIMIT 1", EmployeeData.class)
                    .setParameter("userEmail", email)
                    .uniqueResult();


            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ceo;
    }

    @Override
    public Boolean isCEOExist() {
        Transaction tx = null;
        EmployeeData data = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            data = (EmployeeData) session.createQuery("FROM EmployeeData emp WHERE emp.email IS NULL")
                    .uniqueResult();

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

        return data != null;
    }

    @Override
    public Boolean isEmailExist(String email) {
        Transaction tx = null;
        EmployeeData data = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            data = (EmployeeData) session.createQuery("FROM EmployeeData emp WHERE emp.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

        return data != null;
    }
}
