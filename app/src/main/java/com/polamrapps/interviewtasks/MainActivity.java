package com.polamrapps.interviewtasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIsPalindrom = (Button) findViewById(R.id.btnIsPalindrom);
        btnIsPalindrom.setOnClickListener(this);

        Button longest = (Button) findViewById(R.id.btnLongestPalindrom);
        longest.setOnClickListener(this);

        Button permutations = (Button) findViewById(R.id.btnPermutations);
        permutations.setOnClickListener(this);

        Button minimum = (Button) findViewById(R.id.btnMinimumDrops);
        minimum.setOnClickListener(this);

        Button anagram = (Button) findViewById(R.id.btnAnagram);
        anagram.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnIsPalindrom:
                Utils.show("madam is palindrom : "+isPalindrom("madam"));
                Utils.show("polamreddy is palindrom : "+isPalindrom("polamreddy"));
                break;
            case R.id.btnLongestPalindrom:
                Utils.show("longest palindrom : "+longestPalindrom("12309madam945"));
                Utils.show("longest palindrom : "+longestPalindrom("12312321945"));
                break;
            case R.id.btnPermutations:
                permutations("ABC");
                break;
            case R.id.btnMinimumDrops:
                Utils.show("2 eggs, 100 floors --> minimum drops : "+findMinimumbrops(2, 100));
                break;
            case R.id.btnAnagram:
                Utils.show("tbeclassroom and schoolmaster : anagrams : "+anagrams("tbeclassroom", "schoolmaster"));
                Utils.show("theclassroom and schoolmaster : anagrams : "+anagrams("schoolmaster", "schoolmaster"));
                Utils.show("theclassroom and schoolmaster : anagrams : "+isAnagram("schoolmaster", "schoolmaster"));
                Utils.show("tbeclassroom and schoolmaster : anagrams : "+isAnagram("tbeclassroom", "schoolmaster"));
                break;
        }
    }

    public static boolean isAnagram(String s1, String s2){
        //case insensitive anagram
        StringBuffer sb = new StringBuffer(s2.toLowerCase());
        for (char c: s1.toLowerCase().toCharArray()){
            if (Character.isLetter(c)){
                int index = sb.indexOf(String.valueOf(c));
                if (index == -1){
                    //char does not exist in other s2
                    return false;
                }
                sb.deleteCharAt(index);
            }
        }
        for (char c: sb.toString().toCharArray()){
            //only allow whitespace as left overs
            if (!Character.isWhitespace(c)){
                return false;
            }
        }
        return true;
    }

    private boolean anagrams(String stg1, String stg2) {
        char[] array1 = stg1.replaceAll(" ", "").toCharArray();
        char[] array2 = stg2.replaceAll(" ", "").toCharArray();

        Arrays.sort(array1);
        Arrays.sort(array2);

        return Arrays.equals(array1, array2);
    }

    private int getMaximumFloors(int eggs, int drops) {
        if(eggs == 0)
            return 0;
        else {
            int result = 0;
            for(int i=0; i<drops; i++) {
                result += getMaximumFloors(eggs-1, i) + 1;
            }
            return result;
        }
    }

    private int findMinimumbrops(int eggs, int floors) {
        int drops = 0;
        if(eggs == 0)
            return drops;
        while(getMaximumFloors(eggs, drops) < floors) {
            drops++;
        }
        return drops;
    }

    private void permutations(String stg) {
        permutations("",stg);
    }

    private void permutations(String prefix, String stg) {
        int length = stg.length();
        if(length == 0)
            Utils.show(prefix);
        else {
            for (int i = 0; i < length; i++) {
                permutations(prefix + stg.charAt(i), stg.substring(0, i) + stg.substring(i + 1, length));
            }
        }
    }

    private String palindrom(String stg, int left, int right) {
        if(left > right)
            return "";
        while (left >= 0 && right < stg.length() && stg.charAt(left) == stg.charAt(right)) {
            left--;
            right++;
        }
        return stg.substring(left+1, right);
    }

    private String longestPalindrom(String stg) {
        if(stg.length() == 0)
            return "";

        String longest = stg.substring(0, 1);
        for(int i=0; i<stg.length(); i++) {
            String palindrom = palindrom(stg, i, i);
            if(palindrom.length() > longest.length())
                longest = palindrom;

            palindrom = palindrom(stg, i, i+1);
            if(palindrom.length() > longest.length())
                longest = palindrom;
        }
        return longest;
    }

    private boolean isPalindrom(String stg) {
        //Utils.show(stg);
        int length = stg.length();
        for(int i=0; i<length/2; i++) {
            if(stg.charAt(i) != stg.charAt(length-i-1))
                return false;
        }
        return true;
    }
}
