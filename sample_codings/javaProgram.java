package sample_codings;
import java.util.Scanner;
import java.lang.Math;
import java.util.*;
import java.util.stream.*;

class PangramProblem{

  void pangramProblem(){
    System.out.println("Enter the string");
    Scanner sc = new Scanner(System.in);
    String sentence = sc.nextLine();
    List<Character> characterArray = new ArrayList();
    for(int i=97; i<123; i++){
      char c = (char)i;
      characterArray.add(c);
    }
    for(int i=0; i<sentence.length(); i++){
      int indexValue = characterArray.indexOf(Character.toLowerCase(sentence.charAt(i)));
      if(indexValue >= 0){
          characterArray.remove(indexValue);
      }
    }
    if(characterArray.size() > 0){
      System.out.println("The entered string is not a pangram");
    }
    else{
      System.out.println("The entered string is a pangram");
    }
  }
}

interface InterfaceImplementation{
  public void findArmStrongNumber();
}

class FindArmStrongNumber implements InterfaceImplementation{

  public void findArmStrongNumber(){
      System.out.println("Enter the number");
      Scanner sc = new Scanner(System.in);
      String number = sc.nextLine();
      int firstNumber = Integer.parseInt(number);
      Double armStrongNumber = new Double(0);
      for(int i=0; i<number.length(); i++){
          Double d= new Double(Character.getNumericValue(number.charAt(i)));
          armStrongNumber = armStrongNumber + Math.pow(d, number.length());
      }
      int convertedNumber = armStrongNumber.intValue();
      if(convertedNumber == firstNumber){
        System.out.println("It is an armStrongNumber");
      }
      else{
        System.out.println("It is not an armStrongNumber");
      }
  }
}


class ReverseString{
  String str;

  void getString(){
    Scanner sc = new Scanner(System.in);
    this.str = sc.nextLine();
  }

  StringBuilder reverseString(){
    StringBuilder reverseName = new StringBuilder(this.str);
    int j = 0;
    for(int i=this.str.length(); i>0; i--){
      reverseName.setCharAt(j, this.str.charAt(i-1));
      j++;
    }
    return reverseName;
  }

  public void reverseAString(){
    this.getString();
    System.out.println(this.reverseString());
  }
}

class ShowName{
  String name;

  public void display(){
    Scanner sc = new Scanner(System.in);
    this.name = sc.nextLine();
    if (this.name.length() > 0){
        System.out.println("One for " + this.name + " and One for me");
    }
    else{
      System.out.println("One for you and One for me");
    }
  }
}

class PlayDartGame{
  double x, y;

  double getCircleRadius(){
    double radius = Math.sqrt((this.x * this.x) + (this.y * this.y));
    return radius;
  }

  void getCoordinates(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter x coordinate");
    this.x = sc.nextDouble();
    System.out.println("Enter y coordinate");
    this.y = sc.nextDouble();
  }

  int getDartPoints(double radius){
    if(radius<1){
      return 10;
    }
    else if(radius<=5 && radius>1){
      return 5;
    }
    else if(radius<10){
      return 1;
    }
    else{
      return 0;
    }
  }

  public void playDartGame(){
    this.getCoordinates();
    double circleRadius = this.getCircleRadius();
    System.out.println(this.getDartPoints(circleRadius));
  }
}

class RnaTranscriptionProblem{

  void rnaTranscription(){
    String dnaComponent[] = {"G", "C", "T", "A"};
    String rnaComponent[] = {"C", "G", "A", "U"};
    Map< String,String> hm = new HashMap< String,String>();
    for(int i=0;i<dnaComponent.length; i++){
      hm.put(dnaComponent[i], rnaComponent[i]);
    }
    System.out.println("Enter the DNA string");
    Scanner sc = new Scanner(System.in);
    String dnaValue = sc.nextLine();
    StringBuilder rnaValue = new StringBuilder(dnaValue);
    for(int i=0; i<dnaValue.length(); i++){
      rnaValue.setCharAt(i, (hm.get("" + dnaValue.charAt(i))).charAt(0));
    }
    System.out.println(rnaValue);
  }
}

class AggregationExample{
  PangramProblem hp;

  void panagram(){
    hp = new PangramProblem();
    hp.pangramProblem();
  }
}

class TrollValueProblem extends AggregationExample{

  Integer[] fillValues(int size){
    Integer valuesArray[] = {1, 2, 3, 4, 5, 6};
    Integer newArray[] = new Integer[size];
    Random rand = new Random();
    for(int i=0; i<size; i++){
      newArray[i] = valuesArray[rand.nextInt(6)];
    }
    return newArray;
  }

  Integer[] filterValues(Integer[] currentArray){
    Integer newArray[] = new Integer[3];
    List<Integer> changedList = Arrays.asList(currentArray);
    Collections.sort(changedList);
    Collections.reverse(changedList);
    for(int i=0; i<newArray.length; i++){
      newArray[i] = changedList.get(i);
    }
    // Use filter method with caution as the condition will lead to problems if we have dont have distinct values in a list
    //int min = Collections.min(changedList);
    //System.out.println("Minimum value in the list");
    //List<Integer> convertedList = Arrays.asList(currentArray);
    //System.out.println("Converted list");
    //for(int i=0; i<convertedList.size(); i++){
    //  System.out.println(convertedList.get(i));
    //}
    //List<Integer> finalList = convertedList.stream().filter(s -> s>min).collect(Collectors.toList());
    //System.out.println("Final list");
    //for(int i=0; i<finalList.size(); i++){
    //  System.out.println(finalList.get(i));
    //}
    return newArray;
  }

  void getTrollValues(){
    String abilities[] = {"strength", "dexterity", "constitution", "intelligence", "wisdom", "charisma"};
    Map< String,Integer[]> trollValuesMap = new HashMap< String,Integer[]>();
    Map< String,Integer> trollValuesFinal = new HashMap< String,Integer>();
    for(int i=0; i<abilities.length; i++){
      trollValuesMap.put(abilities[i], this.fillValues(4));
      trollValuesMap.put(abilities[i], this.filterValues(trollValuesMap.get(abilities[i])));
      //int value = 0;        The below reduced parallel is written instead of this
      //for(int j=0; j<trollValuesMap.get(abilities[i]).length; j++){
      //  value = value + trollValuesMap.get(abilities[i])[j];
      //}
      //System.out.println(value);
      int reducedParallel = Arrays.asList(trollValuesMap.get(abilities[i])).parallelStream()
        .reduce(0, (a, b) -> a + b, (a, b) -> {
           return a + b;
        });
      trollValuesFinal.put(abilities[i], reducedParallel);
    }
    Integer constitutionAbility = trollValuesFinal.get("constitution");
    float x = (float)(constitutionAbility - 10);
    float constitutionModifier = Math.round(x / 2.0);
    double hitPoints = 10.0 + constitutionModifier;
    System.out.println("Hitpoint according to constitution modifier is as below");
    System.out.println(hitPoints);
  }

  public void trollValueProblem(){
      this.getTrollValues();
  }
}

public class javaProgram extends TrollValueProblem{
  public static void main(String[] args){
      System.out.println("Troll value problem");
      javaProgram pro = new javaProgram();
      pro.trollValueProblem();
      System.out.println("Interface example - Armstrong number problem");
      InterfaceImplementation example = new FindArmStrongNumber();
      example.findArmStrongNumber();
      System.out.println("Aggregation example - Pangram problem");
      pro.panagram();
  }
}
