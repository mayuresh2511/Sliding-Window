package com.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class SlidingWindow {

  public static void main(String[] args) {
   System.out.println(maximumSumSubArrayOfGivenSizeBruteForce(new Integer[]{2, 5, 1, 8, 2, 9, 1}, 4));
   System.out.println(maximumSumSubArrayOfGivenSizeForLoopSlidingWindow(new Integer[]{2, 5, 1, 8, 2, 9, 1}, 4));
   System.out.println(maximumSumSubArrayOfGivenSizeWhileLoopSlidingWindow(new Integer[]{2, 5, 1, 8, 2, 9, 1}, 4));
    System.out.println(firstNegativeNumberInWindowOfGivenSize(new Integer[]{12, -1, -7, 8, -16, 30, 16, 28}, 3));
    System.out.println(countAnagramsOfString("cbaebabacd", "abc"));
    System.out.println(maximumOfAllSubArraysOfGivenSizeBruteforce(new Integer[]{2, 5, 1, 8, 2, 9, 1}, 3));
    System.out.println(largestSubArrayOfGivenSumBruteForce(new Integer[]{2, 5, 2, 1, 8, 2, 3, 3, 1, 1}, 10));
  }

  public static Integer maximumSumSubArrayOfGivenSizeBruteForce(Integer [] numbers, Integer subArraySize){

    Integer maxSum = Integer.MIN_VALUE;
    Integer tempMaxSum;

    for (int i = 0; i <= numbers.length - subArraySize; i++){

      tempMaxSum = 0;

      for (int j = i; j < i + subArraySize; j++){

        tempMaxSum = tempMaxSum + numbers[j];
      }

      if(maxSum < tempMaxSum){
        maxSum = tempMaxSum;
      }
    }
    return maxSum;
  }

  public static Integer maximumSumSubArrayOfGivenSizeForLoopSlidingWindow(Integer[] numbers, Integer windowSize){

    Integer maxSum = Integer.MIN_VALUE;
    Integer tempMaxSum = 0;
    Integer prevTempMaxSum = 0;

    for (int i = 0; i <= numbers.length - windowSize; i++){

      if(i == 0) {

        for (int j = i; j < i + windowSize; j++) {

          tempMaxSum = tempMaxSum + numbers[j];
        }

      }else{

        tempMaxSum = tempMaxSum - numbers[i - 1];
        tempMaxSum = tempMaxSum + numbers[i + windowSize - 1];

      }

      if(maxSum < tempMaxSum){
        maxSum = tempMaxSum;
      }
    }
    return maxSum;
  }

  public static Integer maximumSumSubArrayOfGivenSizeWhileLoopSlidingWindow(Integer[] numbers, Integer windowSize){

    Integer pointer = 0;
    Integer tempMaxSum = 0;
    Integer maxSum = Integer.MIN_VALUE;

    while (pointer < numbers.length){
      if(pointer < windowSize){
        tempMaxSum = tempMaxSum + numbers[pointer];
        pointer ++;
      }else{
        tempMaxSum = tempMaxSum - numbers[pointer - windowSize];
        tempMaxSum = tempMaxSum + numbers[pointer];
        pointer++;
      }
      if (tempMaxSum > maxSum){
        maxSum = tempMaxSum;
      }
    }
    return  maxSum;
  }

  public static ArrayList<Integer> firstNegativeNumberInWindowOfGivenSize(Integer[] numbers, Integer windowSize){

    Integer pointer1 = 0;
    Integer pointer2 = 0;

    Queue<Integer> firstNegativeNumber = new LinkedList<>();
    Queue<Integer> firstNegativeNumberIndex = new LinkedList<>();

    ArrayList<Integer> result = new ArrayList<>();

    while (pointer2 < numbers.length){

      if (pointer2 < windowSize){
        if(numbers[pointer2] < 0){
          if (result.isEmpty()){
            result.add(numbers[pointer2]);
          }
          firstNegativeNumber.add(numbers[pointer2]);
          firstNegativeNumberIndex.add(pointer2);
        }
        pointer2++;
      }else{
        if(firstNegativeNumber.isEmpty()){
          result.add(null);
        }else{
          pointer1++;
          if(firstNegativeNumberIndex.peek() >= pointer1){
            result.add(firstNegativeNumber.peek());
          }else{
            firstNegativeNumber.poll();
            firstNegativeNumberIndex.poll();
            result.add(firstNegativeNumber.peek());
          }
          if(numbers[pointer2] < 0) {
            firstNegativeNumber.add(numbers[pointer2]);
            firstNegativeNumberIndex.add(pointer2);
          }
          pointer2++;
        }
      }
    }

    return result;
  }

  public static Integer countAnagramsOfString(String largeString, String smallString){

    HashMap<String, Integer> countOriginalString = new HashMap<>();
    HashMap<String, Integer> countSlidingWindow = new HashMap<>();
    Integer pointer = 0;
    Integer pointerToRemove = 0;
    Integer anagramsCount = 0;

    for (int i = 0; i < smallString.length(); i++){
      if (countOriginalString.containsKey(smallString.substring(i, i+1))){
        countOriginalString.replace(smallString.substring(i, i+1), countOriginalString.get(smallString.substring(i, i+1)) + 1);
      }else{
        countOriginalString.put(smallString.substring(i, i+1), 1);
      }
    }

    while (pointer < largeString.length()){
      if(pointer < smallString.length()){

        if (countSlidingWindow.containsKey(largeString.substring(pointer, pointer+1))){
          countSlidingWindow.replace(largeString.substring(pointer, pointer+1),
              countSlidingWindow.get(largeString.substring(pointer, pointer+1)) + 1);
        }else{
          countSlidingWindow.put(largeString.substring(pointer, pointer+1), 1);
        }

        if (pointer == smallString.length() - 1){
          anagramsCount = anagramsCount + checkIfAnagram(countOriginalString, countSlidingWindow);
        }

        pointer ++;
      }else{

        pointerToRemove = pointer - smallString.length();
        countSlidingWindow.replace(largeString.substring(pointerToRemove, pointerToRemove + 1),
            countSlidingWindow.get(largeString.substring(pointerToRemove, pointerToRemove + 1)) - 1);

        if (countSlidingWindow.get(largeString.substring(pointerToRemove, pointerToRemove + 1)) == 0){
          countSlidingWindow.remove(largeString.substring(pointerToRemove, pointerToRemove + 1));
        }

        if (countSlidingWindow.containsKey(largeString.substring(pointer, pointer+1))){
          countSlidingWindow.replace(largeString.substring(pointer, pointer+1),
              countSlidingWindow.get(largeString.substring(pointer, pointer+1)) + 1);
        }else{
          countSlidingWindow.put(largeString.substring(pointer, pointer+1), 1);
        }

        anagramsCount = anagramsCount + checkIfAnagram(countOriginalString, countSlidingWindow);

        pointer++;
      }
    }
    return anagramsCount;
  }

  public static Integer checkIfAnagram(HashMap<String, Integer> anagramStringMap, HashMap<String, Integer> anagramToFindInStringMap){

    if (anagramStringMap.keySet().size() != anagramToFindInStringMap.keySet().size()){
      return 0;
    }

    for (String str : anagramStringMap.keySet()){

      if (! anagramToFindInStringMap.containsKey(str)){
        return 0;
      }else if(anagramStringMap.get(str) != anagramToFindInStringMap.get(str)){
        return  0;
      }
    }
    return  1;
  }

  public static List<Integer> maximumOfAllSubArraysOfGivenSizeBruteforce(Integer[] numbers, Integer windowSize){
    Integer maxValue;
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i <= numbers.length - windowSize; i++){
      maxValue = Integer.MIN_VALUE;

      for (int j = i; j < i + windowSize; j++){
        if(numbers[j] > maxValue){
          maxValue = numbers[j];
        }
      }
      result.add(maxValue);
    }
    return result;
  }

  public static Integer largestSubArrayOfGivenSumBruteForce(Integer[] numbers, Integer Sum){
    Integer largestSubArrayLength = Integer.MIN_VALUE;
    Integer intermediateSum;

    for (int i = 2; i < numbers.length; i++){
      for (int j = 0; j <= numbers.length - i; j++){
        intermediateSum = 0;
        for (int k = j; k < j + i; k++){
          intermediateSum += numbers[k];
        }
        if (intermediateSum == Sum){
          largestSubArrayLength = i;
          break;
        }
      }
    }
    return largestSubArrayLength;
  }
}
