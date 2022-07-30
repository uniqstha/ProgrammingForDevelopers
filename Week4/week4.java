// In a certain city there are x number of villages represented in an array where a[i]
// represents their grade. Each village is assigned grades based on their population count.
// you are assigned task to distribute
// wheat container to each village. Write an algorithm to return the minimum number of
// containers required to distribute wheat to each village such that each village must
// get at least one container and villages with higher grade must receive more container;
// input: grades = [5,2,6]
// output:6
// explain you can distribute first 2nd village 1 container and 1st village 2 container and
// third village 3 container

package Week4;
public class week4 {
    // Input Array sort function
    private static void arraySort(int array[]) {
        // Traversing through array
        for (int i = 1; i < array.length; i++) {
            int j = i;
            int a = array[i];

            // comparing the current element with the previous element
            while ((j > 0) && (array[j - 1] > a)) {
                array[j] = array[j - 1]; // swapping the current element with the previous element
                j--; // decreasing j by -1
            }
            array[j] = a; // assigning the current element to the index where the previous element was
                          // swapped
        }
    }

    public static void totalContainers(int array[]) {
        // sorting the array
        arraySort(array);

        int prevValue = 0; // storing element before the current element
        int containerForCurrentVillage = 0; // number of containers used for the current village
        int totalContainers = 0; // variable to store total number of containers

        // looping through the elements of the sorted array
        for (int i = 0; i < array.length; i++) {
            // execute if statement if the current element is not same as the previous
            // element
            if (array[i] != prevValue) {
                prevValue = array[i];
                containerForCurrentVillage += 1;
                totalContainers += containerForCurrentVillage;
            }
            // execute else statement if the curret element is same as the previous element
            else {
                prevValue = array[i];
                totalContainers += containerForCurrentVillage;
            }
        }

        System.out.println(totalContainers);
    }

    public static void main(String[] args) {
        // initializing an array
        int array[] = { 5, 2, 6 };

        // calling the function to find total number of containers
        totalContainers(array);
    }
}