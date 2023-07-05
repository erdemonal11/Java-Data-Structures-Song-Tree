
package comp2112_project_2;


public class AvlTree<Item> {

    AvlTreeNode<Item> root;

    public AvlTree() {
        root = null;
    }

    public int height(AvlTreeNode<Item> d) {
        if (d == null) {
            return 0;
        } else {
            return d.height;
        }
    }

    public AvlTreeNode<Item> rotateMyLeft(AvlTreeNode<Item> focus) {
        if (focus == root) {
            root = focus.left;
        }
        AvlTreeNode<Item> k = focus.left;
        focus.left = k.right;
        k.right = focus;
        focus.height = Math.max(height(focus.left), height(focus.right)) + 1;
        k.height = Math.max(height(k.left), height(k.right)) + 1;
        return k;
    }

    public AvlTreeNode<Item> rotateMyRight(AvlTreeNode<Item> focus) {
        if (focus == root) {
            root = focus.right;
        }
        AvlTreeNode<Item> k = focus.right;
        focus.right = k.left;
        k.left = focus;

        focus.height = Math.max(height(focus.left), height(focus.right)) + 1;
        k.height = Math.max(height(k.left), height(k.right)) + 1;
        return k;
    }

    public AvlTreeNode<Item> doubleRotateLeftSide(AvlTreeNode focus) {
        focus.left = rotateMyRight(focus.left);
        return rotateMyLeft(focus);
    }

    public AvlTreeNode<Item> doubleRotateRightSide(AvlTreeNode focus) {
        focus.right = rotateMyLeft(focus.right);
        return rotateMyRight(focus);
    }

    int getBalance(AvlTreeNode<Item> focus) {
        if (focus == null) {
            return 0;
        }
        return height(focus.left) - height(focus.right);
    }

    private AvlTreeNode<Item> insert(AvlTreeNode focus, String data, int key, int index) {
        if (focus == null) {
            focus = new AvlTreeNode(data, key, index);
        } else if (key < focus.key) {
            focus.left = insert(focus.left, data, key, index);
            if (getBalance(focus) == 2) {
                if (key < focus.left.key) {
                    focus = rotateMyLeft(focus);
                } else {
                    focus = doubleRotateLeftSide(focus);
                }
            }
        } else if (key > focus.key) {
            focus.right = insert(focus.right, data, key, index);
            if (getBalance(focus) == -2) {
                if (key > focus.right.key) {
                    focus = rotateMyRight(focus);
                } else {
                    focus = doubleRotateRightSide(focus);
                }
            }
        } else {
            focus.data = data;
        }

        focus.height = Math.max(height(focus.left), height(focus.right)) + 1;
        return focus;
    }

    public void insert(String data, int index) {

        int key = asciiGenerator(data);
        if (root == null) {
            root = insert(root, data, key, index);
        } else {
            insert(root, data, key, index);
        }

    }

    AvlTreeNode minValueNode(AvlTreeNode node) {
        AvlTreeNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    private AvlTreeNode deleteNode(AvlTreeNode focus, int key) {
        if (focus == null) {
            return focus;
        }

        if (key < focus.key) {
            focus.left = deleteNode(focus.left, key);
        } 
        else if (key > focus.key) {
            focus.right = deleteNode(focus.right, key);
        } 
        else {

            if ((focus.left == null) || (focus.right == null)) {
                AvlTreeNode temp = null;
                if (null == focus.left) {
                    temp = focus.right;
                } else { 
                    temp = focus.left;
                }
                if (temp == null) {
                    temp = focus;
                    focus = null;
                } else 
                {
                    focus = temp; 
                }                               
            } else {

                
                AvlTreeNode temp = minValueNode(focus.right);
                
                focus.key = temp.key;

                focus.right = deleteNode(focus.right, temp.key);
                
                return temp;
            }
            
        }

        if (focus == null) {
            return focus;
        }

        focus.height = Math.max(height(focus.left), height(focus.right)) + 1;

        int balance = getBalance(focus);

        if (balance > 1 && getBalance(focus.left) >= 0) {
            return rotateMyLeft(focus);
        }

        if (balance > 1 && getBalance(focus.left) < 0) {
            focus.left = rotateMyRight(focus.left);
            return rotateMyLeft(focus);
        }

        if (balance < -1 && getBalance(focus.right) <= 0) {
            return rotateMyRight(focus);
        }

        if (balance < -1 && getBalance(focus.right) > 0) {
            focus.right = rotateMyLeft(focus.right);
            return rotateMyRight(focus);
        }

        return focus;
    }

    public int asciiGenerator(String userData) {
        String userDataString = (String) userData;
        char first = userDataString.charAt(0);
        char second = userDataString.charAt(1);
        char third = userDataString.charAt(2);
        int asciiFirst = first;
        int asciiSecond = second;
        int asciiThird = third;

        return asciiFirst * asciiSecond * asciiThird * 7;

    }

    public int delete(String data, String dataType) {
        int index = -1;
        if (dataType.equals("ID")) {
            index = firstSearchMethod(data);
            deleteNode(root, asciiGenerator(data));
            return index;
        } else if (dataType.equals("SongName")) {
            index = firstSearchMethod(data);
            deleteNode(root, asciiGenerator(data));
            return index;
        } else if (dataType.equals("Artist")) {
            index = firstSearchMethod(data);
            deleteNode(root, asciiGenerator(data));
            return index;
        } else {
            return -1;
        }
    }

    public int firstSearchMethod(String data) {
        AvlTreeNode focus = root;
        int dataKey = asciiGenerator(data);

        if (focus == null) {
            return -1;
        }

        while (focus != null) {
            if (focus.key > dataKey) {
                focus = focus.left;
            } else if (focus.key < dataKey) {
                focus = focus.right;
            } else if (focus.key == dataKey) {
                return focus.index;

            }
        }

        return -1;
    }

    public void thirdSearchMethod(String dataLowerBound, String dataUpperBound, SongList list) {
        int lower = Integer.valueOf(dataLowerBound);
        int upper = Integer.valueOf(dataUpperBound);
        for (int i = lower; i <= upper; i++) {
            if (firstSearchMethod(String.valueOf(i)) != -1) {
                System.out.println(list.songlist[firstSearchMethod(String.valueOf(i))].toString());
            } else {
                
            }
        }

    }

    private void traverseInOrder(AvlTreeNode focus) {
        if (focus.left != null) {
            traverseInOrder(focus.left);
        }
        System.out.println(focus.data);
        if (focus.right != null) {
            traverseInOrder(focus.right);
        }
    }
    
    public void traverseInOrder(){
        traverseInOrder(root);
    }

}
