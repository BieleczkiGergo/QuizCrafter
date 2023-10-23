import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    public void addChains(){
        Document doc = new Document();
        InfoChain chain1 = new InfoChain("chain1");
        chain1.addInfo(new InfoPair("key11", "val11"));
        chain1.addInfo(new InfoPair("key12", "val12"));

        InfoChain chain2 = new InfoChain("chain2");
        chain2.addInfo(new InfoPair("key21", "val21"));
        chain2.addInfo(new InfoPair("key22", "val22"));

        doc.addChain(chain1);
        doc.addChain(chain2);
        System.out.println(doc);
    }
}