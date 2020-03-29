package car.controller;

import car.model.Car;
import car.model.CarDAO;
import car.model.User;
import car.model.UserDAO;
import car.utils.Validation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Label error;
    @FXML
    private Button close;
    @FXML
    private TextField regUser;
    @FXML
    private PasswordField regPassw;
    @FXML
    private PasswordField regConfPassw;
    @FXML
    private TextField regEmail;
    @FXML
    private Label regError;
    @FXML
    private CheckBox spoiler;
    @FXML
    private CheckBox autoParki;
    @FXML
    private CheckBox sportseats;
    @FXML
    private RadioButton electric;
    @FXML
    private RadioButton dElectric;
    @FXML
    private RadioButton pElectric;
    @FXML
    private ComboBox comboNum;
    @FXML
    private TextField make;
    @FXML
    private TextField model;
    @FXML
    private Button create;
    @FXML
    private Label warning;
    @FXML
    private TableView table;
    @FXML
    private TextField id;
    @FXML
    private CheckBox admin;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Label logname;
    @FXML
    private Label role;

    ResultSet rsAllEntries;
    ObservableList<ObservableList> data = FXCollections.observableArrayList();

    public void login(ActionEvent event) {
        if (Validation.isValidUsername(username.getText()) && Validation.isValidPassword(password.getText())) {
            UserDAO userDAO = new UserDAO();
            String msg = userDAO.login(username.getText(), password.getText());
            if (msg.contains("Successful")) {
                User user = userDAO.getUser(username.getText());
                dashboard(event, user);
            } else {
                error.setText(msg);
            }

        } else {
            error.setText("Wrong user name or password!");
        }
        error.setVisible(true);
    }

    public void logOut(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 450, 350));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // button 'X' closes stage(window)
    public void closeWindow(ActionEvent event) {
        if (event.getSource() == close) {
            System.exit(0);
        }
    }

    public void register(ActionEvent event) {
        try {
            // we are in controller folder, but our view is not here, so we need to go one step up - ../
            Parent root = FXMLLoader.load(getClass().getResource("../view/register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root, 450, 350));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerLogin(ActionEvent event) {
        boolean isRegistered = true;

        // clear errors on btn pressed
        regError.setText("");
        if (!Validation.isValidUsername(regUser.getText())) {
            regError.setText("Username is incorrect (letters and numbers only, at least 5 char)");
            isRegistered = false;
        } else if (!Validation.isValidPassword(regPassw.getText())) {
            regError.setText("Password is incorrect (letters and numbers only, at least 5 char)");
            isRegistered = false;
        } else if (!regConfPassw.getText().equals(regPassw.getText())) {
            regError.setText("Password doesn't match");
            isRegistered = false;
        } else if (!Validation.isValidEmail(regEmail.getText())) {
            regError.setText("Email is not correct, pattern- auto@car.eu");
            isRegistered = false;
        }

        if (isRegistered) {
            User user = new User(regUser.getText(), regPassw.getText(), regEmail.getText(),admin.isSelected());
            UserDAO userDAO = new UserDAO();
            String msg = userDAO.register(user);
            if (msg.contains("successfully")) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Login");
                    stage.setScene(new Scene(root, 450, 350));
                    stage.show();
                    // hides current stage (window)
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                regError.setText(msg);
            }
        }
    }

    public void dashboard(ActionEvent event, User user) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root, 1300, 850));

            Label lblLoginName = (Label) root.lookup("#logname");
            Label lblLoginRole = (Label) root.lookup("#role");
            if (lblLoginName != null) lblLoginName.setText(user.getUsername());
            if (lblLoginRole != null) lblLoginRole.setText(user.isAdmin() ? "Admin" : "User");

            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        String make = this.make.getText();
        String model = this.model.getText();

        String feature = "";
        if (spoiler.isSelected()) {
            feature += spoiler.getText() + ",";
        }
        if (autoParki.isSelected()) {
            feature += autoParki.getText() + ",";
        }
        if (sportseats.isSelected()) {
            feature += sportseats.getText() + ",";
        }

        String fuelType = "";
        if (dElectric.isSelected()) {
            fuelType += dElectric.getText();
        } else if (pElectric.isSelected()) {
            fuelType += pElectric.getText();
        } else if (electric.isSelected()) {
            fuelType += electric.getText();
        }
        String door = "";
        if (!comboNum.getSelectionModel().isEmpty()) {
            door = comboNum.getSelectionModel().getSelectedItem().toString();
        } else {
            warning.setText("Please check door number");
        }

        if (!Validation.isValidMake(make)) {
            warning.setText("Make Required");
        } else if (!Validation.isValidModel(model)) {
            warning.setText("Model Required");
        } else {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUser(logname.getText());
            Car car = new Car(make, model, feature, fuelType, Integer.parseInt(door), user.getId());
            CarDAO carDAO = new CarDAO();
            String msg = carDAO.add(car);
            warning.setText(msg);
            updateTableFromDB(""); // get all entries after new entry creation (including new one created)
        }
    }

    public void search() {
        updateTableFromDB(make.getText()); // get entries according team name
    }

    public void updateTableFromDB(String make) {
        CarDAO carDAO = new CarDAO();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(logname.getText());
        try {
            rsAllEntries = carDAO.searchByMake(make, user);
        } catch (NullPointerException e) {
            warning.setText("No rows to display");
        }
        fetchColumnList();
        fetchRowList();
    }

    public void update() {
        if(role.getText().equals("Admin")) {
            String userid = id.getText();
            int teamid = (Integer.parseInt(id.getText()));
            String make1 = make.getText();
            String model1 = model.getText();

            String feature = "";
            if (spoiler.isSelected()) {
                feature += spoiler.getText() + ",";
            }
            if (autoParki.isSelected()) {
                feature += autoParki.getText() + ",";
            }
            if (sportseats.isSelected()) {
                feature += sportseats.getText() + ",";
            }

            String fuelType = "";
            if (dElectric.isSelected()) {
                fuelType += dElectric.getText();
            } else if (pElectric.isSelected()) {
                fuelType += pElectric.getText();
            } else if (electric.isSelected()) {
                fuelType += electric.getText();
            }
            String door = "";
            if (!comboNum.getSelectionModel().isEmpty()) {
                door = comboNum.getSelectionModel().getSelectedItem().toString();
            } else {
                warning.setText("Please check car door number");
            }

            if (!Validation.isValidMake(make1)) {
                warning.setText("Make Required");
            } else if (!Validation.isValidModel(model1)) {
                warning.setText("Model Required");
            } else {
                Car car = new Car(teamid, make1, model1, feature, fuelType, Integer.parseInt(door), Integer.parseInt(userid));
                CarDAO carDAO = new CarDAO();
                carDAO.editById(car);

                updateTableFromDB(""); // get all entries after entry update (including newly updated)
            }
        } else {
            warning.setText("Update feature is only for admins");
        }
    }

    public void delete() {
        if (role.getText().equals("Admin")) {
            CarDAO carDAO = new CarDAO();
            carDAO.deleteById(Integer.parseInt((String) (id.getText())));

            updateTableFromDB(""); // get all entries after entry delete
        } else {
            warning.setText("Delete feature is only for admins");
        }
    }

    //only fetch columns
    private void fetchColumnList() {
        try {
            table.getColumns().clear();

            if (rsAllEntries != null) {
                //SQL FOR SELECTING ALL OF CUSTOMER
                for (int i = 0; i < rsAllEntries.getMetaData().getColumnCount(); i++) {
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rsAllEntries.getMetaData().getColumnName(i + 1).toUpperCase());
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });

                    table.getColumns().removeAll(col);
                    table.getColumns().addAll(col);
                }
            } else {
                warning.setText("No columns to display");
            }
        } catch (SQLException e) {
            warning.setText("Failure in getting all entries");
        }
    }

    //fetches rows and data from the list
    private void fetchRowList() {
        try {
            data.clear();
            if (rsAllEntries != null) {
                while (rsAllEntries.next()) {
                    //Iterate Row
                    ObservableList row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rsAllEntries.getMetaData().getColumnCount(); i++) {
                        //Iterate Column
                        row.add(rsAllEntries.getString(i));
                    }
                    data.add(row);
                }
                //Connects table with list
                table.setItems(data);
            } else {
                warning.setText("No rows to display");
            }
        } catch (SQLException ex) {
            warning.setText("Failure in getting all entries");
        }
    }
}
