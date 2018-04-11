package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long,TimeEntry> repository=new HashMap<Long, TimeEntry>();


    @Override
    public TimeEntry create(TimeEntry any) {

        any.setId(repository.size()+1);
        repository.put(any.getId(),any);
        return any;
    }

    @Override
    public TimeEntry find(long l) {
       //System.out.println(repository.get(l).toString());
        return repository.get(l);

    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> entryList=new ArrayList<TimeEntry>();
        Set<Long> keys=repository.keySet();
        for (long key:keys) {
            entryList.add(repository.get(key));
        }
        return entryList;
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
       if(!repository.containsKey(eq))
           return null;
       any.setId(eq);
       repository.put(eq,any);
       return any;
    }

    @Override
    public TimeEntry delete(long l) {
        TimeEntry entry=repository.get(l);
        repository.remove(l);
        return entry;
    }
}
