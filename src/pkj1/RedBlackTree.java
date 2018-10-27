package pkj1;

import java.util.ArrayList;

//import pkj1.Node.NoneColorException;

public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public static class Node {

        private String value;
        private Node right;
        private Node left;
        private Node parent;
        private boolean color;
        public static int size=0;

        public Node(String value, Node right, Node left) {
            this.value = value;
            this.color = BLACK;
            this.left = None;
            this.right = None;
            this.parent = None;
            //size+=1;
        }

        public Node(String value) {
            this(value, None, None);
        }

        public boolean getColor() {
            return color;
        }

        public String getValue() {
            return value;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public void setValue(String value) {
            this.value = value;

        }


    }


    private Node parent;
    private final static Node None = new Node("-1");
    public Node root = None;


    public RedBlackTree() {
    }

    public void insert(Node node) {
        Node temp = root;
        if (root == None) {
            root = node;
            node.color = BLACK;
            node.parent = None;
        } else {
            node.color = RED;
            while (true) {
                if (node.value.toUpperCase().compareTo(temp.value.toUpperCase()) < 0) {
                    if (temp.left == None) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else temp = temp.left;
                } else if (node.value.toUpperCase().compareTo(temp.value.toUpperCase()) > 0 || node.value.compareTo(temp.value.toUpperCase()) == 0) {
                    if (temp.right == None) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else temp = temp.right;
                }
            }
            fix(node);
        }
        Node.size+=1;
    }

    public void fix(Node node) {
        while (node.parent.color == RED) {
            Node uncle = None;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;
                if (uncle != None && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } else if (node == node.parent.right) {
                    node = node.parent;
                    rotateLeft(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateRight(node.parent.parent);
            } else {
                uncle = node.parent.parent.left;
                if (uncle != None && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } else if (node == node.parent.left) {

                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    public void rotateRight(Node node) {
        if (node.parent != None) {
            if (node == node.parent.left) {
                node.parent.left = node.left;

            } else node.parent.right = node.left;
            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != None) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = None;
            root = left;
        }
    }


    public void rotateLeft(Node node) {
        if (node.parent != None) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != None) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;

        } else {
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = None;
            root = right;
        }
    }

    public boolean isRed(Node node) {
        if (node == None) return false;
        else return node.color == RED;
    }

    public boolean look_up(String value)
    {
        if(Search(root,value)!=None)
            return true;
        else
            return false;
    }

    public Node Search(Node sub,String value) {

        if (sub == None || sub.value.equals(value) )
            return sub;
        if (sub.value.compareToIgnoreCase(value) > 0)
            return Search(sub.left, value);

        return Search(sub.right, value);
    }


   // public void Insert(int value) {
     //   root = InsertRec(root, value);
    //}


    public Node InsertRec(Node root, String value) {
        if (root == None) {
            return root = new Node(value);
        }

        if (root.getValue().compareTo(value.toUpperCase()) > 0)
            root.left = InsertRec(root.left, value);
        else if (root.getValue().compareTo(value.toUpperCase()) < 0)
            root.right = InsertRec(root.right, value);

        return root;

    }

    public void inOrderPrint() {
        inOrderRec(root);
    }

    public void inOrderRec(Node node) {
        if (node != None) {
            inOrderRec(node.left);
            System.out.print(((node.color==RED)?"Color: Red ":"Color: Black ")+"Value: "+node.getValue()+'\n');
            //System.out.println(node.getValue());
            //System.out.println(root.getValue());
            inOrderRec(node.right);
        }
    }

    public boolean isEmpty() {
        return root == None;
    }
    private Node findNode(Node findNode, Node node) {
        if (root == None) {
            return null;
        }

        if (findNode.value.compareToIgnoreCase(node.value) < 0) {
            if (node.left != None) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.value.compareToIgnoreCase(node.value) > 0) {
            if (node.right != None) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.value.compareToIgnoreCase(node.value) == 0) {
            return node;
        }
        return null;
    }

    void references(Node target, Node with){
        if(target.parent == None){
            root = with;
        }else if(target == target.parent.left){
            target.parent.left = with;
        }else
            target.parent.right = with;
        with.parent = target.parent;
    }

    boolean delete(Node z){
        if((z = findNode(z, root)) == null)return false;
        Node x;
        Node y = z;
        boolean y_original_color = y.color;

        if(z.left == None){
            x = z.right;
            references(z, z.right);
        }else if(z.right == None){
            x = z.left;
            references(z, z.left);
        }else{
            y = treeMinimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent == z)
                x.parent = y;
            else{
                references(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            references(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if(y_original_color == BLACK)
            deleteFix(x);
        Node.size-=1;
        return true;
    }

    void deleteFix(Node x){
        while(x!=root && x.color == BLACK){
            if(x == x.parent.left){
                Node w = x.parent.right;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == BLACK && w.right.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == BLACK){
                    w.left.color = BLACK;
                    w.color = RED;
                    rotateRight(w);
                    w = x.parent.right;
                }
                if(w.right.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
                Node w = x.parent.left;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == BLACK && w.left.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    Node treeMinimum(Node subTreeRoot){
        while(subTreeRoot.left!=None){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }

    int Max_depth()
    {
       int max = Max_height(root);
        return max;
    }


    int Max_height(Node sub)
    {
        if(sub==None) return 0;
        else
        {
           int left_depth = Max_height(sub.left);
           int right_depth = Max_height(sub.right);
           if(left_depth>right_depth)
               return (left_depth+1);
           else
               return (right_depth+1);
        }

    }
}
