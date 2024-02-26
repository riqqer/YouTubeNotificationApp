import java.util.ArrayList;
import java.util.List;

interface Channel {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers();
}

class YouTubeChannel implements Channel {
    private List<Subscriber> subscribers = new ArrayList<>();
    private String latestVideo;
    private String channelName;

    public YouTubeChannel(String channelName){
        this.channelName = channelName;
    }

    public void uploadVideo(String latestVideo) {
        this.latestVideo = latestVideo;
        notifySubscribers();
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(channelName, latestVideo);
        }
    }
}

interface Subscriber {
    void update(String channelName, String latestVideo);
}

class YouTubeUser implements Subscriber {
    private final String userName;

    public YouTubeUser(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(String channelName, String latestVideo) {
        System.out.println("Hey @" + userName + "! " + channelName + " uploaded: \"" + latestVideo + "\"");
    }
}

public class YouTubeNotificationApp {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel("IsshoLeeEdits");

        YouTubeUser user1 = new YouTubeUser("Beka228");
        YouTubeUser user2 = new YouTubeUser("supressedmayhem5306");
        YouTubeUser user3 = new YouTubeUser("mwndjddjdhdbbf8408");
        YouTubeUser user4 = new YouTubeUser("ChipiChipiChapaChapa");
        YouTubeUser user5 = new YouTubeUser("Nagibatorpremiumprokick_3000");
        YouTubeUser user6 = new YouTubeUser("joyurizzzz");

        channel.subscribe(user1);
        channel.subscribe(user2);
        channel.subscribe(user3);
        channel.subscribe(user4);
        channel.subscribe(user5);
        channel.subscribe(user6);

        channel.uploadVideo("What if Earth has suddenly stopped?");

        channel.unsubscribe(user2);
        channel.unsubscribe(user5);

        channel.uploadVideo("Relax And Take Notes");
    }
}
