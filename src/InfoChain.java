import java.util.LinkedList;
import java.util.Iterator;

public class InfoChain {
  
  private String name;
  private LinkedList<InfoPair> chain;

  public InfoChain(String name){
    this.name = name;
    chain = new LinkedList<InfoPair>();

  }

  public void addInfo(InfoPair info){
    this.chain.add(info);

  }

  public InfoPair getInfo(int index){
    return this.chain.get(index);
  }

  public int size(){
    return this.chain.size();

  }

  @Override
  public String toString(){
    String out = this.name + "\n";
    Iterator it = this.chain.iterator();
    while(it.hasNext()){
      out += it.next().toString() + "\n";

    }
    return out;

  }
}
