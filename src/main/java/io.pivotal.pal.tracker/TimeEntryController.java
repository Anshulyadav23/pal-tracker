package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }


    @PostMapping()
    public ResponseEntity create(@RequestBody TimeEntry timeEntryControllerToCreate) {
        ResponseEntity responseEntity=new ResponseEntity(timeEntryRepository.create(timeEntryControllerToCreate),HttpStatus.CREATED);
        return responseEntity;
    }


    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        ResponseEntity<TimeEntry> responseEntity;
        TimeEntry timeEntry= timeEntryRepository.find(id);
        if(timeEntry==null)
            responseEntity=new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        else
            responseEntity=new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.OK);
        return  responseEntity;

    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity responseEntity=new ResponseEntity(timeEntryRepository.list(),HttpStatus.OK);
        return responseEntity;
    }


    @PutMapping("/{l}")
    public ResponseEntity update(@PathVariable long l, @RequestBody TimeEntry expected) {

        ResponseEntity responseEntity;
        TimeEntry entry = timeEntryRepository.update(l, expected);
        if(entry==null)
            responseEntity=new ResponseEntity("Data Not found", HttpStatus.NOT_FOUND);
        else
            responseEntity=new ResponseEntity(entry, HttpStatus.OK);
        return  responseEntity;
    }


    @DeleteMapping("/{l}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long l) {

        ResponseEntity<TimeEntry> responseEntity=new ResponseEntity<TimeEntry>(timeEntryRepository.delete(l),HttpStatus.NO_CONTENT);
        return  responseEntity;
    }
}
