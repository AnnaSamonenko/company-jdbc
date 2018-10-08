package entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Employee {
    private long id;
    private String name;
    private String contactInformation;
    private String projectName;
    private String positionName;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Map<Project, List<Date>> getTimeBounds() {
        return timeBounds;
    }

    public void setTimeBounds(Map<Project, List<Date>> timeBounds) {
        this.timeBounds = timeBounds;
    }
}
