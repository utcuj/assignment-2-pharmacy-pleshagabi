package factory.report;

import MVC.model.MedicationModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by plesha on 09-Apr-18.
 */
public interface Report {
    void createReportFile( String username, ArrayList<MedicationModel> meds ) throws IOException;
}
