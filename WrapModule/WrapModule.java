import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WrapModule{
  
  // Spited original arrays of string
  String[] strings;
  // we keep the wrapped strings so that the wrapping process just runs once
  String wrapedStr;
  
  // Constructor: each words or substring is defaulted to be spited by " " 
  public WrapModule(String s){
    this.strings = s.split(" ");
    wrapedStr = "";
  }
  // Constructor: specify the splitter if necessary
  public WrapModule(String s, String spliter){
    this.strings = s.split(spliter);
    wrapedStr = "";
  }
  
  public String wrapString(){
    // already wrapped, return directly
    if (wrapedStr.length() != 0){
      return wrapedStr;
    }
    // not wrap yet, then wrap it
    
    StringBuilder strBld = new StringBuilder();
    
    for(int i = 0; i < strings.length; i++){
      // whether it is a link
      if(isHttp(strings[i])){
        // wrap it as a link
        strBld.append(wrap(strings[i], "HTTP"));
      // whether it is a Tweeter name, specify the identify character
      }else if(isTwitter(strings[i], '@')){ 
        // wrap it as a Tweeter name
        strBld.append(wrap(strings[i], "TN"));
      // Whether it is an entity
      }else if(isEntity(strings[i])){
        // wrap it as an entity
        strBld.append(wrap(strings[i], "EN"));
      
      }else{//append it directly
        strBld.append(strings[i]);
      }
      if(i != strings.length -1){
        strBld.append(" ");
      }
    }
    // change the wrapedStr, so that next time just need to return it directly
    wrapedStr = strBld.toString();
    return wrapedStr;
  }
  
  // wrap the string according to the different styles, is flexible to add more cases if necessary in the future.
  public String wrap(String s, String style){
    
    StringBuilder sb = new StringBuilder();
     // append according to the HTTP requirement: <a href="linkname">linkname</a>
    if(style.equals("HTTP")){
   
      sb.append("<a href=”");
      sb.append(s);
      sb.append("”>");
      sb.append(s);
      sb.append("</a>");
     // append according to the Twitter Name requirement: @<a href="http://twitter.com/twittername">twittername</a>
    }else if(style.equals("TN")){
      
      sb.append("@<a href=”http://twitter.com/");
      sb.append(s.substring(1));
      sb.append("”>");
      sb.append(s.substring(1));
      sb.append("</a>");
    // append according to the Entity requirement: <strong> Entity</strong>
    }else if(style.equals("EN")){
      
      sb.append("<strong>");
      sb.append(s);
      sb.append("</strong>");
    
    }
    
    return sb.toString();
  
  }
  
  public boolean isHttp(String s){
    // regex is used to match the url, can be changed if future needed
    String pattern = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
  
    Pattern patt = Pattern.compile(pattern);
    Matcher matcher = patt.matcher(s);
    return matcher.matches();
  }
  
  public boolean isEntity(String s){
    // Entity criteria: 
    //          The first letter is uppercase
    //further modified if need to test other letters
    if(s.length() > 0 && Character.isUpperCase(s.charAt(0))){
      return true;
    }else{
      return false;
    }  
  }
  
  public boolean isTwitter(String s, char c){
    // TwitterName criteria:
    //        1. The first letter is specific case, e.g.'@'
    //        2. there are at least one letters after the '@'
    if(s.length() >= 2 && s.charAt(0) == c){
      return true;
    }else{
      return false;
    }
  }
}
