class TestCase{
  public static void main(String[] args){
    WrapModule wm1 = new WrapModule("Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile");
    WrapModule wm2 = new WrapModule("Obama,visited,Facebook,headquarters:,http://bit.ly/xyz,@elversatile",",");
    System.out.println(wm1.wrapString());
    
    System.out.println(wm2.wrapString());

  }
}