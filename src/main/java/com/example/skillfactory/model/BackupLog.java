package com.example.skillfactory.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Backup_Logs")
public class BackupLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @Column(name = "backup_date", nullable = false)
    private LocalDateTime backupDate;

    @Column(name = "backup_status", length = 50)
    private String backupStatus;

    @ManyToOne(cascade = CascadeType.REMOVE)  // Изменили CascadeType.ALL на CascadeType.REMOVE
    @JoinColumn(name = "admin_id")
    private User admin;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public LocalDateTime getBackupDate() {
        return backupDate;
    }

    public void setBackupDate(LocalDateTime backupDate) {
        this.backupDate = backupDate;
    }

    public String getBackupStatus() {
        return backupStatus;
    }

    public void setBackupStatus(String backupStatus) {
        this.backupStatus = backupStatus;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
