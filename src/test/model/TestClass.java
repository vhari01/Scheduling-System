package model;

import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;


public class TestClass{
    protected Patient p1;
    protected Patient p2;
    protected Patient p3;
    protected Patient p4;
    protected Patient p5;
    protected Patient p6;
    protected Specialist Orthopedic;
    protected Specialist Cardiologist;

    @BeforeEach
    void runBefore(){
        p1 = new Patient("Max",23, true, 3, Orthopedic, LocalDate.of(2024, 10, 13));
        p2 = new Patient("Jack",50, false, 5, Cardiologist, LocalDate.of(2024, 10, 13));
        p3 = new Patient("Gil",38, true, 3, Cardiologist, LocalDate.of(2024, 10, 13));
        p4 = new Patient("cacy",32, true, 3, Orthopedic, LocalDate.of(2024, 10, 13));
        p5 = new Patient("Alex", 26, true, 4, Orthopedic, LocalDate.of(2024, 10, 13));
        p6 = new Patient("Kale", 37, false, 5, Orthopedic, LocalDate.of(2024, 10, 13));
    }

} 