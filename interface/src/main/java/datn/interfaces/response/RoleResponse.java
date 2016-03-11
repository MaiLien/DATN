package datn.interfaces.response;

import org.springframework.security.core.GrantedAuthority;

public class RoleResponse implements GrantedAuthority {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return name;
    }
}
