package com.springboot.bean;

public class Movie {
	private Integer id;
	private String MovieName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMovieName() {
		return MovieName;
	}
	public void setMovieName(String movieName) {
		MovieName = movieName;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", MovieName=" + MovieName + "]";
	}
	
}
