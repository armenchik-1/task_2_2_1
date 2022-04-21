package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//
//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = " + user.getId());
//         System.out.println("First Name = " + user.getFirstName());
//         System.out.println("Last Name = " + user.getLastName());
//         System.out.println("Email = " + user.getEmail());
//         System.out.println();
//      }


      // Решение:
      // 1) Добавление юзеров с машинами и вывод их на экран

      User user5 = new User("Vitya", "Ivanov", "vitya@gmail.ru");
      User user6 = new User("Igor", "Sidorov", "igor@gmail.ru");
      User user7 = new User("Aleksandr", "Nikolaev", "sasha@gmail.ru");
      Car car5 = new Car("BMW 1501", 133665);
      Car car6 = new Car("Audi 658X", 89552);
      Car car7 = new Car("Mercedes-Benz S221", 355353);
      user5.setCar(car5);
      user6.setCar(car6);
      user7.setCar(car7);
      car5.setUser(user5);
      car6.setUser(user6);
      car7.setUser(user7);

      userService.add(user5);
      userService.add(user6);
      userService.add(user7);

      userService.listUsers().forEach(System.out::println);
      System.out.println("*********************************************************************");

      // 2) Получение юзера по модели и серии

      System.out.println("Получение юзера по модели и серии:");
      System.out.println(userService.getUserByModelAndSeries("Mercedes-Benz S221", 355353));


      context.close();
   }
}
