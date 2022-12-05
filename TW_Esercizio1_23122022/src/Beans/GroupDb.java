package Beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GroupDb implements Serializable{
    private Map<Integer, Group> groups;

    public GroupDb() {
        this.groups = new HashMap<Integer, Group>();
    }

    
    public Group add(Group group) {
        return this.groups.put(group.getId(), group);
    }

    public Group remove(Integer group) {
        return this.groups.remove(group);
    }

    public Group replace(Group group) {
        return this.groups.replace(group.getId(),group);
    }

    public boolean containsKey(Integer group) {
        return this.groups.containsKey(group);
    }

    public boolean contains(Group group) {
        return this.groups.containsKey(group.getId());
    }

    public Group get(Integer group) {
        return this.groups.get(group);
    }

    public Collection<Group> values() {
        return this.groups.values();
    }

    public void empty() {
        this.groups.clear();
    }


    public Map<Integer, Group> getGroups() {
        return groups;
    }


    public void setGroups(Map<Integer, Group> groups) {
        this.groups = groups;
    }
}
