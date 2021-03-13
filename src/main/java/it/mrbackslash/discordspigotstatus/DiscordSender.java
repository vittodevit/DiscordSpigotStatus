package it.mrbackslash.discordspigotstatus;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DiscordSender {
    private String pwebhook, pip, serverOn, serverOff;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddTHH:mm:00.000Z"); //discord timestamp format

    DiscordSender(String ip, String webhook){
        Unirest.setTimeouts(0, 0);
        pwebhook = webhook;
        pip = ip;
        //read templates
        File son = new File(getClass().getResource("server-on.json").getFile());
        File soff = new File(getClass().getResource("server-off.json").getFile());
        try{
            serverOn = FileUtils.readFileToString(son, StandardCharsets.UTF_8);
            serverOff = FileUtils.readFileToString(soff, StandardCharsets.UTF_8);
        }catch (Exception e){}
    }



    public boolean sendOn(){
        //get current timestamp and format to discord type
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String timestampString = dateFormat.format(ts);
        //http post request
        try{
            HttpResponse<String> response = Unirest.post(
                    pwebhook
            )
                    .header("Content-Type", "application/json")
                    .body(String.format(serverOn, pip, timestampString))
                    .asString();
            //TODO: NOT TESTED
            if(response.getStatus() == 204){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public boolean sendOff(){
        //get current timestamp and format to discord type
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String timestampString = dateFormat.format(ts);
        //http post request
        try{
            HttpResponse<String> response = Unirest.post(
                    pwebhook
            )
                    .header("Content-Type", "application/json")
                    .body(String.format(serverOff, pip, timestampString))
                    .asString();
            //TODO: NOT TESTED
            if(response.getStatus() == 204){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
