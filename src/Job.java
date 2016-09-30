import java.util.ArrayList;

/**
 * Created by joshuakeough on 9/26/16.
 */
public class Job {
    private int listId;
    private int salary;
    private String position;
    private int yearsOfExperienceNeeded;
    private String cityLocated;
    private int id;
    private String show = null;


    public Job(int salary, String position, int yearsOfExperienceNeeded, String cityLocated, int id) {
        this.salary = salary;
        this.position = position;
        this.yearsOfExperienceNeeded = yearsOfExperienceNeeded;
        this.cityLocated = cityLocated;
        this.id = id;
    }

    public Job(int listId, int salary, String position, int yearsOfExperienceNeeded, String cityLocated, int id, String show) {
        this.listId = listId;
        this.salary = salary;
        this.position = position;
        this.yearsOfExperienceNeeded = yearsOfExperienceNeeded;
        this.cityLocated = cityLocated;
        this.id = id;
        this.show = show;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getYearsOfExperienceNeeded() {
        return yearsOfExperienceNeeded;
    }

    public void setYearsOfExperienceNeeded(int yearsOfExperienceNeeded) {
        this.yearsOfExperienceNeeded = yearsOfExperienceNeeded;
    }

    public String getCityLocated() {
        return cityLocated;
    }

    public void setCityLocated(String cityLocated) {
        this.cityLocated = cityLocated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String isShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
}
