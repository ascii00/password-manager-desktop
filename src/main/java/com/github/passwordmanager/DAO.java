package com.github.passwordmanager;

import com.github.passwordmanager.entity.Password;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class DAO {

    private final SessionFactory factory;

    public DAO() {
        this.factory = new Configuration()
                .configure("com/github/passwordmanager/hibernate.cfg.xml")
                .addAnnotatedClass(Password.class)
                .buildSessionFactory();
    }

    public void addNewPassword(Password password) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(password);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            factory.close();
            e.printStackTrace();
        }
    }

    public List<Password> getAllPasswords() {

        List<Password> passwords = null;

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            passwords = session.createQuery("FROM Password")
                    .getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            factory.close();
            e.printStackTrace();
        }
        passwords.forEach(password -> {
           if (password.getPass().length() > 2)
               password.setPass(AESEncryption.decrypt(password.getPass()));
        });
        return passwords;
    }

    public List<Password> getPasswordsFromCategory(String category) {
        if (category.equals("ALL"))
            return getAllPasswords();

        List<Password> passwords = null;

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            String hql = "FROM Password WHERE category = :paramName";
            passwords = session.createQuery(hql)
                    .setParameter("paramName", category)
                    .getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            factory.close();
            e.printStackTrace();
        }

        passwords.forEach(password -> {
            if (password.getPass().length() > 2)
                password.setPass(AESEncryption.decrypt(password.getPass()));
        });
        return passwords;
    }

    public void deletePassword(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Password password = session.get(Password.class, id);
            session.delete(password);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            factory.close();
            e.printStackTrace();
        }
    }

    public void deletePassword(Password passwordToDelete){
        long passwordID = getPasswordId(passwordToDelete);
        deletePassword(passwordID);
    }

    public long getPasswordId(Password password){
        long id = 0;
        String login = password.getLogin();
        String pass = password.getPass();
        String service = password.getName();
        String category = password.getCategory();

        String hql = "SELECT id FROM Password WHERE " +
                "name = :paramName AND " +
                "category = :paramCategory AND " +
                "pass = :paramPass AND " +
                "login = :paramLogin";

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            id = (Long) session.createQuery(hql)
                    .setParameter("paramName", service)
                    .setParameter("paramCategory", category)
                    .setParameter("paramPass", pass)
                    .setParameter("paramLogin", login)
                    .getSingleResult();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            factory.close();
            e.printStackTrace();
        }
        return id;
    }
}