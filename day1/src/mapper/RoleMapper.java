package mapper;

import pojo.Role;

public interface RoleMapper {
    public Role getRole(Long id);
    public int insertRole(Role role);
}
