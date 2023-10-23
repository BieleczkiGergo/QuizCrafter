public class DocumentCreator {

    public static Document createTemplateDocument(){
        final int chains = 1 + (int) Math.floor(Math.random()*20);
        final int chainSize = 1 + (int) Math.floor(Math.random()*20);
        return createTemplateDocument(chains, chainSize);
    }

    public static Document createTemplateDocument(int chains, int chainSize){
        Document doc = new Document();

        SortTaskTest.doc = new Document();


        for (int i = 0; i < chains; i++) {
            InfoChain chain = new InfoChain("chain" + i);
            for (int j = 0; j < chainSize; j++) {
                chain.addInfo(new InfoPair("key" + i + ";" + j, "val" + i + ";" + j));

            }
            doc.addChain(chain);

        }

        return doc;
    }
}
