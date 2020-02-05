package learn;

/**
 * Hello world!
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.*;

public class App 
{
    public static void main( String[] args )
    {
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();

            String vppServiceConfig = "https://vpp.itunes.apple.com/WebObjects/MZFinance.woa/wa/VPPServiceConfigSrv";

            HttpGet getRequest = new HttpGet(vppServiceConfig);
            //getRequest.addHeader("accept", "application/json");
    
            HttpResponse response = httpClient.execute(getRequest);
    
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                   + response.getStatusLine().getStatusCode());
            }
    
            BufferedReader br = new BufferedReader(
                             new InputStreamReader((response.getEntity().getContent())));
    
            String output;
            System.out.println("Output from Server .... \n");
            // while ((output = br.readLine()) != null) {
            //     System.out.println(output);
            // }
            
            //Coverting complete buffered reader to string
            output = br.lines().collect(Collectors.joining());

            byte jsonData[] = output.getBytes();

            //Create ObjectMapper instance
            ObjectMapper objMapper = new ObjectMapper();

            //read JSON like DOM Parser
            JsonNode rootnode = objMapper.readTree(output);
            String sToken = "?sToken=eyJleHBEYXRlIjoiMjAyMC0wOS0xNVQyMzoxNToxNi0wNzAwIiwidG9rZW4iOiI5c3RtRTcvbWFuckxJZExqcU1DeXpjaXM2S1BxZ0p3blVha1JMditVN0swdlF1RTQvWDIwdkNYeXd2U3pwZXpZQk05d3B0M0Z0bVYrSExXYldlcVRWdUhmaWxzL050ajZ1OTgzdktPckFjbkNBOHlvN0VDV09IQ1o3bm1kSDFMK09zVzdJeThUVlZ5MkNWS0JXZGVOZEE9PSIsIm9yZ05hbWUiOiJOb3ZlbGwifQ==";
            JsonNode tempNode = rootnode.path("clientConfigSrvUrl");
            String vppClientConfig = tempNode.asText() + sToken;

            tempNode = rootnode.path("contentMetadataLookupUrl");
            String contentMetaData = tempNode.asText() + "?version=2&id=361309726&p=mdm-lockup&caller=MDM&platform=enterprisestore&cc=us&l=en";

            tempNode = rootnode.path("getVPPAssetsSrvUrl");
            String vppAssestSrv = tempNode.asText() + sToken;

            tempNode = rootnode.path("manageVPPLicensesByAdamIdSrvUrl");
            String manageVppLicenss = tempNode.asText() + sToken + "&associateSerialNumbers=serialNumber3&adamIdStr=281796108&pricingParam=STDQ";

            tempNode = rootnode.path("registerUserSrvUrl");
            String registerVPPUser = tempNode.asText() + sToken + "&clientUserIdStr=100002";

            tempNode = rootnode.path("retireUserSrvUrl");
            String retireVPPUser = tempNode.asText() + sToken + "&clientUserIdStr=100001";
           


            getRequest = new HttpGet(registerVPPUser);
            response = httpClient.execute(getRequest);
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            output = br.lines().collect(Collectors.joining());

            //System.out.println(output);

            jsonData = output.getBytes();
            rootnode = objMapper.readTree(jsonData);

            tempNode = rootnode.path("user");

            output = tempNode.toString();

            RegisterUser registerUser = objMapper.readValue(output,RegisterUser.class);

            System.out.println(registerUser.toString());

            PostgresConn pgconn = new PostgresConn();

            System.out.println("Result : " + pgconn.insertUserDetails(registerUser));


            httpClient.getConnectionManager().shutdown();
    
          } catch (ClientProtocolException e) {
        
            e.printStackTrace();
    
          } catch (IOException e) {
        
            e.printStackTrace();
          }
        }
}
