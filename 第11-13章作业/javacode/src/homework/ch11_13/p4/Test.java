package homework.ch11_13.p4;

public class Test {
    public static void main(String[] args){
        Component computer = ComponentFactory.create();
        System.out.println(computer);
        Iterator iterator = computer.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
