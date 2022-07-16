import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import tech.kbtg.entities.ClassRoom;
import tech.kbtg.entities.Student;

import static org.junit.jupiter.api.Assertions.*;

class ClassRoomManagementTest {

    @Test
    @Order(1)
    void classRoomShouldBeCreate() {
        ClassRoomManagement classRoomManagement = new ClassRoomManagement();
        classRoomManagement.clearData();

        ClassRoom classRoom = new ClassRoom("Basic Java");
        classRoomManagement.createClass(classRoom);

        assertEquals(1, classRoomManagement.findAll().size());
    }

    @Test
    @Order(2)
    void studentShouldBeAddToClass() {
        ClassRoomManagement classRoomManagement = new ClassRoomManagement();
        classRoomManagement.clearData();

        String className = "Basic Java";
        ClassRoom classRoom = new ClassRoom(className);
        classRoomManagement.createClass(classRoom);

        assertEquals(1, classRoomManagement.findAll().size());

        Student tong = classRoomManagement.addStudent(new Student("Tong", classRoom));
        classRoomManagement.addStudent(new Student("Ice", classRoom));
        classRoomManagement.addStudent(new Student("Focus", classRoom));

        System.out.println("#########################");
        ClassRoom c = classRoomManagement.findClassById(classRoom.getId());
        assertEquals(className, c.getName());
        assertEquals(3, c.getStudents().size());

        System.out.println("=========================");
        Student studentById = classRoomManagement.findStudentById(tong.getId());
        assertEquals(className, studentById.getClassRoom().getName());
    }
}
