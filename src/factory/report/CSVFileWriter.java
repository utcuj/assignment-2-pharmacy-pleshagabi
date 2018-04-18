package factory.report;

import MVC.model.MedicationModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by plesha on 09-Apr-18.
 */
public class CSVFileWriter implements Report {
    @Override
    public void createReportFile(String username, ArrayList<MedicationModel> meds) {

        //Delimiter used in CSV file


        //CSV file header
        final String FILE_HEADER = "ID,NAME,INGREDIENTS,MANUFACTURER,QUANTITY,PRICE";


            FileWriter fileWriter = null;

            try {
                fileWriter = new FileWriter("E:\\Pharmacy Report.csv");

                //Write the CSV file header
                fileWriter.append(FILE_HEADER.toString() + "\n");

                //Write a new student object list to the CSV file
                for (MedicationModel m : meds) {
                    if( m.getQuantity() == 0 ) {
                        fileWriter.append(Integer.toString(m.getId()) + ",");
                        fileWriter.append(m.getName() + ",");
                        fileWriter.append(m.getIngredients() + ",");
                        fileWriter.append(m.getManufacturer() + ",");
                        fileWriter.append(Integer.toString(m.getQuantity()) + ",");
                        fileWriter.append(Integer.toString(m.getPrice()) + ",");

                        fileWriter.append("\n");
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in CsvFileWriter !!!");
                e.printStackTrace();
            } finally {

                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error while flushing/closing fileWriter !!!");
                    e.printStackTrace();
                }

            }
        }

}
