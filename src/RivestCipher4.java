/*
Author: Seth Hostetler
Date: 10/18/2016
Program: Rivest Cipher 4 implementation, for MTH 120
Methods needed:
    First half - find mask
    second half - find _ letters
    XOR letters with given message
 */
import java.lang.*;
import java.util.HashMap;
import java.util.Map;

public class RivestCipher4 
{

    public static void main(String[] args) 
    {
        //Still need to implement an automatic extension process 
        // - to fill out the rest of the blocks in the key array

        String inputKey = "DISCRETE";
        int bitSize = 5;
        int stateArraySize = (int) Math.pow(2, bitSize);
        
        final String keyString = extendKey(inputKey, stateArraySize);
        System.out.println(keyString);        
        int[] key = stringToNumArray(keyString);    
                
        
        int[] state = new int[stateArraySize];
        stateShuffle(state, key);

        int randNumCount = 5;        //Represents size of output
        int[] answerAsNum = numberGen(state, randNumCount);

        System.out.println("Resulting numbers:");
        for (int count = 1; count < answerAsNum.length; count ++)
        {
            System.out.println(answerAsNum[count] + " ");
        }
        
        String[] binaryString = numberToBinary(answerAsNum, bitSize);
             
    }
    
    public static String extendKey(String key, int arraySize)
    {
        String fullKey = "";
        while (fullKey.length() < arraySize)
        {
            fullKey += key;
        }
        //System.out.println(fullKey);
        fullKey = fullKey.substring(0, arraySize);
        //System.out.println(fullKey);
        return fullKey;
    }
    
    public static int[] stringToNumArray(String pass)
    {
        int[] output = new int[pass.length()];    
        for(int i = 0; i < output.length; i++)
        {
            output[i] = pass.charAt(i) - 64;
            //System.out.print(output[i] + " ");
            //System.out.println(output[i] + "-" + pass.charAt(i));
        }
        return output;
    }
    
    public static void stateShuffle(int[] state, int[] key)
    {

        for(int i = 0; i < state.length; i++)
        {
            state[i] = i;
        } 
        
        int j = 0;
        for(int i = 0; i < state.length; i++)
        {
            //System.out.println("J: " + j + " state[" + i + "]: " + state[i] + " and key[" + i + "]: " + key[i]);
            j = (j + state[i] + key[i]) % state.length;
            //System.out.println("Switch state " + j + ": " + state[j] + " and " + i + ": " + state[i]);
            int temp = state[i];
            state[i] = state[j];
            state[j] = temp;
            //System.out.println("After switch: state " + j + ": " + state[j] + " and " + i + ": " + state[i]);
            //System.out.println();
        }
//        for (int count = 0; count < state.length; count ++)
//        {
//            System.out.println(state[count]);
//        }
    }
    
    public static int[] numberGen(int[] state, int size)
    {
        int j = 0;
        int i = 0;
        int[] outputNumList = new int[size+1];
        while (i < size)
        {
            i++;
            //System.out.println("i: " + i + "  j: " + j);
            if(i > size) { break;}
            j = (j + state[i]) % state.length;
            //System.out.println("i: " + i + "  j: " + j);            
            int temp = state[i];
            state[i] = state[j];
            state[j] = temp;
            //System.out.println("State[" + i + "]: " + state[i] + "; State[" + j + "]: " + state[j]);
            outputNumList[i] = state[(state[i] + state[j]) % state.length];
            //System.out.println(answerAsNum[i]);
            //answerAsNum[i] = convertToBinary(answerAsNum[i]);
        }
//        System.out.println();
//        for (int count = 0; count < state.length; count ++)
//        {
//            System.out.println(state[count]);
//        }
        return outputNumList;
    }
    
    public static String[] numberToBinary(int[] numList, int bitSize)
    {
        String[] binaryString = new String[numList.length];
        for (int count = 1; count < binaryString.length; count ++)
        {
            binaryString[count] = Integer.toBinaryString(numList[count]);
            //System.out.println(binaryString[count] + " length: " + binaryString[count].length());
            if (binaryString[count].length() < bitSize)
            {
                int lengthDifference = bitSize - binaryString[count].length();
                /*
                System.out.println("Difference in lengths: " + lengthDifference);
                System.out.println(binaryString[count] + " length: " + binaryString[count].length());  
                */
                String lengthFixed = new String(new char[lengthDifference]).replace("\0", "0");
                binaryString[count] = lengthFixed + binaryString[count];
                /*
                System.out.println(binaryString[count] + " length: " + binaryString[count].length());    
                System.out.println("******");
                */
            }
        }
        System.out.println("Answers in binary:");
        for (int count = 1; count < binaryString.length; count ++)
        {
            System.out.println(binaryString[count] + " ");
        } 
        return binaryString;
    }
    
    
    public static int convertToBinary(int decimalForm)
    {
        int binary = 0;
        binary = Integer.parseInt(Integer.toBinaryString(decimalForm));
        return binary;        
    }
    
    public static int convertToDecimal(int binary)
{
    int decimal = 0;
    decimal = Integer.parseInt(Integer.toBinaryString(binary),2);
    return decimal;        
}
    
    public static int singleXOR(int num1, int num2)
    {
        int xor = 0;
        if( ( num1 == 1 || num2 == 1 ) && ! ( num1 == 1 && num2 == 1 ) )
        {
            xor = 1;
        }
        return xor;
    }
//    public static int fullXOR(int num1, int num2)
//    {
//        
//        return 
//    }
}