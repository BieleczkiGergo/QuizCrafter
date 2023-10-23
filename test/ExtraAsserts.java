import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

public class ExtraAsserts {

    /** Asserts whether full contains all elements in sub.
     *  sub is not to be thought of as a subarray of full. The elements are individually checked.
     * @param full The array to search
     * @param sub Elements to look up in the array above
     */
    public static void assertSetContains(@NotNull String[] full, @NotNull String[] sub){
        for (var current: sub)
            ExtraAsserts.asserSetContains(full, current);

    }

    /** Asserts whether full contains all elements in sub.
     *  sub is not to be thought of as a subarray of full. The elements are individually checked.
     * @param full The array to search
     * @param sub Elements to look up in the array above
     */
    public static void assertSetContains(@NotNull int[] full, @NotNull int[] sub){
        for (var current: sub)
            assertSetContains(full, current);

    }


    /** Asserts whether the given element is inside the array
     * @param full The array to search
     * @param sub The element to look up
     */
    public static void asserSetContains(@NotNull String[] full, String sub){
        boolean present = false;
        for (var current : full)
            if(current.equals(sub)){
                present = true;
                break;
            }
        Assertions.assertTrue(present);
    }

    /** Asserts whether the given element is inside the array
     * @param full The array to search
     * @param sub The element to look up
     */
    public static void assertSetContains(@NotNull int[] full, int sub){
        boolean present = false;
        for (var current : full)
            if(sub == current) {
                present = true;
                break;
            }

        Assertions.assertTrue(present);
    }
}
