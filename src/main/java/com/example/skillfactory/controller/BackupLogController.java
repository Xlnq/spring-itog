package com.example.skillfactory.controller;

import com.example.skillfactory.model.BackupLog;
import com.example.skillfactory.service.BackupLogService;
import com.example.skillfactory.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/backup-logs")
public class BackupLogController {

    @Autowired
    private BackupLogService backupLogService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String listBackupLogs(Model model) {
        model.addAttribute("backupLogs", backupLogService.findAll());
        return "backupLog/list";
    }

    @GetMapping("/new")
    public String newBackupLog(Model model) {
        model.addAttribute("backupLog", new BackupLog());
        model.addAttribute("users", userService.findAll());
        return "backupLog/form";
    }

    @PostMapping("/save")
    public String saveBackupLog(BackupLog backupLog) {
        backupLogService.save(backupLog);
        return "redirect:/backup-logs";
    }

    @GetMapping("/edit/{id}")
    public String editBackupLog(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("backupLog", backupLogService.findById(id));
        model.addAttribute("users", userService.findAll());
        return "backupLog/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBackupLog(@PathVariable("id") Integer id) {
        backupLogService.delete(id);
        return "redirect:/backup-logs";
    }
}

