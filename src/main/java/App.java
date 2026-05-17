import controller.EmployeeController;
import database.EmployeeDb;
import mapper.EmployeeMapper;
import repository.EmployeeRepository;
import service.EmployeeService;
import service.impl.EmployeeServiceImpl;
import view.EmployeeView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    static void main() throws SQLException {

         String url ="jdbc:postgresql://localhost:6767/ite_db";
         String user= "qwer";
         String pass = "qwer123";

        try{
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("===[ Connection succesfuly ]===");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        EmployeeView view = new EmployeeView();

//        EmployeeDb db = new EmployeeDb();
        EmployeeMapper mapper = new EmployeeMapper();
//        EmployeeRepository repository = new EmployeeRepository(db);
        EmployeeRepository repository = new EmployeeRepository();
//        EmployeeService service = new EmployeeServiceImpl(repository, mapper);
        EmployeeService service = new EmployeeServiceImpl(repository,mapper);
        EmployeeController controller = new EmployeeController(view, service);

//        controller.create();
        controller.start();
    }

}
