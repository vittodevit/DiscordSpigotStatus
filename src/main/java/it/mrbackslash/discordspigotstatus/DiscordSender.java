package it.mrbackslash.discordspigotstatus;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class DiscordSender {
    private String pip, pwebhook;
    DiscordSender(String ip, String webhook){
        pip = ip;
        pwebhook = webhook;
    }

    public boolean sendOn(){
        Unirest.setTimeouts(0, 0);
        try{
            //TODO: finish sendon!
            HttpResponse<String> response = Unirest.post(
                    ""
            )
                    .header("Content-Type", "application/json")
                    .body("")
                    .asString();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean sendOff(){
        //TODO: Finish sendoff
        return true;
    }
}
