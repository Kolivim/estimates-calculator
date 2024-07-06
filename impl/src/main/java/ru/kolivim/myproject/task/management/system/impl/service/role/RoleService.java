package ru.kolivim.myproject.task.management.system.impl.service.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolivim.myproject.task.management.system.domain.role.Role;
import ru.kolivim.myproject.task.management.system.impl.repository.role.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Set<Role> getRoleSet(List<String> roleNames){
        Set<Role> roles = new HashSet<>();
        for(String roleName : roleNames){
            roles.add(roleRepository.getByRole(roleName).orElse(null));
        }
        return roles;
    }
}
