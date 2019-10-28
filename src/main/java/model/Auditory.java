package model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Auditory {
    @Min(value = 1, message = "Building number must be greater than 0")
    private int buildingNumber;
    @Min(value = 1, message = "Floor must be greater than 0")
    private int floor;
    @NotNull(message = "Auditory number must be not null")
    private String auditoryNumber;

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public int getFloor() {
        return floor;
    }

    public String getAuditoryNumber() {
        return auditoryNumber;
    }

    public Auditory setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
        return this;
    }

    public Auditory setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public Auditory setAuditoryNumber(String auditoryNumber) {
        this.auditoryNumber = auditoryNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditory auditory = (Auditory) o;
        return buildingNumber == auditory.buildingNumber &&
                floor == auditory.floor &&
                auditoryNumber == auditory.auditoryNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingNumber, floor, auditoryNumber);
    }
}
