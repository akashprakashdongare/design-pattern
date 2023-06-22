package main.example.chainofResponsibility;

public abstract class RequestHandler {
    String name;
    RequestHandler nexHandler;
    
    private RequestHandler(){}

    public RequestHandler(String name){ this.name = name; }

    public abstract void setNext(RequestHandler handler);

    public void approve(int id){
        if(null != this.nexHandler)
        this.nexHandler.approve(id);
        else
        System.out.println("Request can not be approved");
    }
    
}
