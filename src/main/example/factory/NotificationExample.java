package main.example.factory;

import java.util.HashMap;
import java.util.Map;

interface Notification{
    public void notifyUser(String name);
}
class SMSNotification implements Notification{

    @Override
    public void notifyUser(String name) {
        System.out.println(" Hey " + name 
        + " this notification send from SMS service..");
    }
    
}
class EmailNotification implements Notification{

    @Override
    public void notifyUser(String name) {
        System.out.println(" Hey " + name 
        + " this notification send from Email service..");
    }
    
}
class PushNotification implements Notification{

    @Override
    public void notifyUser(String name) {
        System.out.println(" Hey " + name 
        + " this notification send from Push service..");
    }
    
}
enum NotificationType{
    SMS, EAMIL, PUSH
}
class NotificationFactory{

    Map<NotificationType, Notification> factoryMap = new HashMap<>();

    public Notification factory(NotificationType type){
        if(null == factoryMap.get(type)){
            Notification notification = null;
            switch(type){
                case SMS:
                 notification = new SMSNotification();
                 break;
                case EAMIL:
                 notification = new EmailNotification();
                 break;
                case PUSH:
                 notification = new PushNotification();
                 break;

            }
            factoryMap.put(type, notification);
        }
        return factoryMap.get(type);
    }
}
public class NotificationExample {
    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory();

        Notification email = factory.factory(NotificationType.EAMIL);
        System.out.println("Email object hash code : " + email.hashCode());
        email.notifyUser("John");

        System.out.println();

        Notification push = factory.factory(NotificationType.PUSH);
        System.out.println("Push object hash code : " + push.hashCode());
        push.notifyUser("Jane");

        System.out.println();

        Notification email1 = factory.factory(NotificationType.EAMIL);
        System.out.println("Email 1 hash code : " + email1.hashCode());
        email1.notifyUser("Jane");
    }
}
