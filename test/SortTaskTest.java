import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SortTaskTest {

    static Document doc;
    static String[] infoKeys;
    static String[] infoVals;
    static final int stSize = 5;
    static final int stSkip = 4;

    @BeforeAll
    public static void initDocument(){
        final int chains = 1 + (int) Math.floor(Math.random()*20);
        final int chainSize = 1 + (int) Math.floor(Math.random()*20);

        SortTaskTest.doc = new Document();

        infoKeys = new String[chains*chainSize];
        infoVals = new String[chains*chainSize];

        for (int i = 0; i < chains; i++) {
            InfoChain chain = new InfoChain("chain" + i);
            for (int j = 0; j < chainSize; j++) {
                chain.addInfo(new InfoPair("key" + i + ";" + j, "val" + i + ";" + j));
                infoKeys[i*chainSize + j] = "key" + i + ";" + j;
                infoVals[i*chainSize + j] = "val" + i + ";" + j;

            }
            doc.addChain(chain);

        }

        System.out.println(Arrays.toString(infoKeys));
        System.out.println(Arrays.toString(infoVals));
    }

    @Test
    public void buildTest(){
        SortTask st = new SortTask(SortTaskTest.doc, stSize, stSkip);
        Assertions.assertEquals(stSize, st.size);

        ExtraAsserts.assertSetContains(infoKeys, st.getFields());
        ExtraAsserts.assertSetContains(infoVals, st.getLabels());
        ExtraAsserts.assertSetContains(new int[]{0, 1, 2, 3, 4, 5}, st.getCorrectAnsvers());
    }

    @Test
    public void testGrading(){
        SortTask st = new SortTask(SortTaskTest.doc, stSize, stSkip);

        //Test with random data
        final int answerTimes = 200;
        for (int i = 0; i < answerTimes; i++) {
            int correct = 0;
            int[] testAnswer = new int[stSize];

            //Make test data (in testAnswer)
            {
                for (int j = 0; j < stSize; j++)
                    testAnswer[j] = j;

                //swap values of
                final int swaptimes = 7;
                for (int j = 0; j < swaptimes; j++) {
                    int swapi = (int) Math.floor(Math.random() * stSize);
                    int swapj = (int) Math.floor(Math.random() * stSize);
                    int helper = testAnswer[swapi];
                    testAnswer[swapi] = testAnswer[swapj];
                    testAnswer[swapj] = helper;

                }

            }

            for (int j = 0; j < stSize; j++)
                st.answer(j, testAnswer[j]);

            for (int j = 0; j < stSize; j++)
                if(st.getCorrectAnsvers()[j] == testAnswer[j])
                    correct++;

            Assertions.assertEquals((double) correct/stSize, st.grade());

        }

        //Test with correct data
        for (int i = 0; i < stSize; i++)
            st.answer(i, st.getCorrectAnsvers()[i]);
        Assertions.assertEquals((double) 1, st.grade());

    }

}