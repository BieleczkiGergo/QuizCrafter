import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FillInTaskTest {

    @Test
    public void testFlattening(){
        int fieldNum = 4;
        Document doc = DocumentCreator.createTemplateDocument(7, 7);

        FillInTask task = new FillInTask(doc, 4, 7);

        assertNotEquals(0, task.size);

        System.out.println("From testFlattening:");
        System.out.println(Arrays.toString(task.getFields()));
        System.out.println(Arrays.toString(task.getCorrectAnswers()));
    }

    public String makeFillInAnsver(){
        int max = 6;
        return ("val" + (int) Math.floor(Math.random()*max)) + (int) Math.floor(Math.random()*max);

    }

    @Test
    public void testGrading(){
        Document doc = new Document();
        //Filling the document with data
        {
            final int chains = 1 + (int) Math.floor(Math.random()*20);
            final int chainSize = 1 + (int) Math.floor(Math.random()*20);

            System.out.println("chains: " + chains + " ; size of chains: " + chainSize);

            SortTaskTest.doc = new Document();


            for (int i = 0; i < chains; i++) {
                InfoChain chain = new InfoChain("chain" + i);
                for (int j = 0; j < chainSize; j++) {
                    chain.addInfo(new InfoPair("key" + i + ";" + j, "val" + i + ";" + j));

                }
                doc.addChain(chain);

            }
        }

        //Creating the task
        final int n = 4;    //Number of test cases

        FillInTask task = new FillInTask(doc, n);

        //Testing with multiple data
        final int testIterations = 3;
        for (int i = 0; i < testIterations; i++) {
            System.out.println("iteration: " + i);
            int correct = 0;
            for (int j = 0; j < n; j++) {
                String testAnsver = this.makeFillInAnsver();
                System.out.println(testAnsver);
                task.answer(j, testAnsver);

                if(testAnsver.equals(task.getCorrectAnswers()[j])) {
                    System.out.println("Ansver was correct: " + task.getCorrectAnswers()[j]);
                    correct++;
                }else
                    System.out.println("Ansver was incorrect: " + task.getCorrectAnswers()[j]);

            }
            double grading = task.grade();
            assertEquals(correct/n, grading);

        }

    }
}