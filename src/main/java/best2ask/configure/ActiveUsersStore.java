package best2ask.configure;

import java.util.ArrayList;
import java.util.List;

public class ActiveUsersStore {

    public List<String> users;

    public ActiveUsersStore() {
        users = new ArrayList<String>();
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}