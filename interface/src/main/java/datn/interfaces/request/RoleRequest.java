package datn.interfaces.request;

import java.util.UUID;

public class RoleRequest {

    private String id = UUID.randomUUID().toString();
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
}
