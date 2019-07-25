package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        return ResponseEntity.created(null).body(timeEntryRepository.create(timeEntry));
    }

    @RequestMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry read = timeEntryRepository.find(id);
        if(read != null){
            return ResponseEntity.ok().body(read);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry update = timeEntryRepository.update(id, timeEntry);
        if(update != null){
            return ResponseEntity.ok().body(update);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/time-entries", method = RequestMethod.GET)
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok().body(timeEntryRepository.list());
    }
}