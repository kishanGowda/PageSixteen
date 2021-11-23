package com.example.pagesixteen.ApiPackage;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
public class LatestConversation {
    public int chat_userId;
    public String chat_userName;
    public String chat_orgId;
    public String chat_orgName;
    public String chat_userAvatar;
    public String chat_userRole;
    public String message;
    public String chat_type;
    public int chat_id;
    public String chat_createdAt;
    public String unreadMessage;
}
