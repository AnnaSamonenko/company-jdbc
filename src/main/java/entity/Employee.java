package entity;

import java.util.List;

public class Employee {
    private int id;
    private String name;
    private String contactInformation;
    private Project project;
    private Position position;
    private List<WorkingHistory> workingHistory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<WorkingHistory> getWorkingHistory() {
        return workingHistory;
    }

    public void setWorkingHistory(List<WorkingHistory> workingHistory) {
        this.workingHistory = workingHistory;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id: " + id +
                ", name: " + name +
                ", contactInformation: " + contactInformation +
                ", project: " + project.getTitle() +
                ", position: " + position.getTitle() +
                ", amount of projects: " + workingHistory.size() +
                '}';
    }
}
