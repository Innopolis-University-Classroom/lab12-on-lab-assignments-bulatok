// your solution here
//fix all the errors including package errors
package ru.innopolis;

public interface Chain{
    void setNext(Chain nextChain);
    void write(Logg logg);
}

// base class
public static class Logg{
    // log message
    private String message;
    private String type;

    public Logg(String message, String type){
        this.message = message;
        this.type = type;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String newMessage){
        message = newMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
public class writeConsole implements Chain{
    private Chain nextChain;

    public void setNext(Chain nextChain){
        this.nextChain = nextChain;
    }

    public void write(Logg logg){
        if (logg.getType() == "Console"){
            System.out.println(logg.getMessage());
        }else{
            nextChain.write(logg);
        }
    }
}

public class writeFile implements Chain{
    private Chain nextChain;

    public void setNext(Chain nextChain){
        this.nextChain = nextChain;
    }

    public void write(Logg logg){
        if (logg.getType() == "File"){
            FileOutputStream outputStream = new FileOutputStream(fileName);
            outputStream.write(logg.getMessage().getBytes())
        }else{
            nextChain.write(logg);
        }
    }
}
public class writeServer implements Chain{
    private Chain nextChain;

    public void setNext(Chain nextChain){
        this.nextChain = nextChain;
    }

    public void write(Logg logg){
        if (logg.getType() == "Server"){
            System.out.println("log simulation from logger - " + logg.getMessage());
        }else{
            throw new IllegalArgumentException("unknow source");
        }
    }
}

public class TestProblem1{
    public static void main(String[] args){
        Chain ch1 = new writeConsole();
        Chain ch2 = new writeFile();
        Chain ch3 = new writeServer();

        ch1.setNext(ch2);
        ch2.setNext(ch3);

        // this is our request
        Logg logg = new Logg("log test", "server");

        ch1.write(logg);
    }
}