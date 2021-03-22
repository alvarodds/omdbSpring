package com.entregable.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.entregable.models.component.PeliculaComp;



	
	@Component
	@SessionScope
	@Entity
	@Table(name = "peliculas")
	public class Pelicula {
		
		@Id
		@Column(name = "id")
		private String imdbID;
		
		@Column(name = "titulo")
		private String Title;
		
		@Column(name = "director")
		private String Director;
		
		@Column(name = "anio")
		private String Year;
		
		@Column(name = "duracion")
		private String Runtime;
	 
		@Column(name = "poster")
		private String Poster;
		
		@Column(name = "rating")
		private String imdbRating;
		
		@Column(name = "votes")
		private String imdbVotes;
		
		public Pelicula() {
			super();
		}

		public String getTitle() {
			return Title;
		}

		public void setTitle(String title) {
			Title = title;
		}

		public String getDirector() {
			return Director;
		}

		public void setDirector(String director) {
			Director = director;
		}

		public String getYear() {
			return Year;
		}

		public void setYear(String year) {
			Year = year;
		}

		public String getRuntime() {
			return Runtime;
		}

		public void setRuntime(String runtime) {
			Runtime = runtime;
		}

		public String getImdbId() {
			return imdbID;
		}

		public void setImdbId(String imdbId) {
			imdbId = imdbId;
		}

		public String getPoster() {
			return Poster;
		}

		public void setPoster(String poster) {
			Poster = poster;
		}
		
		public String getimdbRating() {
			return imdbRating;
		}

		public void setimdbRating(String rating) {
			imdbRating = rating;
		}

		public String getImdbVotes() {
			return imdbVotes;
		}

		public void setImdbVotes(String imdbVotes) {
			this.imdbVotes = imdbVotes;
		}

		public Pelicula(String title, String director, String year, String runtime, String imdbId, String poster, String rating, String votes) {
			super();
			Title = title;
			Director = director;
			Year = year;
			Runtime = runtime;
			imdbID = imdbId;
			Poster = poster;
			imdbRating = rating;
			imdbVotes = votes;
		}
		
		public void copia(PeliculaComp p2) {
			this.Director=p2.getDirector();
			this.imdbID=p2.getImdbId();
			this.Poster=p2.getPoster();
			this.Runtime=p2.getRuntime();
			this.Title=p2.getTitle();
			this.Year=p2.getYear();
			this.imdbRating = p2.getRating();
			this.imdbVotes = p2.getVotes();
		}

		@Override
		public String toString() {
			return "Pelicula [imdbID=" + imdbID + ", Title=" + Title + ", Director=" + Director + ", Year=" + Year
					+ ", Runtime=" + Runtime + ", Poster=" + Poster + "]";
		}

		
		
		
	  


}
