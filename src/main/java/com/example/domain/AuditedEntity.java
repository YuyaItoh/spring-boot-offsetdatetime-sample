package com.example.domain;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;

@MappedSuperclass
@Getter
public class AuditedEntity {
    private OffsetDateTime createDateTime;
    private OffsetDateTime updateDateTime;

    @PrePersist
    public void prePersist() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        createDateTime = now;
        updateDateTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        updateDateTime = OffsetDateTime.now(ZoneOffset.UTC);
    }
}
