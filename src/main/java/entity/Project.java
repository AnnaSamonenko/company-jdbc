package entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Project {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private LocalDate startDate;
    @Getter @Setter
    private LocalDate endDate;
}
