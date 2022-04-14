package data;


import org.junit.Assert;
import org.junit.Test;

public class CryptographyTest {
    @Test
    public void testEncryption(){
        String cipher = Cryptography.encryptPassword("hehehe");
        Assert.assertEquals("hehehe", Cryptography.decryptPassword(cipher));
    }

}