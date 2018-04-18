package factory.report;

/**
 * Created by plesha on 09-Apr-18.
 */
public class ReportFactory {

    public ReportFactory(){

    }

    public Report getReportType( String type ){
        if( type == null ){
            return null;
        }
        else if( type.equalsIgnoreCase("pdf") ){
            return new PDFFileWriter();
        }
        else if( type.equalsIgnoreCase("csv") ){
            return new CSVFileWriter();
        }

        return null;
    }

}
