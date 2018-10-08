package entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Employee {
    private long id;
    private String name;
    private String contactInformation;
    private Project project;
    private Position position;
    private Map<Project, List<Date>> timeBounds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Map<Project, List<Date>> getTimeBounds() {
        return timeBounds;
    }

    public void setTimeBounds(Map<Project, List<Date>> timeBounds) {
        this.timeBounds = timeBounds;
    }
}
