import entity.Password;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class Hibernate {

    private SessionFactory factory;

    public Hibernate() {
        this.factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(entity.Password.class)
                .buildSessionFactory();
    }

    public void closeFactory() {
        factory.close();
    }

    public void addNewPassword(Password password){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(password);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            factory.close();
            e.printStackTrace();
        }
    }

    public List<Password> getAllPasswords(){

        List<Password> passwords = null;

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            passwords = session.createQuery("FROM Password")
                            .getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            factory.close();
            e.printStackTrace();
        }
        return passwords;
    }

    public List<Password> getPasswordsFromCategory(String category){
        List<Password> passwords = null;

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            String hql = "FROM Password WHERE category = :paramName";
            passwords = session.createQuery(hql)
                    .setParameter("paramName", category)
                    .getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            factory.close();
            e.printStackTrace();
        }
        return passwords;
    }

    public void deletePassword(long id){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Password password = session.get(Password.class, id);
            session.delete(password);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            factory.close();
            e.printStackTrace();
        }
    }

    public void deleteCategoryOfPasswords(String category){
        List<Password> passwords = getPasswordsFromCategory(category);

        for (Password password : passwords)
            deletePassword(password.getId());
    }
}
