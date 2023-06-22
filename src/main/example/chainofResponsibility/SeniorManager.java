package main.example.chainofResponsibility;

public class SeniorManager extends RequestHandler {

    public SeniorManager() {
        super("Senior Manager");
    }

    @Override
    public void setNext(RequestHandler handler) {
        this.nexHandler = handler;
    }

    @Override
    public void approve(int id) {
        if(id >= 21 && id <= 40)
        System.out.println("Senior Manager : Request approved");
        else
        super.approve(id);
    }
    
    
}
