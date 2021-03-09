package petClinic;

import com.opencsv.exceptions.CsvValidationException;
import petClinic.service.PetClinicService;
import petClinic.utility.HibernateUtil;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {

        PetClinicService.showOption();

        HibernateUtil.shutdown();

    }
}
