package com.ecomm.ecomm_profile_service_application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createdAt;
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    public BaseModel() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.status = Status.ACTIVE;
    }
}
