package DeterministicMode;

import java.io.File;
import java.nio.file.Files;
import java.util.List;


public class DeterministicMode implements ActionReader,RandomGenerator {
    private final String pathToNumbers="random_numbers.txt";
    private final  String pathToActions="user_actions.txt";
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
        String temp = nextAction;
        nextAction = actions.get(currentAct);
        currentAct++;
        return temp;
    }

    @Override
    public int nextInt(int n) {
        int temp = nextNum.charAt(0) - '0';
        nextNum = numbers.get(currentNum);
        currentNum++;
        return temp;
    }

    public boolean hasNextAction(){
        return actions.size()>currentAct;
    }
}
