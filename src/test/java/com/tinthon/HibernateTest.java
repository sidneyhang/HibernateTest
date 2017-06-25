package com.tinthon;

import com.tinthon.model.Message;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by sidne on 2017/6/23.
 */
public class HibernateTest{

    @Test
    public void testMessage() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HelloWorld");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Message message = new Message();
        message.setText("hello");

        entityManager.persist(message);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Message> messages = entityManager.createQuery("select m from  Message m").getResultList();
        System.out.println(messages.get(0).getId()+ ", " + messages.get(0).getText());

        messages.get(0).setText("world");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
