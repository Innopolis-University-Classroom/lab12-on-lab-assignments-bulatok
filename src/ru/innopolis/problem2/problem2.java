// your solution here
//fix all the errors including package errors
package ru.innopolis;

public interface Command{
    void execute(int amount);
}
// reciever class
public class Stock{
    // for example BTC
    public void sell(int amount){
        System.out.printf("you've sold - %d BTC", amount);
    }
    public void buy(int amount){
        System.out.printf("you've bought - %d BTC", amount);
    }
}
public class Switch{

    public void execute(String commandName, int amount){
        if (commandName == null) throw new IllegalStateException("no command registered for " + commandName);

        if (commandName == "sell"){
            Command c = new SellCommand();
            c.execute(amount);
        }else if (commandName == "buy"){
            Command c = new BuyCommand();
            c.execute(amount);
        }
    }
}
public class BuyCommand implements Command{
    private final Stock stock;

    public BuyCommand(Stock stock){
        this.stock = stock;
    }

    @Override
    public void execute(int amount){
        stock.buy(amount);
    }
}

public class SellCommand implements Command{
    private final Stock stock;

    public SellCommand(Stock stock){
        this.stock = stock;
    }

    @Override
    public void execute(int amount){
        stock.sell(amount);
    }
}
public class TestProblem2{
    public static void main(String[] args){
        Stock stock = new Stock();

        Command sell = new SellCommand();
        Command buy = new BuyCommand();

        Switch sw = new Switch();

        sw.execute("buy", 100);
        sw.execute("sell", 100);
    }
}