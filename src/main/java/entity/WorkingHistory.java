package entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class WorkingHistory {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private Project project;
    @Getter @Setter
    private Employee employee;
    @Getter @Setter
    private LocalDate startDate;
    @Getter @Setter
    private LocalDate endDate;
}
