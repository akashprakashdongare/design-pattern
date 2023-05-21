package main.solid;

class Document{

}

interface Machine{
    void print(Document document);
    void scan(Document document);
    void fax(Document document) throws Exception;
}

class MultifunctionPrinter implements Machine{

    @Override
    public void print(Document document) {
        //
    }

    @Override
    public void scan(Document document) {
        //
    }

    @Override
    public void fax(Document document) {
        //
    }

}

class OldFashinPrinter implements Machine{

    @Override
    public void print(Document document) {
        //
    }

    @Override
    public void scan(Document document) {
       //
    }

    @Override
    public void fax(Document document) throws Exception {
        throw new Exception();
    }
    
}

interface Printer{
    void print(Document document);
}

interface Scanner{
    void scan(Document document);
}

class JustAPrint implements Printer{

    @Override
    public void print(Document document) {
        //
    }
    
}

class Photocopier implements Printer, Scanner{

    @Override
    public void scan(Document document) {
        //
    }

    @Override
    public void print(Document document) {
        //
    }
    
}

interface MultifunctionDevice extends Printer, Scanner{

}

class MultifunctionMachine implements MultifunctionDevice{

    private Scanner scanner;
    private Printer printer;

    public MultifunctionMachine(Scanner scanner, Printer printer){
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document document) {
        printer.print(document);
    }

    @Override
    public void scan(Document document) {
        scanner.scan(document);
    }

}
public class InterfaceSegregation {
    
}
