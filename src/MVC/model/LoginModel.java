package MVC.model;

/**
 * Created by plesha on 08-Apr-18.
 */
public class LoginModel {
    private RegularUserModel userModel;
    private AdministratorModel adminModel;

    public  LoginModel(){
    }

    public  LoginModel( RegularUserModel userModel, AdministratorModel adminModel ){
        this.userModel = userModel;
        this.adminModel = adminModel;
    }

    public RegularUserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(RegularUserModel userModel) {
        this.userModel = userModel;
    }

    public AdministratorModel getAdminModel() {
        return adminModel;
    }

    public void setAdminModel(AdministratorModel adminModel) {
        this.adminModel = adminModel;
    }
}
