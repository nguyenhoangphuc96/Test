package vn.com.lacviet.lacviethpsmuseummanagementapp;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class FileDecryption {

    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[] { 'S', 'n', 'd', 'A', 'p', 'p','s','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

    public  String decrypt(String encryptedData) {
        //encryptedData="PDvLeKmmKUcUQd/zKZ5b2JPRCXHJwu4dVZhaZJCrRK4JRvBP3IhRpizk6qCLcaUmhoDGR+QUeS1fjHdWujZTGQ==";
        String decryptedValue=null;
        //int flags= Base64.NO_WRAP | Base64.URL_SAFE;
        if("ON".equals("ON")){
            try{
                Key key = generateKey();
                Cipher c = Cipher.getInstance(ALGO);
                c.init(Cipher.DECRYPT_MODE, key);
                // byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
                byte[] decordedValue = Base64.decode(encryptedData);
                byte[] decValue = c.doFinal(decordedValue);
                decryptedValue = new String(decValue);}
            catch(Exception e){
                e.printStackTrace();
            }
        }else{
            decryptedValue=encryptedData;
        }

        //Log.d("decrypted : ", decryptedValue);
        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

}