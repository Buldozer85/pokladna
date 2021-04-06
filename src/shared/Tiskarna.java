package shared;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;



import java.text.SimpleDateFormat;

public class Tiskarna {
    private static Tiskarna instance = null;

    public static Tiskarna getITiskarna() {
        if (instance == null) {
            instance = new Tiskarna();
        }
        return instance;
    }

    public void Tiskni(Objednavka uctenka) {
        try (FileWriter fw = new FileWriter("uctenka.txt")) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(uctenka.getCasObjednavky());

            DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String result = df.format(date);
            fw.write("Objednávka z " + result);
            fw.write("\n_____________________");
            fw.write("\n");
            for (Polozka polozka : uctenka.getPolozky()) {
                fw.write("\t" + polozka.getNazev() + "\t" + polozka.getCena().toString() + " Kč");
                fw.write("\n");
                for (Pridavek pridavek : polozka.getPridavky()) {
                    fw.write("\n\t\t" + "+" + pridavek.getNazev() + "\t" + pridavek.getCena().toString() + " Kč");

                }
                fw.write("\n");
            }
            fw.write("\n Celková cena: " + uctenka.getCena().toString());
            

        } catch (IOException  | ParseException e) {
            e.printStackTrace();
            
        }
    }

}
