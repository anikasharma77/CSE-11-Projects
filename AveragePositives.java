class AveragePositives{
  public static void main(String[] args){
    double ans = 0.0;
    double val;
    double value;
    boolean boolAns = true;
    int counter = 0;
    boolean finBool = false;
    for(int i = 0; i<args.length; i++){
      value = Double.parseDouble(args[i]);
      if(value<=0){
        finBool = true; 
      }
      else{
        counter++;
      }
    
      if(finBool == false){
        val = Double.parseDouble(args[i]);
        ans = ans + val;
      }
      finBool = false;
  }
      
    
      //System.out.println(ans);
      //System.out.println(finBool);
      if(ans==0.0){
        System.out.println("0");
      }
      else{
        double ans2 = ans / (counter);
        System.out.println(ans2);
      }
      

    
    
    
  }
}