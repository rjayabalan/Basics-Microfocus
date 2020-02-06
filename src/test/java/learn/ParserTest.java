package learn;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;

import org.junit.Test;

public class ParserTest{

    private Parser parser = new Parser();

    @Test
    public void testGetRegisterVPPUserUrl() throws Exception{

        String expected = "https://vpp.itunes.apple.com/mdm/registerVPPUserSrv";

        BufferedReader br = new BufferedReader(new FileReader("D:\\FirstEver Java Exercise\\learn\\src\\test\\java\\learn\\res\\jsonresponse1")); 

        String outfile = br.lines().collect(Collectors.joining());

        String actual = parser.getRegisterVPPUserUrl(outfile.getBytes());

        br.close();

        assertEquals( expected, actual);
    }

    @Test
    public void testGetUser() throws Exception{
        
        String expected = "RegisterUser [clientUserIdStr=100004, inviteCode=c09e96a19c4c438389cc1f52e3f26a86, inviteUrl=https://buy.itunes.apple.com/WebObjects/MZFinance.woa/wa/associateVPPUserWithITSAccount?cc=us&inviteCode=c09e96a19c4c438389cc1f52e3f26a86&mt=8, status=Registered, userId=87646589]";

        BufferedReader br = new BufferedReader(new FileReader("D:\\FirstEver Java Exercise\\learn\\src\\test\\java\\learn\\res\\jsonresponse2")); 

        String outfile = br.lines().collect(Collectors.joining());

        RegisterUser user = parser.getUser(outfile.getBytes());

        String actual = user.toString();

        br.close();
        
        assertEquals(expected, actual);


    }

}