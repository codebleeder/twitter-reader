package com.twitter.reader;

import twitter4j.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.twitter.reader.HtmlGenerator.*;

public class Twitter4j {

    private Twitter twitter;

    public Twitter4j() {
        // gets Twitter instance with default credentials
        twitter = new TwitterFactory().getInstance();
    }

    private List<Status> getTopTenStatus(){

        User user = null;
        try {
            user = twitter.verifyCredentials();
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
            return statuses.stream()
                    .sorted((s1, s2)-> Long.compare(s2.getFavoriteCount(), s1.getFavoriteCount()))
                    .sorted((s1, s2)-> Long.compare(s2.getRetweetCount(), s1.getRetweetCount()))
                    .collect(Collectors.toList());
            //return statuses;
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
       return null;
    }

    private String statusToEmbed(Status status){
        String url = "https://twitter.com/"+status.getUser().getName()+"/status/"+status.getId();
        try {
            OEmbedRequest oEmbedRequest = new OEmbedRequest(status.getId(), url);
            oEmbedRequest.setHideMedia(false);
            oEmbedRequest.setMaxWidth(550);
            var oEmbed = twitter.getOEmbed(oEmbedRequest);
            return oEmbed.getHtml();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return "";
    }
    public  String generateHtmlContentFromList(List<Status> statuses){
        return statuses.stream()
                .map(s->statusToEmbed(s))
                .reduce("", (s1, s2)-> prepareContent(s1) + prepareContent(s2)) + script;
    }
    public String getHtml(){
         List<Status> statuses = getTopTenStatus();
         String content = generateHtmlContentFromList(statuses);
         return HtmlGenerator.wrapWithHtmlBoilerplate(content);
    }

    public static void main(String[] args) {
        try {
            // gets Twitter instance with default credentials
            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
            /*for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }*/
            List<Status> statuses2 = twitter.getUserTimeline("Being_Humor");
            for (Status status : statuses2) {
                System.out.println("favorite count: " + status.getFavoriteCount());
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }

    }

}
