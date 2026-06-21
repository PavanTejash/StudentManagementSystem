package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.Student;
import utility.JPAUtil;

public class StudentDAO {

    public void saveStudent(Student student) {

        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();

        em.close();
    }

    public void updateStudent(Student student) {

        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();

        em.close();
    }

    public void deleteStudent(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        Student student = em.find(Student.class, id);

        if (student != null) {

            em.getTransaction().begin();
            em.remove(student);
            em.getTransaction().commit();
        }

        em.close();
    }

    public Student searchStudent(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        Student student = em.find(Student.class, id);

        em.close();

        return student;
    }

    public List<Student> getAllStudents() {

        EntityManager em = JPAUtil.getEntityManager();

        TypedQuery<Student> query =
                em.createQuery("from Student", Student.class);

        List<Student> students = query.getResultList();

        em.close();

        return students;
    }
}