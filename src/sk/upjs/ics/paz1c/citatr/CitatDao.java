package sk.upjs.ics.paz1c.citatr;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CitatDao {
    private List<Citat> citaty = new LinkedList<Citat>();
    
    private long dalsieId = 1;

    public CitatDao() {
        Citat citat = new Citat();
        citat.setText("E = mc^2");
        citat.setAutor("Einstein");
        
        save(citat);
        
        Citat citat2 = new Citat();
        citat2.setText("YOLO");
        citat2.setAutor("Drake");
        
        save(citat2);        
    }
        
    
    
    Citat findById(Long id) {
        for(Citat citat : citaty) {
            if(citat.getId().equals(id)) {
                return citat;
            }
        }
        return null;
    }
    
    void save(Citat citat) {
       citat.setId(dalsieId);
       citaty.add(citat);
       dalsieId++;
    }

    int count() {
        return citaty.size();
    }

    Citat dajNahodnyCitat() {
        if(citaty.isEmpty()) {
            return null;
        }
        
        Collections.shuffle(citaty);
        return citaty.get(0);
    }
    
}
