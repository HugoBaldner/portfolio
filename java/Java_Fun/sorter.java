



public class sorter{
  

   public sorter(){
   
   
   }
   
   public int[] bubble(int[] x){
      boolean edit = true;
      while(edit){
         edit = false;
         for(int i = 0; i < x.length - 1; i++){
            int store = x[i];
            if( store > x[i+1]){
               x[i] = x[i+1];
               x[i+1] = store;
               edit = true;
            }
      
         }
      }
      return x;
   }
 

}