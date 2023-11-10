package DSAA.lab6;
/*
前序遍历：
1.先输出当前节点（初始为root节点）
2.如果当前节点的左子节点不为空，则递归继续前序遍历
3.如果当前节点的右子节点不为空，则递归继续前序遍历
中序遍历：
1.如果当前节点的左子节点不为空，则递归继续中序遍历
2.输出当前节点
3.如果当前节点的右子节点不为空，则递归继续中序遍历
后序遍历：
1.如果当前节点的左子节点不为空，则递归继续后序遍历
2.如果当前节点的右子序列不为空，则递归继续后续遍历
3.输出当前节点
 */
public class binaryTreeDemo {
    public static void main(String[] args) {
        //先创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);


        binaryTree.setRoot(root);

        System.out.println("前序遍历:" );
        binaryTree.preOrder();
        System.out.println("中序遍历:" );
        binaryTree.infixOrder();
        System.out.println("后序遍历:" );
        binaryTree.postOrder();
    }
}

//先创建节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    public HeroNode(int no, String name){
        this.no = no;
        this.name = name;
    }
    public int getNo() {
        return no;
    }
    public String getName() {
        return name;
    }
    public HeroNode getLeft() {
        return left;
    }
    public HeroNode getRight() {
        return right;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLeft(HeroNode left) {
        this.left = left;
    }
    public void setRight(HeroNode right) {
        this.right = right;
    }
    @Override
    public String toString() {
        return "HeroName{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
    //编写中序遍历的方法
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }
    //编写后续遍历的方法
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("empty");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("empty");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("empty");
        }
    }
}
