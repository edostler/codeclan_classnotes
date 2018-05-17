import db.DBHelper;
import models.Department;
import models.Engineer;
import models.Manager;

import java.util.List;

public class Runner {

    public static void main(String[] args) {

        Department department = new Department("Sales");
        DBHelper.saveOrUpdate(department);
        Manager manager = new Manager("Peter", "Griffin", 40000, department, 100000);
        DBHelper.saveOrUpdate(manager);
        Engineer engineer1 = new Engineer("Lois", "Griffin", 29000, department);
        DBHelper.saveOrUpdate(engineer1);
        Engineer engineer2 = new Engineer("Stewie", "Griffin", 27000, department);
        DBHelper.saveOrUpdate(engineer2);

        Manager foundManager = DBHelper.find(Manager.class, manager.getId());
        List<Engineer> engineers = DBHelper.getAll(Engineer.class);

        Manager deptManager = DBHelper.findManager(department);

    }
}
