package com.beerhouse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beers")
public class Beers implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String nome;
	
	@Column(name = "ingredients")
	private String ingredientes;
	
	@Column(name = "alcool")
	private String alcoolica;
	
	@Column(name = "price")
	private Double preco;
	
	@Column(name = "category")
	private String categoria;

	public Beers() {
		super();
	}
	
	public Beers(Long id, String nome, String ingredientes, String alcoolica, Double preco, String categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.ingredientes = ingredientes;
		this.alcoolica = alcoolica;
		this.preco = preco;
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "Beers [id=" + id + ", nome=" + nome + ", ingredientes=" + ingredientes + ", alcoolica=" + alcoolica
				+ ", preco=" + preco + ", categoria=" + categoria + "]";
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getAlcoolica() {
		return alcoolica;
	}

	public void setAlcoolica(String alcoolica) {
		this.alcoolica = alcoolica;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}
	
}
