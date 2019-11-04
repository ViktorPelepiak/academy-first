package model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Subject {
    private Long id;
    @NotNull(message = "Subject name must be not null")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Subject setId(Long id) {
        this.id = id;
        return this;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return name.equals(subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
