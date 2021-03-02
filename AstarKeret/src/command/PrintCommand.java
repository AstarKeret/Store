package command;

public interface PrintCommand{
	//different command to print all product and print one product
	void turnOf();	//set the screen invisibale
	void turnOn();	//set the screen visibale
	void execute();	//load the data from the current map
}