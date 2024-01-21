package Array;

//427. Construct Quad Tree
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};

public class Solution {

    public Node construct(int[][] grid) {
        Node result = handler(grid,0,0,grid.length);
        return result;
    }

    public Node handler(int[][] grid,int row,int col,int len){
        if(isSame(grid,row,col,len)){
            return new Node(grid[row][col]==1?true:false,true);
        }else{
            Node node = new Node(true,false);
            node.topLeft=handler(grid,row,col,len/2);
            node.topRight=handler(grid,row,col+len/2,len/2);
            node.bottomLeft=handler(grid,row+len/2,col,len/2);
            node.bottomRight=handler(grid,row+len/2,col+len/2,len/2);
            return node;
        }
    }

    public boolean isSame(int[][] grid,int row,int col,int len){
        for(int i=row;i<row+len;i++){
            for(int j=col;j<col+len;j++){
                if(grid[i][j]!=grid[row][col]){
                    return false;
                }
            }
        }
        return true;
    }
}
