
public class Feedbacks {
  private String autor;
  private String comentario;
  private String data;
  public Feedbacks(String autor, String comentario, String data) {
      this.autor = autor;
      this.comentario = comentario;
      this.data = data;
  }
  
  public String getAutor() {
      return autor;
  }
  
  public String getComentario() {
      return comentario;
  }
  
  @Override
  public String toString() {
      return String.format("[%s] %s: %s", data, autor, comentario);
  }
}