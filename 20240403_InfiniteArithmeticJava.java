import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 20240403_InfiniteArithmeticJava {

    private List<Integer> internalArray = new ArrayList<>();
    private List<Integer> primaryInternalArray = Collections.singletonList(1);

    public 20240403_InfiniteArithmeticJava(Object inputObject) {
        Object objStore = inputObject;

        if (inputObject instanceof Integer) {
            int inputValue = (Integer) inputObject;

            if (inputValue < 0) {
                throw new IllegalArgumentException("Input cannot be negative");
            }

            while (inputValue != 0) {
                internalArray.add(0, inputValue % 10);
                inputValue = inputValue / 10;
            }
        } else if (inputObject instanceof String) {
            String inputValue = (String) inputObject;

            if (inputValue.isEmpty()) {
                throw new IllegalArgumentException("Empty string is not accepted.");
            }

            if (!inputValue.matches("\\d+")) {
                throw new IllegalArgumentException("String can have decimal numbers only.");
            }

            for (int i = 0; i < inputValue.length(); i++) {
                char digitChar = inputValue.charAt(i);
                int digit = Character.getNumericValue(digitChar);
                internalArray.add(digit);
            }
        } else if (inputObject instanceof List<?>) {
            List<?> inputList = (List<?>) inputObject;

            // TODO: Validate individual elements of the inputList and initialize internalArray
            // inputList

            internalArray = (List<Integer>) inputList;
        } else if (inputObject instanceof Object) {
            // TODO: Check if this object has getInternalArray() and make a deep copy
            // and assign it to local internalArray

            internalArray = List.of(4, 5, 6);
        } else {
            throw new IllegalArgumentException("Constructor of 20240403_InfiniteArithmeticJava does not support this data type: " + inputObject.getClass());
        }
    }

    public List<Integer> getInternalArray() {
        return new ArrayList<>(internalArray);
    }

    public String getNumberAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer digit : internalArray) {
            stringBuilder.append(digit);
        }
        return stringBuilder.toString();
    }

    public List<Integer> add() {
        List<Integer> result = new ArrayList<>();
        int carry = 0;

        List<Integer> primary = primaryInternalArray;
        List<Integer> secondary = internalArray;

        int l4 = 0;
        int l1 = primary.size();
        int l2 = secondary.size();
        int l3 = Math.abs(l1 - l2);
        int index = 0;

        if (l1 > l2) {
            while (index < l3) {
                secondary.add(0, 0); // Adding zeros to the secondary array
                index++;
            }
        } else {
            while (index < l3) {
                primary.add(0, 0); // Adding zeros to the primary array
                index++;
            }
        }

        Collections.reverse(primary);
        Collections.reverse(secondary);

        if (l1 > l2) {
            l4 = l1;
        } else {
            l4 = l2;
        }

        for (index = 0; index < l4; index++) {
            int temp = primary.get(index) + secondary.get(index) + carry;
            if (temp >= 10) {
                result.add(temp % 10);
                carry = temp / 10;
            } else {
                result.add(temp % 10);
                carry = 0;
            }
        }

        if (carry > 0) {
            result.add(carry); // Pushing the last carry if it exists
        }

        Collections.reverse(result); // Reversing the result array
        return result;
    }

    public List<Integer> subtract(Object objStore) {
        List<Integer> primary = internalArray;
        List<Integer> secondary = ((20240403_InfiniteArithmeticJava) objStore).getInternalArray();

        List<Integer> result1 = new ArrayList<>();

        List<Integer> tempArray = new ArrayList<>();

        int l4 = 0;
        int l1 = primary.size();
        int l2 = secondary.size();
        int l3 = Math.abs(l1 - l2);
        int index = 0, flag = 0;

        if (l1 < l2) {
            while (index < l3) {
                primary.add(0, 0); // Adding zeros to the primary array
                index++;
            }
        } else {
            while (index < l3) {
                secondary.add(0, 0); // Adding zeros to the secondary array
                index++;
            }
        }

        if (l1 > l2) {
            l4 = l1;
        } else {
            l4 = l2;
        }

        int primaryNum = Integer.parseInt(getNumberAsString()); // Checking the magnitude by joining the arrays
        int secondaryNum = Integer.parseInt(((20240403_InfiniteArithmeticJava) objStore).getNumberAsString()); // Checking the magnitude by joining the arrays

        Collections.reverse(primary);
        Collections.reverse(secondary);

        if (primaryNum < secondaryNum) {
            tempArray = primary;
            primary = secondary;
            secondary = tempArray;
            flag = 1;
        }

        for (index = 0; index < l4; index++) {
            int tempIndex = index;
            if (primary.get(index) < secondary.get(index)) {
                primary.set(index, primary.get(index) + 10);
                result1.add(primary.get(index) - secondary.get(index));
                if (index + 1 == l4) {
                    break;
                } else {
                    if (primary.get(index + 1) == 0) {
                        int temp2 = index + 1;
                        while (primary.get(temp2) == 0) {
                            primary.set(temp2, 9);
                            temp2++;
                        }
                        primary.set(temp2, primary.get(temp2) - 1);
                    } else {
                        primary.set(index + 1, primary.get(index + 1) - 1);
                    }
                }
            } else {
                result1.add(primary.get(index) - secondary.get(index));
            }
            index = tempIndex;
        }

        if (flag == 0) {
            Collections.reverse(result1);
            return result1;
        } else {
            result1.add(-1); // Adding the negative sign if the result is negative
            Collections.reverse(result1);
            return result1;
        }
    }
}
