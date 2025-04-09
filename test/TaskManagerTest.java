import static org.junit.Assert.*;
import org.junit.Test;


public class TaskManagerTest {

    @Test
    public void testAddTask() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Test", "JUnit test", "2025-04-10", "High");
        taskManager.addTask(task);
        // We assume displayTasks will print something — we can't directly assert, so we check size
        assertEquals(1, taskManager.getTasks().size());
    }

    @Test
    public void testEditTask_ValidIndex() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Original", "desc", "2025-04-10", "Medium");
        taskManager.addTask(task);
        Task updated = new Task("Updated", "new desc", "2025-05-01", "Low");
        taskManager.editTask(0, updated);
        assertEquals("Updated", taskManager.getTasks().get(0).getTitle());
    }

    @Test
    public void testDeleteTask_ValidIndex() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Delete", "desc", "2025-04-10", "High");
        taskManager.addTask(task);
        taskManager.deleteTask(0);
        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    public void testMarkTaskAsComplete() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Complete", "desc", "2025-04-10", "Low");
        taskManager.addTask(task);
        taskManager.markTaskAsComplete(0);
        assertTrue(taskManager.getTasks().get(0).isCompleted());
    }
}
