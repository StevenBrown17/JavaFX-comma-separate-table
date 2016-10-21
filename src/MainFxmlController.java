


import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class MainFxmlController implements Initializable {


	
	String output="";
@FXML public TableView<tableClass> table;
@FXML private TableColumn<tableClass, String>col1;
@SuppressWarnings({ "unchecked", "rawtypes" })
@FXML public TableColumn<tableClass, TextField> col2;
@FXML public TextField inputField,outputField;
@FXML public Button btn, btn2;
public static ArrayList<String> input = new ArrayList<String>();
public static Group hb = new Group();
public ObservableList<tableClass> obList = FXCollections.observableArrayList();  // each column contains an observable list object
public ObservableList<tableClass> loadTable(){
    return obList;   //return data object
}////END loadData


@Override
public void initialize(URL url, ResourceBundle rb) {
    table.setEditable(true);
    col1.setCellValueFactory(cellData -> cellData.getValue().col1Property());


    col2.setCellValueFactory(new PropertyValueFactory<>("col2"));

    table.setItems(loadTable());

    col1.setCellFactory(TextFieldTableCell.<tableClass>forTableColumn()); //Makes the columns themselves editable
    col1.setOnEditCommit(
            new EventHandler<CellEditEvent<tableClass, String>>() {
                @Override
                public void handle(CellEditEvent<tableClass, String> t) {
                    ((tableClass) t.getTableView().getItems().get( t.getTablePosition().getRow())).setCol1(t.getNewValue());
                }
            }
    );
    col1.setStyle( "-fx-alignment: BOTTOM-RIGHT;"); //to alight text next to textArea

    inputField.setText("fsad,0,0,gfds,43,4,4,fdsg,rtewrtwe,0,67,3,4,4,,4,44,,4");  //TO BE ROMOVED UPON COMPLETION

}//end initialize

public void buttonAction(ActionEvent e){
    if(inputField.getText() != ""){
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(inputField.getText().split(",")));
        input = myList;
    }

    for(int i =0; i< input.size(); i++){
        obList.add(new tableClass(input.get(i),input.get(i)));
    }//end for

}//end buttonAction


public void captureText(ActionEvent e) {
    obList.forEach(event -> {
        event.setCol1(event.getCol2().getText());        
    });
  
    for(int i=0; i<table.getItems().size();i++)
    	output=output + obList.get(i).col2.getText() +",";
    
    outputField.setText(output);
    
}


public static class tableClass{              ///table object with getters and setters.
    public final SimpleStringProperty col1;
    public final TextField col2;

    public tableClass(String col1, String col2) {   //uses an enum for the second type
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new TextField(col2);
    }

    public StringProperty col1Property() {
        return col1;
    }
    public String getCol1(){
        return col1.get();
    }

    public void setCol1(String i) {
        col1.set(i);
    }

    public void setCol2(String tx) {
        col2.setText(tx);
    }

    public TextField getCol2() {
        return col2;
    }

}//end table class


}//end controller