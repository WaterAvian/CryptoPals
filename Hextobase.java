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

                switch (ch){
                    case 'a':
                        binary[i] = (byte)a;
                        System.out.println("hexToBinary addition: "+binary[i]);
                        continue;
                    case 'b':
                        binary[i] = (byte)b;
                        System.out.println("hexToBinary addition: "+binary[i]);
                        continue;
                    case 'c':
                        binary[i] = (byte)c;
                        System.out.println("hexToBinary addition: "+binary[i]);
                        continue;
                    case 'd':
                        binary[i] = (byte)d;
                        System.out.println("hexToBinary addition: "+binary[i]);
                        continue;
                    case 'e':
                        binary[i] = (byte)e;
                        System.out.println("hexToBinary addition: "+binary[i]);
                        continue;
                    case 'f':
                        binary[i] = (byte)f;
                        System.out.println("hexToBinary addition: "+binary[i]);
                        continue;
                }

                binary[i] = Byte.parseByte(String.valueOf(ch));
            System.out.println("hexToBinary addition: "+binary[i]);
        }

        return binary;
    }

    static private String binaryToBase(byte[] arr)
    {
        String base = "";
        byte[] baseBytes = new byte[countBinaryHex(arr)/64 + 2];
        int placeholder;
        int carry = 0;
        for (int i = 0, j = 0; i < arr.length; i+=2, j++) {
            placeholder = carry;
            carry = 0;
            placeholder += (int)arr[i];
            if (i != arr.length - 1) { placeholder += (int) (arr[i+1] * 16); }

            if (placeholder > 63)
            {
                carry = placeholder / 64;
                placeholder = placeholder % 63;
            }
            baseBytes[j] = (byte)placeholder;
        }

        for(int i = 0; i< baseBytes.length; i++)
        {
            if( ((int) baseBytes[i]) < 26)
            {
                base += (char) ( ((int)baseBytes[i]) + 65);

            } else if ( ((int) baseBytes[i]) < 52) {

                base += (char) ( ((int)baseBytes[i]) + 65);

            } else if ( ((int) baseBytes[i]) < 62) {

                base += (char) ( ((int)baseBytes[i]) - 4);

            } else if ( ((int) baseBytes[i]) > 61) {
                switch ((int)baseBytes[i]){
                    case 62:
                        base += "+";
                        continue;
                    case 63:
                        base += "/";
                }
            }
            return base;
        }

        System.out.println("Something went wrong in binaryToBase()");
        return null;
    }

    static private int countBinaryHex(byte[] arr)
    {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count += (int)arr[i];
        }
        System.out.println("count is " + count);
        return count;
    }

    public static void main(String[] args)
    {
        System.out.println(hexToBase("fffcccc"));
    }
}