package TODO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Note from SB - having difficulties running the tests. Am able to run it from terminal by building the gradle file though.
class ScheduleTest {

    @Test
    void getName() {
        Schedule s = new Schedule("Test");
        //Test to fail. Does fail when I run gradle build.
        assertEquals("SomethingElse", s.getName());
        //Test to pass
        assertEquals("Test", s.getName());
    }

    @Test
    void changeName() {
        Schedule s = new Schedule("Test");
        s.changeName("Changed");
        //Test to fail. Does fail when I run gradle build.
        assertEquals("Test", s.getName());
        //Test to pass.
        assertEquals("Changed", s.getName());
    }

    @Test
    void addClass() {
    }

    @Test
    void getClasses() {
    }

    @Test
    void removeClass() {
    }

    @Test
    void hasClass() {
    }

    @Test
    void hasTimeConflict() {
    }
}