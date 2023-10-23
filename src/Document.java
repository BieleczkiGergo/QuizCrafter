import java.util.Iterator;
import java.util.LinkedList;

public class Document{
  public LinkedList<InfoChain> chains;

  public Document(){
    this.chains = new LinkedList<InfoChain>();
  }
  
  public void addChain(InfoChain chain){
    chains.add(chain);
  }

  public InfoChain getChain(int index){
    return this.chains.get(index);
  }

  @Override
  public String toString(){
    String out = "";
    Iterator it = this.chains.iterator();
    while (it.hasNext()){
      out += it.next() + "\n";

    }
    return out;
  }

  /**
   *
   * @return - The number of infos (InfoPairs) the document contains
   */
  public int size(){
    int len = 0;
    for(int i=0; i < this.chains.size(); i++){
      len += this.chains.get(i).size();

    }
    return len;
  }
}
