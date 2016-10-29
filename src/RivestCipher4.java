/*
Author: Seth Hostetler
Date: 10/18/2016
Program: Rivest Cipher 4 implementation
 */
import java.lang.*;
import java.util.HashMap;
import java.util.Map;

public class RivestCipher4 
{

    public static void main(String[] args) 
    {
        String inputKey = "DISCRETE";
        int bitSize = 5;
        int randNumCount = 5;        //Represents size of output
        
        int stateArraySize = (int) Math.pow(2, bitSize);
        
        final String keyString = extendKey(inputKey, stateArraySize);     
        
        int[] key = stringToNumArray(keyString);    
        
        int[] state = new int[stateArraySize];
        stateShuffle(state, key);

        
        int[] answerAsNum = numberGen(state, randNumCount);
        
        String[] binaryString = numberToBinary(answerAsNum, bitSize);
        
        String message = "XNV@H";
        int[] messageArray = stringToNumArray(message);
        String[] messageString = numberToBinary(messageArray, bitSize);
        
        String[] answerArray = new String[binaryString.length];        
        if( binaryString.length == messageString.length)
        {

            for(int i = 0; i < answerArray.length; i++)
            {
                answerArray[i] = binaryXOR(binaryString[i], messageString[i]);
            }
        }
        
        int[] intOutput = binaryStringToInt(answerArray);
        
        System.out.println(intArrayToString(intOutput));
        
    }
    
    public static String extendKey(String key, int arraySize)
    {
        /*
        Description:    This method takes in a string, key, and extends by 
                    concatenating copies of the string until it reaches 
                    length arraySize.
        */
        
        String fullKey = "";
        while (fullKey.length() < arraySize)
        {
            fullKey += key;
        }        
        fullKey = fullKey.substring(0, arraySize);
        
        return fullKey;
    }
    
    public static int[] stringToNumArray(String pass)
    {
        /*
        Description:    This method takes in a string, and converts each
                    character to its equivalent integer.
                    Ex: A = 1, D = 4, Z = 26
                        This method currently assumes that the characters 
                    are captial letters only.
        */
        
        int[] output = new int[pass.length()];    
        for(int i = 0; i < output.length; i++)
        {
            output[i] = pass.charAt(i) - 64;
        }
        
        return output;
    }
    
    public static void stateShuffle(int[] state, int[] key)
    {
        /*
        Description:    This method shuffles the state array, according
                    to the RC4 algorithm.
        */

        for(int i = 0; i < state.length; i++)   //Initialize the state array
        {
            state[i] = i;
        } 
        
        int j = 0;
        for(int i = 0; i < state.length; i++)
        {
            j = (j + state[i] + key[i]) % state.length;
            int temp = state[i];
            state[i] = state[j];
            state[j] = temp;
        }
        
    }
    
    public static int[] numberGen(int[] state, int size)
    {
        /*
        Description:    This method generates (size) random numbers from the
                    given state array.
        */
        
        int j = 0;
        int i = 0;
        int[] outputNumList = new int[size];
        while (i < size)
        {
            i++;
            if(i > size) { break;}
            j = (j + state[i]) % state.length;      
            int temp = state[i];
            state[i] = state[j];
            state[j] = temp;
            outputNumList[i-1] = state[(state[i] + state[j]) % state.length];
        }
        
        return outputNumList;
    }
    
    public static String[] numberToBinary(int[] numList, int bitSize)
    {
        /*
        Description:    This method converts an array of integers 
                    into binary numbers of the given bit size.
        */
        String[] binaryString = new String[numList.length];
        for (int count = 0; count < binaryString.length; count ++)
        {
            binaryString[count] = Integer.toBinaryString(numList[count]);
            if (binaryString[count].length() < bitSize)
            {
                int lengthDifference = bitSize - binaryString[count].length();
                String lengthFixed = new String(new char[lengthDifference]).replace("\0", "0");
                binaryString[count] = lengthFixed + binaryString[count];
            }
        }
        
        return binaryString;
    }

    public static String binaryXOR(String num1, String num2)
    {   
        /*
        Description:    This method caluclates the binary XOR value
                    for two given strings.
        */
        
        return Integer.toBinaryString(Integer.parseInt(num1, 2) ^ Integer.parseInt(num2, 2));
    }

    public static int[] binaryStringToInt(String[] binary)
    {
        /*
        Description:    This method converts the binary values back to integer
        */
        
        int[] output = new int[binary.length];
        for(int i = 0; i < output.length; i++)
        {
            output[i] = Integer.parseInt(binary[i], 2);
        }
        return output;
    }
    
    public static String intArrayToString(int[] numbers)
    {
        /*
        Description:    This method converts the integer array to one String.
        */        
        String output = "";
        for(int i: numbers)
        {
            output += String.valueOf((char)(i + 64));
        }
        
        return output;
    }
}