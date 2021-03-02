package views.screen.add;


import java.io.FileNotFoundException;
import java.io.IOException;

import command.Command;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import listeners.AllListeners;
import listeners.ViewListenable;
import view.root.ConsoleMsg;
import view.root.SystemManagementViewable;

public class AddButtonCommand implements Command, SystemManagementViewable{ 
	private AddProductScreen add;
	public AddButtonCommand(AddProductScreen add) {
		this.add = add;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		HBox hbPhone = (HBox)add.getChildrenUnmodifiable().get(16);
		HBox hbPromotion = (HBox)add.getChildrenUnmodifiable().get(17);
		TextField[] tfAll = new TextField[5];
		for(int i = 0, j = 5 ; i < tfAll.length - 1 ; i++, j+=2) 
			tfAll[i] = (TextField)add.getChildrenUnmodifiable().get(j);
		tfAll[4] = (TextField)add.getChildrenUnmodifiable().get(14);
		ConsoleMsg.getMsg().setText(fireViewAddProduct(tfAll[0].getText(), tfAll[1].getText(), tfAll[2].getText().trim(), tfAll[3].getText().trim(),  tfAll[4].getText(), ((ComboBox<String>)hbPhone.getChildrenUnmodifiable().get(0)).getValue(), ((TextField)hbPhone.getChildrenUnmodifiable().get(1)).getText().trim(), ((RadioButton)hbPromotion.getChildrenUnmodifiable().get(0)).isSelected()));
		ConsoleMsg.getMsg().setColor();
	}
	
	private String fireViewAddProduct(String productName, String catalogNumber, String cost, String price, String customerName, String phoneNumberBegin, String phoneNumberEnd , boolean isPromotion) {
		for(ViewListenable vl : AllListeners.getAllListeners())
			try {
				return vl.fireViewAddProduct(productName, catalogNumber, cost, price, customerName, phoneNumberBegin, phoneNumberEnd, isPromotion);
			} catch (FileNotFoundException e) {
				ConsoleMsg.getMsg().setText("Failed read from file ");
				ConsoleMsg.getMsg().setColor();
			} catch (IOException e) {
				ConsoleMsg.getMsg().setText("Failed read from file ");
				ConsoleMsg.getMsg().setColor();
			}
		return null;
	}

	@Override
	public void registerListener(ViewListenable vl) {
		AllListeners.registerListener(vl);
	}
	@Override
	public void fireModelDeleteProduct(String msg) {
		// TODO Auto-generated method stub
		
	}
}
