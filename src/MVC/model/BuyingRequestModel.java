package MVC.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by plesha on 08-Apr-18.
 */
@XStreamAlias("model.BuyingRequestModel")
public class BuyingRequestModel {

    @XStreamAlias("idRquest")
    private int idRquest;
    @XStreamAlias("medName")
    private String medName;
    @XStreamAlias("quantity")
    private int quantity;
    @XStreamAlias("clientName")
    private String clientName;
    @XStreamAlias("clientAddress")
    private String clientAddress;
    @XStreamAlias("clientMoney")
    private int clientMoney;

    public BuyingRequestModel(){

    }

    public BuyingRequestModel( int idRquest, String medName, int quantity, String clientName, String clientAddress, int clientMoney){
        this.idRquest = idRquest;
        this.medName = medName;
        this.quantity = quantity;
        this.clientAddress = clientAddress;
        this.clientName = clientName;
        this.clientMoney = clientMoney;
    }

    public int getIdRquest() {
        return idRquest;
    }

    public void setIdRquest(int idRquest) {
        this.idRquest = idRquest;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public int getClientMoney() {
        return clientMoney;
    }

    public void setClientMoney(int clientMoney) {
        this.clientMoney = clientMoney;
    }
}
