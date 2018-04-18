package xml.operations;

import MVC.model.AdministratorModel;
import MVC.model.BuyingRequestModel;
import MVC.model.MedicationModel;
import MVC.model.RegularUserModel;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by plesha on 18-Apr-18.
 */
public class XMLOperation {

    public XMLOperation(){

    }

    public  <T> T readXMLFile( String fileName, int classModel ){

        final String path = "E:\\GitHub\\assignment-2-pharmacy-pleshagabi\\XMLFiles\\";

        Class<?>[] classes = new Class[] { MedicationModel.class, AdministratorModel.class, RegularUserModel.class, BuyingRequestModel.class};
        XStream xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(classes);
        xStream.processAnnotations(classes[classModel]);

        switch( classModel ){
            case 0: {
                ArrayList<MedicationModel> readArray = (ArrayList<MedicationModel>) xStream.fromXML(new File(path + fileName + ".xml"));
                return (T) readArray;
            }
            case 1: {
                ArrayList<AdministratorModel> readArray = (ArrayList<AdministratorModel>) xStream.fromXML(new File(path + fileName + ".xml"));
                return (T) readArray;
            }
            case 2: {
                ArrayList<RegularUserModel> readArray = (ArrayList<RegularUserModel>) xStream.fromXML(new File(path + fileName + ".xml"));
                return (T) readArray;
            }
            case 3: {
                ArrayList<BuyingRequestModel> readArray = (ArrayList<BuyingRequestModel>) xStream.fromXML(new File(path + fileName + ".xml"));
                return (T) readArray;
            }
            default: {
                ArrayList<MedicationModel> readArray = (ArrayList<MedicationModel>) xStream.fromXML(new File(path + fileName + ".xml"));
                return (T) readArray;
            }
        }
    }

    public void saveDataToXMLFile( String fileName, Object data, int idClass ){
        FileOutputStream fos = null;

        Class<?>[] classes = new Class[] { MedicationModel.class, AdministratorModel.class, RegularUserModel.class, BuyingRequestModel.class };
        XStream xStream = new XStream();

        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(classes);


        try{
            String xml = "";

            switch (idClass){
                case 0:{
                    xml  = xStream.toXML((ArrayList<MedicationModel>)data);
                    break;
                }
                case 1:{
                    xml  = xStream.toXML((ArrayList<AdministratorModel>)data);
                    break;
                }
                case 2:{
                    xml  = xStream.toXML((ArrayList<RegularUserModel>)data);
                    break;
                }
                case 3:{
                    xml  = xStream.toXML((ArrayList<BuyingRequestModel>)data);
                    break;
                }
                default:{
                    xml  = xStream.toXML((ArrayList<MedicationModel>)data);
                    break;
                }
            }

            fos = new FileOutputStream("E:\\GitHub\\assignment-2-pharmacy-pleshagabi\\XMLFiles\\" + fileName + ".xml");
            fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
            byte[] bytes = xml.getBytes("UTF-8");
            fos.write(bytes);

        }catch (Exception e){
            System.err.println("Error in XML Write: " + e.getMessage());
        }
        finally{
            if(fos != null){
                try{
                    fos.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
