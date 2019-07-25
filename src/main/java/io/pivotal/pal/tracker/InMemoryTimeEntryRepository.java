package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> entities = new HashMap<>();
    private int counter = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(new Long(counter));
        entities.put(timeEntry.getId(), timeEntry);
        counter++;
        return timeEntry;
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (entities.containsKey(id)) {
            timeEntry.setId(id);
            entities.put(id, timeEntry);

        } else {
            return null;
        }
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return entities.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return entities.values().stream().collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        entities.remove(id);
    }
}