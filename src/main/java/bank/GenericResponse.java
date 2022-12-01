package bank;
public class GenericResponse<T> {
  private T data;
  public String message;

  public void setData(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }
}
