package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      try (Session session = sessionFactory.openSession()) {
         session.save(user);
      }
   }

   @Override
   public List<User> listUsers() {
      try (Session session = sessionFactory.openSession()) {
         TypedQuery<User> query = session.createQuery("from User", User.class);
         return query.getResultList();
      }
   }

   @Override
   public User getUserByModelAndSeries(String model, int series) {
      try (Session session = sessionFactory.openSession()) {
         Car car = session
                 .createQuery("from Car where model=:model and series=:series", Car.class)
                 .setParameter("model", model)
                 .setParameter("series", series)
                 .getSingleResult();

         return car.getUser();
      }
   }

}
