public class Main {
    public static void main(String[]args){
        double sum =0;
       for(int i =0;i<99115148;i++){
           sum+=(Math.pow(-1,i))/((2*i)+1);

       }
       System.out.println(4*sum);
       /*double s =0;
        for(int x =0;x<9999999;x++){
           s+=(1/(Math.pow(16,x)));

        }
        System.out.println(s*2);*/

    }
}
