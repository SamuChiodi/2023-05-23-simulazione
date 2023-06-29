package it.polito.tdp.baseball;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.baseball.model.Model;
import it.polito.tdp.baseball.model.People;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConnesse;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnDreamTeam;

    @FXML
    private Button btnGradoMassimo;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtYear;

    
    private Graph<People, DefaultEdge> grafo;
    
    
    @FXML
    void doCalcolaConnesse(ActionEvent event) {
    	
    	this.txtResult.appendText("\nCi sono "+this.model.calcolaConnessa()+" componenti connesse");
    	
    }

    private List<Integer> anni ;
    
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	this.anni = new ArrayList<Integer>();
    	
    	for(int i=1871; i<=2019; i++) {
    		anni.add(i);
    	}
    	
    	
    	
    	if(anni.contains(Integer.parseInt(txtYear.getText()))) {
    		
    		Integer anno = Integer.parseInt(txtYear.getText());
    		double salary = Double.parseDouble(txtSalary.getText())*1000000;
    		
    		this.grafo = this.model.creaGrafo(anno, salary);
    		
    		this.txtResult.setText("Vertici: " +this.grafo.vertexSet().size()+"\n" +"Archi: "+this.grafo.edgeSet().size());
    		
    		this.btnGradoMassimo.setDisable(false);
    		this.btnConnesse.setDisable(false);
    		
    		
    	}else {	this.txtResult.setText("Anno inesistente");}
    	
    	
    	
    	
    }

    
    @FXML
    void doDreamTeam(ActionEvent event) {

    }

    
    @FXML
    void doGradoMassimo(ActionEvent event) {

    	this.txtResult.appendText("\nPersona grado massimo: " + this.model.getMax().toString());
    	
    }

    
    @FXML
    void initialize() {
        assert btnConnesse != null : "fx:id=\"btnConnesse\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDreamTeam != null : "fx:id=\"btnDreamTeam\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGradoMassimo != null : "fx:id=\"btnGradoMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSalary != null : "fx:id=\"txtSalary\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYear != null : "fx:id=\"txtYear\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    }

}
