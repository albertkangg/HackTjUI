package com.example.hacktjui;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;
import java.util.TreeSet;

public class MainActivity extends Activity {
    int numRounds = 10;
    int sol, roundCount, difficulty;
    String eval = "";
    TextView level, solution, timer, target;
    Button next, box1, box2, box3, box4, plus, minus, multiply, divide;
    ImageButton undo;
    boolean numTouch, numOneTouch, numTwoTouch, numThreeTouch, numFourTouch, startedRound, gameEnded;
    int[] numbers = new int[5];
    int possol;
    boolean[] used;
    TreeSet<Integer> solutions = new TreeSet<Integer>();
    public static int count2;
    public static int a2,a3,a4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.timer);
        level = findViewById(R.id.RoundView);
        solution = findViewById(R.id.textView4);
        undo = findViewById(R.id.reset);
        next = findViewById(R.id.next);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        target = findViewById(R.id.targetNum);

        plus.setText("+");
        minus.setText("-");
        multiply.setText("*");
        divide.setText("/");
        next.setText("Start Game");

        difficulty = Objects.requireNonNull(getIntent().getExtras()).getInt("level");
        switch(difficulty) {
            case 0:
                level.setText("Easy");
                break;
            case 1:
                level.setText("Medium");
                break;
            case 2:
                level.setText("Hard");
                break;
            case 3:
                level.setText("Survival");
                break;
        }

        roundCount = 1;
    }

    public void playGame(int difficulty) {
        int maxNum;
        if(difficulty == 0) {
            maxNum = 10;
            playRound(maxNum);
        }
        else if(difficulty == 1) {
            maxNum = 20;
            playRound(maxNum);
        }
        else if(difficulty == 2) {
            maxNum = 30;
            playRound(maxNum);
        }
        else if(difficulty == 3) {
            runGame2();
        }
    }

    private void startRoundTimerDisplay() {
        CountDownTimer gameRoundTimer = new CountDownTimer(4*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                timer.setText("Solve!");
            }

        }.start();
    }

    public void playRound(int max) {

        numbers[1] = (int) (Math.random() * max) + 1;
        //numbers[1] = 7;
        box1.setText(String.valueOf(numbers[1]));
        numbers[2] = (int) (Math.random() * max) + 1;
        //numbers[2] = 10;
        box2.setText(String.valueOf(numbers[2]));
        numbers[3] = (int) (Math.random() * max) + 1;
        //numbers[3] = 6;
        box3.setText(String.valueOf(numbers[3]));
        numbers[4] = (int) (Math.random() * max) + 1;
        //numbers[4] = 4;
        box4.setText(String.valueOf(numbers[4]));

        generateSolution(numbers);

        int rand = (int) (Math.random() * solutions.size()) +1;
        int count = 0;

        for(int i: solutions) {
            if(count==rand) {
                sol = i;
            }
            count++;
        }
        target.setText(String.valueOf(sol));
    }

    public void runGame2() {

    }

    public void backToLevels(View view) {
        finish();
    }

    public void num1 (View view) {
        if(startedRound)
            if(!numTouch && !numOneTouch) {
                eval += numbers[1] + " ";
                numTouch = true;
                numOneTouch = true;
                solution.setText(eval);
            }
    }

    public void num2 (View view) {
        if(startedRound)
            if(!numTouch && !numTwoTouch) {
                eval += numbers[2] + " ";
                numTouch = true;
                numTwoTouch = true;
                solution.setText(eval);
            }
    }
    public void num3 (View view) {
        if(startedRound)
            if(!numTouch && !numThreeTouch) {
                eval += numbers[3] + " ";
                numTouch = true;
                numThreeTouch = true;
                solution.setText(eval);
            }
    }
    public void num4 (View view) {
        if(startedRound)
            if(!numTouch && !numFourTouch) {
                eval += numbers[4] + " ";
                numTouch = true;
                numFourTouch = true;
                solution.setText(eval);
            }
    }
    public void plus (View view) {
        if(startedRound)
            if(numTouch) {
                eval += "+ ";
                numTouch = false;
                solution.setText(eval);
            }

    }
    public void minus (View view) {
        if(startedRound)
            if(numTouch) {
                eval += "- ";
                numTouch = false;
                solution.setText(eval);
            }
    }
    public void multiply (View view) {
        if(startedRound)
            if(numTouch) {
                eval += "* ";
                numTouch = false;
                solution.setText(eval);
            }
    }
    public void divide (View view) {
        if(startedRound)
            if(numTouch) {
                eval += "/ ";
                numTouch = false;
                solution.setText(eval);
            }
    }

    public void reset (View view) {
        eval = "";
        solution.setText(eval);
        resetTouch();
    }

    public boolean userDone() {
        if(numOneTouch && numTwoTouch && numThreeTouch && numFourTouch)
            return true;
        return false;
    }
    public void resetTouch() {
        numTouch = false;
        numOneTouch = false;
        numTwoTouch = false;
        numThreeTouch = false;
        numFourTouch = false;
    }
    public void nextRound(View view) {
        if(!startedRound) {
            resetTouch();
            eval = "";
            solution.setText("");
            target.setText("");
            next.setVisibility(View.GONE);
            startRoundTimerDisplay();
            level.setText("Round: " + roundCount);
            next.setText("submit");
            next.setVisibility(View.VISIBLE);
            playGame(difficulty);
            startedRound = true;
        }
        else {
            if(gameEnded) {
                gameEnded = false;
                finish();
            }
            else {
                if(userDone()) {
                    eval = eval.substring(0, eval.length()-1);
                    if((double) sol == evaluate(eval))  {
                        solution.setText("Correct");
                    }
                    else {
                        solution.setText("Incorrect");
                    }
                    if (roundCount == 10){
                        next.setVisibility(View.GONE);
                        target.setText("Game Over");
                        gameEnded = true;
                    }
                    else {
                        roundCount++;
                        //                   roundEnded = true;
                        next.setText("Next Round");
                    }
                    startedRound = false;
                }
            }
        }
    }

    public void generateSolution(int[] nums)
    {
        possol = 1536;
        used = new boolean[5];
        generateSolution(solutions, nums, 0, 0, 0, used);
    }
    public void generateSolution(TreeSet<Integer> solutions, int[] nums, int count, int count3, int sol, boolean[] used)
    {
        if(count == 0){
            if(count == (possol/4))
                count3 = 0;
            if((count2-1)/possol == 0){
                sol = nums[1];
                used[1] = true;
                generateSolution(solutions, nums, count+1, count3, sol, used);
            }
            if((count2-1)/possol == 1){
                sol = nums[2];
                used[2] = true;
                generateSolution(solutions, nums, count+1, count3, sol, used);
            }
            if((count2-1)/possol == 2){
                sol = nums[3];
                used[3] = true;
                generateSolution(solutions, nums, count+1, count3, sol, used);
            }
            if((count2-1)/possol == 3){
                sol = nums[4];
                used[4] = true;
                generateSolution(solutions, nums, count+1, count3, sol, used);
            }
        }
        if(count == 1){
            if(a4%3 == 0){
                used[(a4/3)+1] = true;
                int a = 1;
                while(used[a])
                    a++;
                used[a] = true;
                count3++;
                generateSolution(solutions, nums, count+1, count3, sol + nums[a], used);
                used[a2] = false;
                used[a3] = false;
                generateSolution(solutions, nums, count+1, count3, sol - nums[a], used);
                used[a2] = false;
                used[a3] = false;
                generateSolution(solutions, nums, count+1, count3, sol / nums[a], used);
                used[a2] = false;
                used[a3] = false;
                generateSolution(solutions, nums, count+1, count3, sol * nums[a], used);
            }
        }
        if(count == 2){
            if(count3%2==1){
                used[a3] = false;
                int a = 1;
                while(used[a])
                    a++;
                a2 = a;
                used[a] = true;
                count3++;
                generateSolution(solutions, nums, count+1, count3, sol + nums[a], used);
                used[a3] = false;
                generateSolution(solutions, nums, count+1, count3, sol - nums[a], used);
                used[a3] = false;
                generateSolution(solutions, nums, count+1, count3, sol / nums[a], used);
                used[a3] = false;
                generateSolution(solutions, nums, count+1, count3, sol * nums[a], used);
            }
            if(count3%2==0){
                used[a2] = false;
                int a = 1;
                while(used[a])
                    a++;
                a+=1;
                while(used[a])
                    a++;
                a2 = a;
                used[a] = true;
                count3++;
                generateSolution(solutions, nums, count+1, count3, sol + nums[a], used);
                used[a3] = false;
                generateSolution(solutions, nums, count+1, count3, sol - nums[a], used);
                used[a3] = false;
                generateSolution(solutions, nums, count+1, count3, sol / nums[a], used);
                used[a3] = false;
                generateSolution(solutions, nums, count+1, count3, sol * nums[a], used);
            }
        }
        if(count == 3){
            int a = 1;
            while(used[a])
                a++;
            used[a] = true;
            a3 = a;
            generateSolution(solutions, nums, count+1, count3, sol + nums[a], used);
            used[a] = false;
            generateSolution(solutions, nums, count+1, count3, sol - nums[a], used);
            used[a] = false;
            generateSolution(solutions, nums, count+1, count3, sol / nums[a], used);
            used[a] = false;
            generateSolution(solutions, nums, count+1, count3, sol * nums[a], used);
        }
        if(count == 4){
            count2++;
            if(sol%1==0)
                solutions.add(sol);
        }
    }

    public double evaluate(String s){
        String[] c = s.split(" ");
        double n1 = Double.parseDouble(c[0]);
        double n2 = Double.parseDouble(c[2]);
        double n3 = Double.parseDouble(c[4]);
        double n4 = Double.parseDouble(c[6]);
        char o1 = c[1].charAt(0);
        char o2 = c[3].charAt(0);
        char o3 = c[5].charAt(0);
        double answer = n1;
        if(o1 =='+')
            answer += n2;
        if(o1 == '-')
            answer -= n2;
        if(o1 == '*')
            answer *= n2;
        if(o1 == '/')
            answer /= n2;
        if(o2 =='+')
            answer += n3;
        if(o2 == '-')
            answer -= n3;
        if(o2 == '*')
            answer *= n3;
        if(o2 == '/')
            answer /= n3;
        if(o3 =='+')
            answer += n4;
        if(o3 == '-')
            answer -= n4;
        if(o3 == '*')
            answer *= n4;
        if(o3 == '/')
            answer /= n4;
        return answer;
    }
}