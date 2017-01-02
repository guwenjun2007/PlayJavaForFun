//User wants to walk from one element to another element in a NxN matrix, find
//the shortest path;


import java.io.*;
import java.util.*;

//Class Position shows the coordinate of the elements
class Position{
  int x;
  int y;
  public Position(int x, int y){
    this.x = x;
    this.y = y;
  }
}

// solution class
class Solution {
  // the main function specifies a test case  
  public static void main(String[] args) {
    // the Path Matrix
    int[][] map = {{1,2,2,8,9,9,1},
                    {3,3,4,2,0,4,2},
                    {5,9,8,6,6,2,7},
                    {9,4,4,5,5,5,5},
                    {3,7,0,3,5,2,0},
                    {7,4,8,4,7,7,4},
                    {2,1,1,5,2,3,2}};
    
    // the start element and end element of the path;
    Position start = new Position(6,1);
    Position end = new Position(4,5);
    
    Solution solution = new Solution();
    ArrayList<ArrayList<Position>> result= solution.minSum(map, start, end);
    // the following code out put all the path that has the smallest pathSum
    for(int i = 0; i < result.size(); i++){
      // output the path number
      System.out.println("path: "+ (i+1));
      // output the path
      for(int j = 0; j < result.get(i).size(); j++){
        System.out.print("(" + result.get(i).get(j).x + ", " + result.get(i).get(j).y +")" + (j == result.get(i).size() -1 ? "":"->"));
      }
      System.out.println();
    }
  }
  
  // global vairables
  ArrayList<ArrayList<Position>> result = new ArrayList<>();// path
  int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};// moving direction
  int minSum = Integer.MAX_VALUE;// mininum path sum
 
  //function minSum calculate the minimun path sum by depth-first-search
  //input is the map matrix, start element and end element
  //output is all the minimun path
  public ArrayList<ArrayList<Position>> minSum(int[][] map, Position start, Position end){
    if(map == null || map.length == 0 || start == null || end == null){
      System.out.println("illigal input");
      return result;
    }
    ArrayList<Position> currPath = new ArrayList<>();
    // dfs to find the path
    dfs(currPath , map, start, end, 0);
    
    return result;
  
  }
  
  // the input of dfs include:
  //ArrayList<position> currPath : the current path;
  //int[][] map: the map matrix
  //Position: start, end
  //int: currSum, the current pathSum;
  //although no output, this function will change the global vairable "result" in the running process
  public void dfs(ArrayList<Position> currPath, int[][] map, Position start, Position end, int currSum){
    // return case:
    //1. current position is out of boundary
    //2. this position has already been passed through
    //3. the current pathSum is too large
    if(start.x < 0 || start.x >= map.length || start.y < 0 || start.y >= map[0].length || map[start.x][start.y] == Integer.MIN_VALUE || currSum > minSum){
      return;
    }
    // add the current position in the path list;
    currPath.add(new Position(start.x, start.y));
    
    // if already arrived at the end element
    if(start.x == end.x && start.y == end.y){
      // if the current path sum is the same with minumum path sum: add the path in the result
      // if the current path sum is smaller than 
      if(currSum == minSum){
        result.add(new ArrayList<Position>(currPath));
      }else if(currSum < minSum){
        minSum = currSum;
        result.clear();
        result.add(new ArrayList<Position>(currPath));  
      } 
       currPath.remove(currPath.size() - 1);
      return;
    }
    // save the currValue for restoration
    int currValue = map[start.x][start.y];
    map[start.x][start.y] = Integer.MIN_VALUE;
    
    // depth first search in four directions
    for(int i = 0; i < 4; i++){
      // get the next position
      Position next = new Position(start.x + direction[i][0], start.y+ direction[i][1]);
      dfs(currPath, map, next, end, currSum + currValue);
    }
    // restore the matrix and the currPath
    map[start.x][start.y] = currValue;
    currPath.remove(currPath.size() - 1 );
  }
}
