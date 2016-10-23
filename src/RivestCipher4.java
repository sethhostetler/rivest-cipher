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
        final String keyString = "DISCRETEDISCRETEDISCRETEDISCRETE";
        System.out.println(keyString);        
        
        final Map<Character, Integer> map;        
        map = new HashMap<>();
        map.put('@', 0);
        map.put('A', 1);
        map.put('B', 2);
        map.put('C', 3);
        map.put('D', 4);
        map.put('E', 5);
        map.put('F', 6);
        map.put('G', 7);
        map.put('H', 8);
        map.put('I', 9);
        map.put('J', 10);
        map.put('K', 11);
        map.put('L', 12);
        map.put('M', 13);
        map.put('N', 14);
        map.put('O', 15);
        map.put('P', 16);
        map.put('Q', 17);
        map.put('R', 18);
        map.put('S', 19);
        map.put('T', 20);
        map.put('U', 21);
        map.put('V', 22);
        map.put('W', 23);
        map.put('X', 24);
        map.put('Y', 25);
        map.put('Z', 26);
        map.put('_', 31);
        

        
        int[] key = new int[keyString.length()];
        for(int i = 0; i < keyString.toCharArray().length; i++)
        {
//            final Integer val;
            key[i] = map.get(keyString.toCharArray()[i]);
            /*
            if(val == null)
            {   
                System.out.println("Error!");
            }
            else
            {
                key[i] = val;
            }
            */
        }
        
//        for(int i = 0; i < key.length; i++)
//        {
//            System.out.print(key[i]+" ");
//        }         
//        System.out.println("\n*** ***");
        
        
        int bitSize = 5;
        int base = 2;
        int stateArraySize = (int) Math.pow(2, bitSize);
        int[] state = new int[stateArraySize];
        for(int i = 0; i < state.length; i++)
        {
            state[i] = i;
        }        

        
        int j = 0;
        for(int i = 0; i < stateArraySize; i++)
        {
            //System.out.println("J: " + j + " state[" + i + "]: " + state[i] + " and key[" + i + "]: " + key[i]);
            j = (j + state[i] + key[i]) % stateArraySize;
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
        
        System.out.println();
        j = 0;
        int i = 0;
        int randNumCount = 5;        
        int[] answerAsNum = new int[randNumCount+1];
        while (i < 5)//randNumCount-1)
        {
            i++;
            //System.out.println("i: " + i + "  j: " + j);
            if(i > randNumCount) { break;}
            j = (j + state[i]) % stateArraySize;
            //System.out.println("i: " + i + "  j: " + j);            
            int temp = state[i];
            state[i] = state[j];
            state[j] = temp;
            //System.out.println("State[" + i + "]: " + state[i] + "; State[" + j + "]: " + state[j]);
            answerAsNum[i] = state[(state[i] + state[j]) % stateArraySize];
            //System.out.println(answerAsNum[i]);
            //answerAsNum[i] = convertToBinary(answerAsNum[i]);
        }
//        System.out.println();
//        for (int count = 0; count < state.length; count ++)
//        {
//            System.out.println(state[count]);
//        }
        
        System.out.println("Resulting numbers:");
        for (int count = 1; count < answerAsNum.length; count ++)
        {
            System.out.println(answerAsNum[count] + " ");
        }
        
        String[] binaryString = new String[answerAsNum.length];
        for (int count = 1; count < binaryString.length; count ++)
        {
            binaryString[count] = Integer.toBinaryString(answerAsNum[count]);
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
    }
    
    public static int[] stringToNumArray(String pass)
    {
        int[] output = new int[pass.length()];
        final Map<Character, Integer> map;        
        map = new HashMap<>();
        map.put('@', 0);
        map.put('A', 1);
        map.put('B', 2);
        map.put('C', 3);
        map.put('D', 4);
        map.put('E', 5);
        map.put('F', 6);
        map.put('G', 7);
        map.put('H', 8);
        map.put('I', 9);
        map.put('J', 10);
        map.put('K', 11);
        map.put('L', 12);
        map.put('M', 13);
        map.put('N', 14);
        map.put('O', 15);
        map.put('P', 16);
        map.put('Q', 17);
        map.put('R', 18);
        map.put('S', 19);
        map.put('T', 20);
        map.put('U', 21);
        map.put('V', 22);
        map.put('W', 23);
        map.put('X', 24);
        map.put('Y', 25);
        map.put('Z', 26);
        map.put('_', 31);
        

        
        int[] key = new int[pass.length()];
        for(int i = 0; i < pass.toCharArray().length; i++)
        {
//            final Integer val;
            key[i] = map.get(pass.toCharArray()[i]);
            /*
            if(val == null)
            {   
                System.out.println("Error!");
            }
            else
            {
                key[i] = val;
            }
            */
        }
        return output;
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