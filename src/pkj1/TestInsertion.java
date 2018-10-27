package pkj1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestInsertion {
	
	public static void main(String[] args) throws FileNotFoundException {
            RedBlackTree r = new RedBlackTree();
            Scanner scan = new Scanner(System.in);
            Scanner input = new Scanner(System.in);
            File file = new File("/home/ahmed/IdeaProjects/Red Black Trees/src/pkj1/dictionary.txt");
            input = new Scanner(file);
            while (input.hasNextLine())
            {
                String item = input.nextLine();
                RedBlackTree.Node Node = new RedBlackTree.Node(item);
                r.insert(Node);
                Node=null;
            }
            input.close();
            while (true) {
                System.out.println("\n1- Add items\n"
                        + "2- Delete items\n"
                        + "3- Check items\n"
                        + "4- Print tree\n");
                System.out.println("Enter your choice : ");
                int choice = scan.nextInt();
                RedBlackTree.Node n;
                String item ;
                switch (choice) {
                    case 1:
                        System.out.println("Enter element to insert");
                        item = scan.next();
                        item += scan.nextLine();
                        if(r.look_up(item)==false){
                            n = new RedBlackTree.Node(item);
                            r.insert(n);
                            System.out.println("--------Tree------\n");
                            r.inOrderPrint();
                            System.out.println("size = " + RedBlackTree.Node.size);
                            System.out.println("MaxDepth = " + r.Max_depth());
                            n = null;
                        }
                        else
                            System.out.println("ERROR: Word already in the dictionary!");
                        break;
                    case 2:
                        System.out.println("Enter element to delete");
                        item = scan.next();
                        item += scan.nextLine();
                        n = new RedBlackTree.Node(item);
                        if (r.delete(n)) {
                            System.out.print(": deleted!");
                            r.inOrderPrint();
                            System.out.println("size = " + RedBlackTree.Node.size);
                            System.out.println("MaxDepth = " + r.Max_depth());
                        } else
                            System.out.println(": doesn't exist");
                        System.out.println("\n--------Tree------\n");
                        n = null;
                        break;
                    case 3:
                        System.out.println("Enter element to Search");
                        item = scan.next();
                        item += scan.nextLine();
                        System.out.println(((r.look_up(item)) == true) ? "Found" : "Not Found");
                        break;
                    case 4:
                        System.out.println("\n--------Tree------\n");
                        r.inOrderPrint();
                        System.out.println("size = " + RedBlackTree.Node.size);
                        System.out.println("MaxDepth = " + r.Max_depth());
                }
                        }
	}
}
