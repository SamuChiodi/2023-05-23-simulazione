package it.polito.tdp.baseball.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


import it.polito.tdp.baseball.db.BaseballDAO;

public class Model {
	
	private BaseballDAO dao;
	private Graph<People, DefaultEdge> grafo;
	
	
	public Model() {
		
		this.dao = new BaseballDAO();
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		
		
	}
	
	public List<People> getVertex(Integer year, double salary){
		
		return this.dao.getVertex(year, salary);
		
	}
	
	
	public Graph<People, DefaultEdge>creaGrafo(Integer year, double salary){
		
		Graphs.addAllVertices(this.grafo, getVertex(year, salary));
		
		for(People p1 : this.grafo.vertexSet()) {
			for(People p2 : this.grafo.vertexSet()) {
				
				List<Integer> list1 = this.dao.getApp(p1.getPlayerID(), year);
				List<Integer> list2 = this.dao.getApp(p2.getPlayerID(), year);
				
				if(p1.getPlayerID()!=p2.getPlayerID()) {
					
					for(Integer id1 : list1 ) {
							if(list2.contains(id1)) {
								Graphs.addEdgeWithVertices(this.grafo, p1, p2);
							}
					}
					
				}
				
			}
		}
			
		
		return grafo;	
		
	}
	
	
	public People getMax() {
		
		People persona  = null;
		int max = 0;
		for(People p : this.grafo.vertexSet()) {
			if(this.grafo.degreeOf(p) > max) {
				max = this.grafo.degreeOf(p);
				persona = p;
			}
		}
		
		return persona;
	}
	
	
	public Integer calcolaConnessa() {
		
		Integer connessa = 0;
		
		ConnectivityInspector<People, DefaultEdge> inspector = new ConnectivityInspector<>(this.grafo);
		
		connessa = inspector.connectedSets().size();
		
		
		return connessa;
	}
	
}