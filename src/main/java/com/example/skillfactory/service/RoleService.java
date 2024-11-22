package com.example.skillfactory.service;

import com.example.skillfactory.model.Role;
import com.example.skillfactory.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Integer id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }

    public Role findRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Роль с именем " + roleName + " не найдена"));
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}

