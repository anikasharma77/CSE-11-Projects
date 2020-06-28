 public class ExampleTweets{
    User userNum1 = new User("java", "Java", 460300);

    /*link to tweet: https://twitter.com/OpenJDK/status/1245762987970813952
    All parts of the tweet are represented*/
    Tweet tweetNum1 = new Tweet("Targeted to JDK 15: JEP 378: Text Blocks (Standard): https://openjdk.java.net/jeps/378 #jdk15 #openjdk #java", userNum1, 72, "1245762987970813952");
   /*link to tweet: https://twitter.com/java/status/1249721570114797568
   Image in the tweet is not represented properly with this code*/
    Tweet tweetNum2 = new Tweet("Top developers agree: #Java 14 makes code super expressive. (via @Forbes)http://oracl.info/WoHE50zcD9B", userNum1, 205, "1249721570114797568");

    User userNum2 = new User("justinbieber", "Justin Bieber", 111200000);
    /*link to tweet: https://twitter.com/justinbieber/status/1250128587480391680
    All parts of the tweet are represented*/
    Tweet tweetNum3 = new Tweet("#intentionschallenge???", userNum2, 30600, "1250128587480391680" );

    User userNum3 = new User("kehlani", "Kehlani", 1600000);
    /*link to tweet: https:https://twitter.com/Kehlani/status/1250062953782657025
    All parts of the tweet are represented*/
    Tweet tweetNum4 = new Tweet("i miss the farmers market.", userNum3, 16300, "1250062953782657025");

    String user1TextValue = userNum1.toText();
    String user2TextValue = userNum2.toText();
    String user3TextValue = userNum3.toText();
    
    Boolean tweet1Boolean = tweetNum1.longerThan(tweetNum2);
    Boolean tweet3Boolean = tweetNum1.longerThan(tweetNum3);
    Boolean tweet4Boolean = tweetNum1.longerThan(tweetNum4);

    Boolean tweet2Likes = tweetNum1.moreLikes(tweetNum2);
    Boolean tweet3Likes = tweetNum1.moreLikes(tweetNum3);
    Boolean tweet4Likes = tweetNum1.moreLikes(tweetNum4);

    Tweet rt1 = tweetNum1.retweet(userNum1);
    Tweet rt2 = tweetNum3.retweet(userNum2);
    Tweet rt3 = tweetNum4.retweet(userNum3);

    String textVal1 = tweetNum1.toText();
    String textVal2 = tweetNum3.toText();
    String textVal3 =  tweetNum4.toText();

    String linkVal1 = tweetNum1.toLink();
    String linkVal2 = tweetNum3.toLink();
    String linkVal3 = tweetNum4.toLink();

}

class User{
    String username;
    String fullname;
    int followers;

    User(String username, String fullname, int followers){
        this.username = username; 
        this.fullname = fullname;
        this.followers = followers;
    }

    String toText(){
        return fullname + " @"+ username;
    }
}

class Tweet{
    String content;
    User user;
    int likes;
    String tweetID;


    Tweet(String content, User user, int likes, String tweetID){
        this.content = content; 
        this.user = user;
        this.likes = likes;
        this.tweetID = tweetID;
    }
    boolean longerThan(Tweet tweet2){
        boolean a = false;
        if (content.length() > tweet2.content.length()){
            a = true;
        }
        return a;
    }

    boolean moreLikes(Tweet tweet2){
        boolean a = false;
        if (likes > tweet2.likes){
            a = true;
        }
        return a;
    }
    Tweet retweet(User user1){
        Tweet tweet1 = new Tweet(content, user1, 0, tweetID);
        tweet1.tweetID = tweetID + "-rt";
        return tweet1;
    }

    String toText(){
        String newString = user.toText()+" : "+content+" : "+likes;
        return newString;
    }
    
    String toLink(){
        String newString = "https://twitter.com/"+user.username+"/status/"+tweetID;
        return newString;

    }
}

