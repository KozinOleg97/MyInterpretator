import Core.CodeLoader;
import Core.Interpretator;

class Main {

    public static void main(String args[]) {

        CodeLoader loader = new CodeLoader("src\\main\\resources\\flavi.prog");

        Interpretator interpretator = new Interpretator(loader);


    }
}
