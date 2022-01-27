package com.example.cs408lab1a;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cs408lab1a.databinding.FragmentFirstBinding;

import java.util.Random;

public class FirstFragment extends Fragment {
    private int playerScore, computerScore;
    private FragmentFirstBinding binding;
    private String[] choices;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        playerScore = 0;
        choices = new String[]{"rock", "paper", "scissors"};
        computerScore = 0;
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int comp = generateComputerGuess("rock");
                boolean wins = false;
                if(choices[comp] == "paper"){
                    computerScore += 1;
                    setScore(playerScore, computerScore);
                    changeWhoWon(false);
                }
                else if (choices[comp] == "scissors"){
                    playerScore += 1;
                    setScore(playerScore, computerScore);
                    changeWhoWon(true);
                    wins = true;
                }
                else{
                    changeReason();
                    changeWhoWon();
                    return;
                }
                changeReason("rock",choices[comp], wins);
            }
        });
        binding.paper.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view) {
               boolean wins = false;
               int comp = generateComputerGuess("paper");
               if(choices[comp] == "scissors"){
                   computerScore += 1;
                   changeWhoWon(false);
                   setScore(playerScore, computerScore);
               }
               else if (choices[comp] == "rock"){
                   playerScore += 1;
                   changeWhoWon(true);
                   setScore(playerScore, computerScore);
                   changeWhoWon(true);
                   wins = true;
               }
               else{
                   changeReason();
                   changeWhoWon();
                   return;
               }
               changeReason("paper",choices[comp], wins);
           }
        });
        binding.scissors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int comp = generateComputerGuess("scissors");
                boolean wins = false;
                if(choices[comp] == "rock"){
                    computerScore += 1;
                    setScore(playerScore, computerScore);
                    changeWhoWon(false);
                }
                else if (choices[comp] == "paper"){
                    playerScore += 1;
                    setScore(playerScore, computerScore);
                    changeWhoWon(true);
                    wins = true;
                }
                else{
                    changeWhoWon();
                    changeReason();
                    return;
                }
                changeReason("scissors",choices[comp], wins);
            }
        });

    }

    private void setScore(int player, int computer){
        TextView text = binding.Score;
        String t = "Player: " + player + ", Computer: " + computer;
        text.setText(t);
    }
    private int generateComputerGuess(String playerChoice){
        Random rand = new Random();
        int compGuess = Math.abs(rand.nextInt()%3);
        TextView t = binding.compUsed;
        t.setText("Computer Used: "+choices[compGuess]);
        TextView p = binding.playerUsed;
        p.setText("Player used: "+playerChoice);
        return compGuess;
    }

    private void changeWhoWon(boolean playerWins){
        TextView t = binding.winner;
        if(playerWins){
            t.setText("Winner: Player");
        }else{
            t.setText("Winner: Computer");
        }
    }
    private void changeWhoWon(){
        TextView t = binding.winner;
        t.setText("Winner: tie");
    }


    private void changeReason(String playerChose, String compChose, boolean playerWins){
        TextView t = binding.reason;
        if(playerWins){
            t.setText("Reason: " + playerChose + " beats " + compChose);
        }
        else{
            t.setText("Reason: " + compChose + " beats " + playerChose);
        }
    }
    private void changeReason(){
        TextView t = binding.reason;
        t.setText("Reason: tie");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}