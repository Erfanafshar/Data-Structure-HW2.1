import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int numberOfOrders;
        int newLikeAmount;
        int numberOfLikes = 0;
        String order;
        Scanner scanner = new Scanner(System.in);
        numberOfOrders = Integer.valueOf(scanner.nextLine());
        BST numbers = new BST();

        for (int i = 0; i < numberOfOrders; i++) {

            order = scanner.nextLine();

            if (order.startsWith("1")) {
                newLikeAmount = Integer.valueOf(order.substring(2, order.length()));
                numbers.root = numbers.add(numbers.root, newLikeAmount);
                numberOfLikes++;
            }

            if (order.startsWith("2")) {
                if (numberOfLikes < 3) {
                    System.out.println("No reviews yet");
                } else {
                    numbers.printRes(numbers.root, numberOfLikes);
                }
            }
        }
    }
}

class BST {

    public Node root;

    class Node {

        int key;
        Node left, right;
        int numberOfRightChilds = 0;
        int numberOfLeftChilds = 0;

        public Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }

    public BST() {
        root = null;
    }

    public Node add(Node root, int key) {

        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key <= root.key) {
            root.numberOfLeftChilds++;
            root.left = add(root.left, key);
        }
        if (key > root.key) {
            root.numberOfRightChilds++;
            root.right = add(root.right, key);
        }
        return root;
    }

    public void printRes(Node rot, int numberOfLikes) {
        int num = numberOfLikes / 3;
        while (true) {
            if (num == rot.numberOfRightChilds) {
                rot = rot.right;
                while (!(rot.left == null)) {
                    rot = rot.left;
                }
                System.out.println(rot.key);
                return;
            }
            if (num > rot.numberOfRightChilds) {
                if (num == rot.numberOfRightChilds + 1) {
                    System.out.println(rot.key);
                    return;
                } else {
                    num = num - rot.numberOfRightChilds - 1;
                    rot = rot.left;
                }
            }
            if (num < rot.numberOfRightChilds) {
                rot = rot.right;
            }
        }
    }
}



