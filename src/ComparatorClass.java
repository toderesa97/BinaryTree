public class ComparatorClass {

    public static void main(String[] args) {
        int[] arr = {-1,1,2,-5,0,4,8,2};
        BinarySearch binarySearch = new BinarySearch();
        for (int i = 0; i < arr.length; i++) {
            binarySearch.add(arr[i]);
        }

        System.out.println(binarySearch);
        BinarySearch.Node root = binarySearch.getRoot();

        binarySearch.erase(0);
        System.out.println(binarySearch);
    }

    private static void beginTreeTest(int[] arr) {
        System.out.println("----------------TREE TESTS----------------");
        BinarySearch binarySearch = new BinarySearch();
        long init = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            binarySearch.add(arr[i]);
        }
        long end = System.nanoTime();
        System.out.println("Total elapsed time inserting (Tree): "+
                (end-init) +" [ns]\n Per insertion: "+(end-init)/arr.length+" [ns]");
        init = System.nanoTime();
        for (int i = 0; i < 5000; i++) {
            binarySearch.search(-2);
        }
        end = System.nanoTime();
        System.out.println("Worst case of searching (5555) : "+(end-init)/5000);

        System.out.println(binarySearch);
    }

    private static void beginLinkedListTest(int[] arr) {
        System.out.println("----------------LINKEDLIST TESTS----------------");

        NodeStructure nodeStructure = new NodeStructure();
        long init = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            nodeStructure.add(arr[i]);

        }
        long end = System.nanoTime();
        System.out.println("Total elapsed time inserting (LinkedList): "+
                (end-init) +" [ns]\n Per insertion: "+(end-init)/arr.length+" [ns]");
        init = System.nanoTime();
        for (int i = 0; i < 5000; i++) {
            nodeStructure.contains(-2);
        }
        end = System.nanoTime();
        System.out.println("Worst case of searching (5555) : "+(end-init)/5000);



    }


}
