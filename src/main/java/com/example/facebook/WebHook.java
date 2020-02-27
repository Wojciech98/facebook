package com.example.facebook;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController()
@RequestMapping("webhook")
public class WebHook {

    private final String PAGE_TOKEN ="EAACymOV645gBADMKXPXvoRtYyL14VtMQ6K8AJKteZAADdaLfYJCx5o9XVhlCrIUvE4HiumI96mWJT9ZAvMGl4kZAZAVgVn02ZByn3EWtAMQ0QBiSyNd6u9usXtvsBG6ZCLNHbO0cBr0wqEMX2LeuPZABuBY8kZB9V8zaqsJDFMegctCIOYiaZBMsBvISEM7M2EZC0ZD";
    private final String VERIFY_TOKEN="eae8aa5a15cf02a6b05d2043dbfb859c";
    //this is for reply messages
    private final String FB_MSG_URL="https://graph.facebook.com/v2.6/me/messages?access_token="
            + PAGE_TOKEN;

    //logger to watch whats happening in our bot
    private final Logger logger = LoggerFactory.getLogger(WebHook.class);
    private final RestTemplate template = new RestTemplate();


    //This is necessary for register a webhook in facebook
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String get(@RequestParam(name = "hub.verify_token")String token,
                      @RequestParam(name = "hub.challenge")String challenge){
        if(token!=null && !token.isEmpty() && token.equals(VERIFY_TOKEN)){
            return challenge;
        }else{
            return "Wrong Token";
        }
    }

    //This method  reply all messages with: 'This is a test message'
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void post(@RequestBody FacebookHookRequest request){
        logger.info("Message from chat: {}",request);
        request.getEntry().forEach(e->{
            e.getMessaging().forEach(m->{
                String id = m.getSender().get("id");
                sendReply(id,"This is a test message");
            });
        });
    }

    private void sendReply(String id,String text){
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setMessage_type("text");
        response.getRecipient().put("id",id);
        response.getMessage().put("text",text);
        HttpEntity<FacebookMessageResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity(FB_MSG_URL,entity,String.class).getBody();
        logger.info("Message result: {}",result);

    }
}
