package main.example.chainofResponsibility;

public class Director extends RequestHandler {

    public Director() {
        super("Director");
    }

    @Override
    public void setNext(RequestHandler handler) {
        this.nexHandler = handler;
    }

    @Override
    public void approve(int id) {
        if(id >= 41 && id <= 80)
        System.out.println("Director : Request approved");
        else
        super.approve(id);
    }

    
    
}
