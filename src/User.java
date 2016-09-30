import java.util.ArrayList;

/**
 * Created by joshuakeough on 9/26/16.
 */
public class User {
    private String firstName;
    private String lastName;
    private String currentJob;
    private int id;


    public User(String firstName, String lastName, String currentJob, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentJob = currentJob;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

