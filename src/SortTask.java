
public class SortTask extends Task{

    private String[] fields;     //Key values from InfoPairs
    private String[] labels;    // Value values from InfoPairs
    private int[] correctAnsvers;
    private int[] ansvers;

    public final int size;

    private static final int min_fields = 2;
    private static final int max_fields = 6;

    public SortTask(Document doc){
        this(doc, min_fields + (int) Math.round(Math.random()*(max_fields-min_fields)));
    }
    public SortTask(Document doc, int size){
        this(doc, size, 0);
    }

    public SortTask(Document doc, int size, int skip){
        this.size = Math.min(size, doc.size());
        // initialize arrays
        {
            this.fields = new String[this.size];
            this.ansvers = new int[this.size];
            this.correctAnsvers = new int[this.size];
            this.labels = new String[this.size];
        }

        //Skip first "skip" elements
        int i = 0;
        int j =0;
        while(skip > 0){
            InfoChain chain = doc.getChain(i);
            if(chain.size() < skip){
                skip -= chain.size();
                i++;
            }else{
                j = skip;
                skip = 0;
            }
        }

        //Get next "size" elements
        int doneFields = 0;
        for (; doneFields < size; i++) {
            InfoChain chain = doc.getChain(i);
            while(j < chain.size() && doneFields < size){
                InfoPair info = chain.getInfo(j);

                this.fields[doneFields] = info.key;
                this.labels[doneFields] = info.value;
                this.correctAnsvers[doneFields] = doneFields;

                j++;
                doneFields++;
            }
            j = 0;
        }

        //Swapping values
        final int swapTimes = 7;
        for (i = 0; i < swapTimes; i++) {
            int s1 = (int) Math.floor(Math.random()*this.size);
            int s2 = (int) Math.floor(Math.random()*this.size);

            String task = this.fields[s1];
            String label = this.labels[s1];
            int correctAnsver = this.correctAnsvers[s1];

            this.fields[s1] = this.fields[s2];
            this.labels[s1] = this.labels[s2];
            this.correctAnsvers[s1] = this.correctAnsvers[s2];

            this.fields[s2] = task;
            this.labels[s2] = label;
            this.correctAnsvers[s2] = correctAnsver;

        }
    }

    @Override
    public double grade() {
        int correct = 0;
        for (int i = 0; i < this.size; i++)
            if(this.ansvers[i] == this.correctAnsvers[i])
                correct++;

        return (double) correct / this.size;
    }


    /**
     * @param index The index of the ansver field to set
     * @param position The value to set the ansver field to
     */
    public void answer(int index, int position){
        this.ansvers[index] = position;

    }

    public String[] getFields() {
        return fields;
    }


    public String[] getLabels() {
        return labels;
    }

    public int[] getCorrectAnsvers() {
        return correctAnsvers;
    }

    public int[] getAnsvers() {
        return ansvers;
    }

}
