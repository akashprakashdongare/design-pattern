package main.example.chainofResponsibility;

public class Manager extends RequestHandler {

    public Manager() {
        super("Manager");
    }

    @Override
    public void setNext(RequestHandler handler) {
        this.nexHandler = handler;
    }

    @Override
    public void approve(int id) {
        if(id >= 1 && id <= 20)
        System.out.println("Manager : Request approved");
        else
        super.approve(id);
    }

    
    
}
