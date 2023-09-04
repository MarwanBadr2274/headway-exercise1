public class Problem2 {

    public static void main(String[] args){
        foo(10,1);
    }

    public static void foo(int n, int m){
        if(m>10){
            return;
        }
        else{
            System.out.println(n*m);
            foo(n,m+1);
        }
        if(m==10){
            foo2(n, m);
        }
    }

    public static void foo2(int n, int m){
        if(m==0){
            return;
        }
        else{
            System.out.println(n*m);
            foo2(n, m-1);
        }
    }

}
