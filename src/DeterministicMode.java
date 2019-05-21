import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class DeterministicMode implements ActionReader,RandomGenerator {
    private final String pathToNumbers="C:\\Users\\amitc\\Desktop\\Degree\\Second Semester\\oop\\OopEx3\\deterministicMode\\random_numbers.txt";
    private final  String pathToActions="C:\\Users\\amitc\\Desktop\\Degree\\Second Semester\\oop\\OopEx3\\deterministicMode\\user_actions.txt";
    private List<String> actions;
    private List<String> numbers;
    private String nextAction;
    private String nextNum;
    private int currentAct=0;
    private int currentNum=0;

    public DeterministicMode(){
        try {
            actions= Files.readAllLines(new File(pathToActions).toPath());
            nextAction=actions.get(currentAct);
            currentAct++;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            numbers=Files.readAllLines(new File(pathToNumbers).toPath());
            nextNum=numbers.get(currentNum);
            currentNum++;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public String nextAction() {
        if (GameBoard.isDeterministic!=null) {
            String temp = nextAction;
            nextAction = actions.get(currentAct);
            currentAct++;
            return temp;
        }
        else{
            Scanner reader = new Scanner(System.in);
            return reader.next();
        }
    }

    @Override
    public int nextInt(int n) {
        if (GameBoard.isDeterministic!=null) {
            int temp = nextNum.charAt(0) - '0';
            nextNum = numbers.get(currentNum);
            currentNum++;
            return temp;
        }
        else {
            Random rnd =new Random();
            return rnd.nextInt(n);
        }
    }

    public boolean hasNextAction(){
        return actions.size()>currentAct;
    }
    public boolean hasNextNum(){
        return numbers.size()>currentNum;
    }
}
