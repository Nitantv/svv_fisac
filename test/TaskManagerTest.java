import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    public void setup() {
        taskManager = new TaskManager();
    }

    @Test
    public void testAddTask() {
        Task task = new Task("Test", "Desc", "2025-04-10", "High");
        taskManager.addTask(task);
        assertEquals(1, taskManager.getTasks().size());
        assertEquals("Test", taskManager.getTasks().get(0).getTitle());
    }

    @Test
    public void testDisplayTasksEmpty() {
        // This test checks if empty list is handled - output is printed, so not assertable easily
        // We'll just make sure no exceptions and the list remains empty
        taskManager.displayTasks();
        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    public void testDisplayTasksWithTasks() {
        taskManager.addTask(new Task("Task 1", "Desc 1", "2025-04-10", "Medium"));
        taskManager.addTask(new Task("Task 2", "Desc 2", "2025-04-11", "Low"));
        List<Task> tasks = taskManager.getTasks();
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());
    }

    @Test
    public void testEditTaskValidIndex() {
        taskManager.addTask(new Task("Old Title", "Old Desc", "2025-04-10", "Low"));
        Task updated = new Task("New Title", "New Desc", "2025-04-11", "High");
        taskManager.editTask(0, updated);
        Task result = taskManager.getTasks().get(0);
        assertEquals("New Title", result.getTitle());
        assertEquals("High", result.getPriority());
    }

    @Test
    public void testEditTaskInvalidIndex() {
        Task updated = new Task("New Title", "New Desc", "2025-04-11", "High");
        taskManager.editTask(5, updated);
        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    public void testDeleteTaskValidIndex() {
        taskManager.addTask(new Task("Delete Me", "Desc", "2025-04-10", "High"));
        assertEquals(1, taskManager.getTasks().size());
        taskManager.deleteTask(0);
        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    public void testDeleteTaskInvalidIndex() {
        taskManager.deleteTask(10); // should do nothing
        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    public void testMarkTaskAsCompleteValidIndex() {
        Task task = new Task("Complete Me", "Desc", "2025-04-10", "Low");
        taskManager.addTask(task);
        taskManager.markTaskAsComplete(0);
        assertTrue(taskManager.getTasks().get(0).isCompleted());
    }

    @Test
    public void testMarkTaskAsCompleteInvalidIndex() {
        taskManager.markTaskAsComplete(99); // should do nothing
        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    public void testTaskToString() {
        Task task = new Task("Title", "Description", "2025-04-10", "Medium");
        String output = task.toString();
        assertTrue(output.contains("Title"));
        assertTrue(output.contains("Description"));
        assertTrue(output.contains("Not Completed"));
    }

    @Test
    public void testAddTaskWithEmptyFields() {
        Task task = new Task("", "", "", "");
        taskManager.addTask(task);
        Task added = taskManager.getTasks().get(0);
        assertEquals("", added.getTitle());
        assertEquals("", added.getDueDate());
    }
}
