package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class MainApp {

   public static void main(String[] args) {
      Logger logger = Logger.getLogger("MainApp");

      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      // Решение:
      // 1) Добавление юзеров с машинами и вывод их на экран

      User user1 = new User("Vitya", "Ivanov", "vitya@gmail.ru");
      User user2 = new User("Igor", "Sidorov", "igor@gmail.ru");
      User user3 = new User("Aleksandr", "Nikolaev", "sasha@gmail.ru");
      Car car1 = new Car("BMW 1501", 133665);
      Car car2 = new Car("Audi 658X", 89552);
      Car car3 = new Car("Mercedes-Benz S221", 355353);
      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      car1.setUser(user1);
      car2.setUser(user2);
      car3.setUser(user3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      userService.listUsers().forEach(System.out::println);
      System.out.println("*********************************************************************");

      // 2) Получение юзера по модели и серии

      logger.info("Получение юзера по модели и серии:");
      System.out.println(userService.getUserByModelAndSeries("Mercedes-Benz S221", 355353));


      context.close();
   }
}
