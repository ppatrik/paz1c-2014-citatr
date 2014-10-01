package sk.upjs.ics.paz1c.citatr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        for (Citat citat : citaty) {
            if (citat.getId().equals(id)) {
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
        if (citaty.isEmpty()) {
            return null;
        }

        Collections.shuffle(citaty);
        return citaty.get(0);
    }

    void saveToFile(File file) {
        FileOutputStream ostream = null;
        ObjectOutputStream output = null;
        try {
            ostream = new FileOutputStream(file);
            output = new ObjectOutputStream(ostream);
            for (Citat citat : citaty) {
                output.writeObject(citat);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ostream != null) {
                try {
                    ostream.close();
                } catch (IOException ex) {
                    Logger.getLogger(CitatDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(CitatDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static CitatDao loadFromFile(File file) throws IOException {
        CitatDao citatDao = new CitatDao();
        FileInputStream istream = null;
        ObjectInputStream input = null;
        try {
            istream = new FileInputStream(file);
            input = new ObjectInputStream(istream);
            while (istream.available() > 0) {
                Object found = input.readObject();
                if (found == null) {
                    break;
                }
                citatDao.save((Citat) found);
            }
            input.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            if (istream != null) {
                istream.close();
            }
            if (input != null) {
                input.close();
            }
        }
        return citatDao;
    }

}
