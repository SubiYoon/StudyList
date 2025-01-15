package io.jpastudy.study01.jpa;

import io.jpastudy.study01.jpa.entity.Customer;
import io.jpastudy.study01.jpa.entity.Major;
import io.jpastudy.study01.jpa.entity.Student;
import jakarta.persistence.*;

import java.util.List;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("study01");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        //
        try {
//            Customer customer = new Customer(1L, "Jin"); // 비영속 상태
//            em.persist(customer); // 영속 상태
//            em.detach(customer); // 준영속 상태

            // 쿼리를 날려 DB에서 가져온다.
//            Customer foundCustomer = em.find(Customer.class, "ID0005");
//            System.out.println(foundCustomer.toString());



//            Customer customer = new Customer();
//            customer.setName("Kim");
//            customer.setRegisterDate(System.currentTimeMillis());
//            em.persist(customer);

            Major major = new Major("Computer Science", "College of Engineering");
            em.persist(major);

            Student student = new Student("Kim", "3");
            student.setMajor(major);
            em.persist(student);

            em.flush();
            em.clear();

            Student foundStudent = em.find(Student.class, 1);
            System.out.println(foundStudent.getStudentId());
            System.out.println(foundStudent.getName());

            System.out.println(foundStudent.getMajor());

//            System.out.println(em.find(Major.class, student1.getMajor()));
            System.out.println("============ Before Commit ============");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
        //
    }
}
