package com.example.skillfactory.repository;

import com.example.skillfactory.model.BackupLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupLogRepository extends JpaRepository<BackupLog, Integer> {
}

