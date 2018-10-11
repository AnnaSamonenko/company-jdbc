package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Employee {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String contactInformation;
    @Getter @Setter
    private Project project;
    @Getter @Setter
    private Position position;
    @Setter
    private List<WorkingHistory> workingHistory;

    @Override
    public String toString() {
        return "Employee{" +
                "id: " + id +
                ", name: " + name +
                ", contactInformation: " + contactInformation +
                ", project: " + project.getTitle() +
                ", position: " + position.getTitle() +
                ", amount of projects: " + workingHistory.size() + "}";
    }
}
