import org.jetbrains.annotations.NotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    public static void main(String[] args){
        Document doc = new Document();
        //Filling the document with data
        {
            InfoChain chain1 = new InfoChain("chain1");
            InfoChain chain2 = new InfoChain("chain2");
            InfoChain chain3 = new InfoChain("chain3");

            chain1.addInfo(new InfoPair("key11", "val11"));
            chain1.addInfo(new InfoPair("key12", "val12"));
            chain2.addInfo(new InfoPair("key21", "val21"));
            chain2.addInfo(new InfoPair("key22", "val22"));
            chain3.addInfo(new InfoPair("key31", "val31"));
            chain3.addInfo(new InfoPair("key32", "val32"));

            doc.addChain(chain1);
            doc.addChain(chain2);
            doc.addChain(chain3);
        }

        final int testCases = 4;    //Number of test cases in an iteration
        final int testIterations = 10;

        //Creating the task
        FillInTask task = new FillInTask(doc, testCases);

        for (int i = 0; i < testIterations; i++) {
            System.out.println("--------------------\niteration: " + i);
            int correct = 0;
            for (int j = 0; j < testCases; j++) {
                String testAnsver = makeFillInAnsver();
                task.answer(j, testAnsver);

                if(testAnsver.equals(task.getCorrectAnswers()[j]))
                    correct++;

            }
            double grading = task.grade();
            System.out.println("Generated: " + ((double) correct/testCases) + " manual: " + grading);

        }


    }

    public static @NotNull String makeFillInAnsver(){
        int max = 6;
        return ("val" + (int) Math.floor(Math.random()*max)) + (int) Math.floor(Math.random()*max);

    }
}