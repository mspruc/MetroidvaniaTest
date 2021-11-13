package Action;

import Entity.Entity;

public interface Action {
    Entity source = null;
    Entity target = null;

    void execute();
    void undo();
}
