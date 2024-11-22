package com.example.skillfactory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Property_Types")
public class PropertyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer typeId;

    @NotNull
    @Column(name = "type_name", length = 100)
    private String typeName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public @NotNull String getTypeName() {
        return typeName;
    }

    public void setTypeName(@NotNull String typeName) {
        this.typeName = typeName;
    }
}
