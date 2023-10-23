import org.jetbrains.annotations.NotNull;

import javax.print.Doc;
import java.util.Scanner;

/**
 * Task where you're expected to fill in the other half of the information given
 * You can then grade yourself with the grade() method, where you will be prompted with your answer and a correct answer
 * to determine whether your answer was right or not
 */
public class FillInTask extends Task{

    private static final int min_fields = 1;
    private static final int max_fields = 3;
    private String[] fields;
    private String[] answers;
    private String[] correctAnswers;
    public final int size;


    /**
     * Makes a random number of fill-in tasks
     * The random number is between min_fields and max_fields
     * @param doc The document to make tasks from
     */
    public FillInTask(@NotNull Document doc){
        this(doc, min_fields + (int) Math.round(Math.random()*(max_fields-min_fields)));
    }

    /**
     * Makes a set amount of fill-in tasks
     * @param doc The document to build the tasks from
     * @param size The number of fields there should be.
     */
    public FillInTask(@NotNull Document doc, int size){
        this(doc, size, 0);
    }

    /** Makes a set amount of fill-in tasks with a certain amount of skip
     * @param doc The document to build tasks from
     * @param size The number of fields there should be
     * @param skip First n elements to skip from the beginning of the document
     */
    public FillInTask(@NotNull Document doc, int size, int skip){
        //Size correction
        this.size = Math.min(size, doc.size());
        skip = Math.min(doc.size() - size, skip);

        this.fields = new String[this.size];
        this.answers = new String[this.size];
        this.correctAnswers = new String[this.size];


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
                this.correctAnswers[doneFields] = info.value;

                j++;
                doneFields++;
            }
            j = 0;
        }


    }

    public String[] getFields(){
        return fields;
    }
    public String[] getCorrectAnswers(){
        return this.correctAnswers;
    }

    public void answer(int index, String answer){
        this.answers[index] = answer;
    }

    @Override
    public double grade() {
        int correct = 0;
        final Scanner input = new Scanner(System.in);

        for (int i = 0; i < this.size; i++) {
            System.out.print("Is this correct? " + this.answers[i] + " == " + this.correctAnswers[i] + " ;y/n ");
            if (input.nextLine().equals("y"))
                correct++;
        }

        System.out.println();
        return (double) correct /this.size;
    }
}
