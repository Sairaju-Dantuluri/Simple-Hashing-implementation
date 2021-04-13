import java.util.*; 
  
class convert
{ 
    static String strToBinary(String s) // this function is to be called for string to binary string.
    { 
        int n = s.length(); 
  		String ans="";
        for (int i = 0; i < n; i++)  
        {  
            int val = Integer.valueOf(s.charAt(i));  
            String bin = ""; 
            while (val > 0)  
            { 
                if (val % 2 == 1) 
                { 
                    bin += '1'; 
                } 
                else
                    bin += '0'; 
                val /= 2; 
            } 
            bin = reverse(bin); 
  			ans=ans+bin;
            //System.out.print(bin + " "); 
        } 
        return ans;
    } 
  
    static String reverse(String input)  
    { 
        char[] a = input.toCharArray(); 
        int l, r = 0; 
        r = a.length - 1; 
  
        for (l = 0; l < r; l++, r--) 
        { 
            // Swap values of l and r  
            char temp = a[l]; 
            a[l] = a[r]; 
            a[r] = temp; 
        } 
        return String.valueOf(a); 
    } 
} 
		
