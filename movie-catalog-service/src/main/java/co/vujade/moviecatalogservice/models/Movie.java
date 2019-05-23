package co.vujade.moviecatalogservice.models;

public class Movie {
	
	private String id;
	private String title;
	private String overview;
	private String imdb_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getImdb_id() {
		return imdb_id;
	}
	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", imdb_id=" + imdb_id + "]";
	}
	

}
