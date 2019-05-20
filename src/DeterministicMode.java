import java.io.File;

public class DeterministicMode {
    private final String pathToNumbers="C:\\Users\\amitc\\Desktop\\Degree\\Second Semester\\oop\\OopEx3\\deterministicMode\\random_numbers.txt";
    private final  String pathToActions="C:\\Users\\amitc\\Desktop\\Degree\\Second Semester\\oop\\OopEx3\\deterministicMode\\user_actions.txt";
    private File actions;
    private File numbers;

    public DeterministicMode(){
        actions=new File(pathToActions);
        numbers=new File(pathToNumbers);
    }

    public File getActions() {
        return actions;
    }

    public File getNumbers() {
        return numbers;
    }
}
