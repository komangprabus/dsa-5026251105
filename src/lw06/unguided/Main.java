import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree tree = new BinarySearchTree();

        String[] values = read.nextLine().trim().split("\\s+");
        for (String v : values){
            tree.insert(Integer.parseInt(v));
        }
        int target = Integer.parseInt(read.nextLine().trim());

        System.out.print("Preorder : ");
        tree.preOrder();
        System.out.print("Inorder : ");
        tree.inOrder();
        System.out.print("Postorder : ");
        tree.postOrder();
        System.out.println("Search " + target + ": " + tree.search(target));
        System.out.println("Nodes : " + tree.countNodes());
        System.out.println("Leaves : " + tree.countLeaves());

        read.close();
    }
}