package com.mdevoc.persistence;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import java.util.Objects;

@MappedSuperclass
@Schema(description = "Base schema for the entities of the application.")
public abstract class BaseEntity {

    private static final String SEQUENCE_GENERATOR_NAME = "idSequenceGenerator";

    @Id
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, sequenceName = "id_sequence", allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @Schema(description = "The id is the primary key for an entity and can be used as unique key. Once persisted, this value will always be required.",
            nullable = true)
    private Long id;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) object;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
