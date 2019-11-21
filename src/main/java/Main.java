public class Main {

    public static void main(String args[]) {

        CodeLoader loader = new CodeLoader("src\\main\\resources\\ProgrammText1.prog");

        Interpretator interpretator = new Interpretator(loader);


    }
}
