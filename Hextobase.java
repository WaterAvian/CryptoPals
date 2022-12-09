import java.lang.Math;

public class Hextobase {
    public String hex;
    public String base;

    static public String hexToBase(String hex)
    {
        return binaryToBase(hexToBinary(hex));
    }

    static public String baseToHex(String base)
    {
        return null;
    }

    static private byte[] hexToBinary(String hex)
    {
        byte[] binary = new byte[hex.length()];
        int a = 10, b = 11, c = 12, d = 13, e = 14, f =15;
        for(int i=0; i < hex.length(); i++)
        {
            char ch = hex.charAt(i);

            switch (ch) {
                case 'a' -> {
                    binary[i] = (byte) a;
                    continue;
                }
                case 'b' -> {
                    binary[i] = (byte) b;
                    continue;
                }
                case 'c' -> {
                    binary[i] = (byte) c;
                    continue;
                }
                case 'd' -> {
                    binary[i] = (byte) d;
                    continue;
                }
                case 'e' -> {
                    binary[i] = (byte) e;
                    continue;
                }
                case 'f' -> {
                    binary[i] = (byte) f;
                    continue;
                }
            }

                binary[i] = Byte.parseByte(String.valueOf(ch));
        }
        reverseArray(binary);
        return binary;
    }

    static private String binaryToBase(byte[] arr)
    {
        StringBuilder base = new StringBuilder();
        byte[] baseBytes = new byte[arr.length];
        int placeholder;
        int carry = 0;
        int j = 0;
        for (int i = 0; i < arr.length; i+=2, j++) {
            placeholder = carry;
            System.out.println("placeholder init: "+ placeholder);
            carry = 0;
            placeholder += (int)arr[i];
            System.out.println("Placeholder <- arr[i]: " + placeholder);

            if (i != arr.length - 1) {
                placeholder += (int) (arr[i+1] * 16);
                System.out.println("placeholder <- [i+1]: " + placeholder);
            }

            if (placeholder > 63)
            {
                carry = placeholder / 64;
                System.out.println("Placeholder: "+placeholder);
                placeholder = placeholder % 64;
                System.out.println("Placeholder after mod: "+placeholder);
                System.out.println("carry >63: "+ carry);
                System.out.println("placeholder after %: " + placeholder);
            }
            System.out.println(placeholder +" j: "+ j);
            System.out.println("byte placeholder: " + (byte)placeholder);
            baseBytes[j] = (byte)placeholder;
        }
        if(baseBytes.length != 1) {baseBytes[j] = (byte)carry;} //Interesting, j is incremented after i > condition. So no [j+1]

        for(byte b : baseBytes){
            System.out.println("test "+b);
        }

        for (byte baseByte : baseBytes) {
            if (((int) baseByte) < 26) {
                base.append((char) (((int) baseByte) + 65));

            } else if (((int) baseByte) < 52) {

                base.append((char) (((int) baseByte) + 65));

            } else if (((int) baseByte) < 62) {

                base.append((char) (((int) baseByte) - 4));

            } else {
                switch ((int) baseByte) {
                    case 62 -> {
                        base.append("+");
                        continue;
                    }
                    case 63 -> base.append("/");
                }
            }
        }
        return base.reverse().toString();

    }

    /* OVERFLOW ISSUES : DEPRECIATED <!>
    static private int countBinaryHex(byte[] arr)
    {
        int count = 0;
        int power = 0;
        for (byte b : arr) {
            count += ((int)b) * ((int)Math.pow((16), power));
            power++;
        }
        return count;
    }
     */

    /*Reversing array is necessary as indexing i=0 starts at the most significant digit. Rest of functions work on assumption that 0 index is least significant and carry over excess*/
    static byte[] reverseArray(byte[] arr)
    {
        byte temp;
        for(int i=0, j=arr.length - 1; i < arr.length / 2; i++, j--)
        {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    public static void main(String[] args)
    {
        System.out.println(hexToBase("1f"));
    }
}