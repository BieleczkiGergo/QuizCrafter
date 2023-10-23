public class InfoPair {
  
  public String key;
  public String value;

  public InfoPair(String key, String value){
    this.key = key;
    this.value = value;
    
  }

  @Override
  public String toString(){
    return "key: " + this.key + " value: " + this.value;

  }
}
