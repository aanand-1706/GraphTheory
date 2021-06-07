public class Solution{
    public static void main(String[] args){
        System.out.println("My name is Aanand ");
        Solution master=new Solution();

        InnerClassDemo inner=master.new InnerClassDemo();
        System.out.println(inner.a+" "+inner.b);

        OuterClass outer=new OuterClass();
        System.out.println(outer.a+" "+outer.b);

        StaticNestedClass staticinner=new StaticNestedClass();
        System.out.println(staticinner.a+" "+staticinner.b);
    }
    class InnerClassDemo{
        int a=4,b=5;
    }
    static class StaticNestedClass{
        int a=44,b=55;
    }
}
class OuterClass{
    int a=5,b=4;
}