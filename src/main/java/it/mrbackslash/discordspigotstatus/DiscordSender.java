package it.mrbackslash.discordspigotstatus;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class DiscordSender {
    private String pwebhook, serverOnFormatted, serverOffFormatted;
    DiscordSender(String ip, String webhook){
        Unirest.setTimeouts(0, 0);
        pwebhook = webhook;
        //read templates
        File son = new File(getClass().getResource("server-on.json").getFile());
        File soff = new File(getClass().getResource("server-off.json").getFile());
        try{
            String serverOn = FileUtils.readFileToString(son, StandardCharsets.UTF_8);
            String serverOff = FileUtils.readFileToString(soff, StandardCharsets.UTF_8);
            serverOnFormatted = String.format(serverOn, ip);
            serverOffFormatted = String.format(serverOff, ip);
        }catch (Exception e){}
    }



    public boolean sendOn(){
        try{
            HttpResponse<String> response = Unirest.post(
                    pwebhook
            )
                    .header("Content-Type", "application/json")
                    .body(serverOnFormatted)
                    .asString();
            //TODO: NOT TESTED
            if(response.toString().length() == 0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public boolean sendOff(){
        try{
            HttpResponse<String> response = Unirest.post(
                    pwebhook
            )
                    .header("Content-Type", "application/json")
                    .body(serverOffFormatted)
                    .asString();
            if(response.toString().length() == 0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
