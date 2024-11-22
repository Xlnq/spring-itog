package com.example.skillfactory.service;

import com.example.skillfactory.model.BackupLog;
import com.example.skillfactory.repository.BackupLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BackupLogService {

    @Autowired
    private BackupLogRepository backupLogRepository;

    public List<BackupLog> findAll() {
        return backupLogRepository.findAll();
    }

    public BackupLog findById(Integer id) {
        Optional<BackupLog> backupLog = backupLogRepository.findById(id);
        return backupLog.orElse(null);
    }

    public void save(BackupLog backupLog) {
        backupLogRepository.save(backupLog);
    }

    public void delete(Integer id) {
        backupLogRepository.deleteById(id);
    }
}

