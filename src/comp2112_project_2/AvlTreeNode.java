
package comp2112_project_2;


public class AvlTreeNode<Item> {

    
    Item data;
    int key; 
    int height;
    int index;
    AvlTreeNode left;
    AvlTreeNode right;

    AvlTreeNode(Item data,int d,int index) {
        this.data=data;
        this.index=index;
        key = d;
        height = 1;
    }

}
