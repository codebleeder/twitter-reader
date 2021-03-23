package com.twitter.reader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private final Twitter4j twitter4j;

    public HomeController() {
        twitter4j = new Twitter4j();
    }

    @GetMapping("/")
    public List<TweetDTO> hello() {
        //return new ArrayList<>(Arrays.asList(new TweetDTO("user", "tweet", 10, 10)));
        return twitter4j.getTopTenTweets();
    }

    @GetMapping("/home")
    public String getEmbeds(){

        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <blockquote class=\"twitter-tweet\" data-conversation=\"none\" data-cards=\"hidden\" data-width=\"220\"><p lang=\"hi\" dir=\"ltr\">Kisi aur ko pakdo ab \uD83D\uDE39</p>&mdash; Maithun Chakraborty (@Being_Humor) <a href=\"https://twitter.com/Being_Humor/status/1374044678471831554?ref_src=twsrc%5Etfw\">March 22, 2021</a></blockquote>\n" +
                "<script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>\n" +
                "    </body>\n" +
                "</html>";
    }

    @GetMapping("/home2")
    public String getHtml(){
        return twitter4j.getHtml();
    }
}
