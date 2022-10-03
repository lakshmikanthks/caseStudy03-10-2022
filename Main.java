package com.gl.caseStudy2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static String salesTaxCalculation(double billAmount) {
		double taxAmount = 0.0;
        if (billAmount <= 1000) {
            taxAmount = billAmount * 0.125;
        } else if (billAmount <= 2500) {
            taxAmount = billAmount * 0.10;
        } else {
            taxAmount = billAmount * 0.075;
        }
        String tax = floatToString(taxAmount);
        return tax;
	}
	 
	 private static String floatToString(double value) {
		 String formattedValue = String.format("%.2f", value);
	     return formattedValue;
	 }
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		List<OrderedItem> orderedList = new ArrayList<OrderedItem>();
		System.out.println("Enter Customer Name: ");
		String cName = s.nextLine();
		System.out.println("Enter the number of items purchased by customer: ");
		int noItemPurchased = Integer.parseInt(s.nextLine());
		if (noItemPurchased <= 0) {
            System.out.println("Invalid Output");
            System.exit(-1);
        }
		else {
		for(int i=0;i<noItemPurchased;i++) {
			System.out.println("Enter name and quantity (comma separate format) of purchased item no. "+(i+1));
			String details = s.nextLine();
			String detailsSplit[] = details.split(",");
			SnackItem snackI = ItemData.getItem(detailsSplit[0]);
			if(ItemData.isAvailable(snackI.getItemName())) {
				int qty = Integer.parseInt(detailsSplit[1]);
	            int dRate = Integer.parseInt(snackI.getDiscountRate());
	            double dQty = Double.parseDouble(snackI.getDiscountQty());
	            double price = Double.parseDouble(snackI.getRate()) * qty;
	            double discount = 0.0;
	            if(qty>=dRate) {
	            	discount = price * (dQty/100);
	            	price = price - discount;
	            }
	            String qtyO = detailsSplit[1];
	           OrderedItem orderedItem = new OrderedItem(snackI.getItemName(),snackI.getRate(),qtyO,floatToString(discount),floatToString(price));
	           orderedList.add(orderedItem);
			}
			else {
				System.out.println("Item not available");
			}
		 }// end of for
        }
		String datePattern = "dd-MM-yyyy";
        String date = new SimpleDateFormat(datePattern).format(new Date());
        
        System.out.println("\nCustomer Name:"+cName+"\t\t\tDate:"+date);
		String output=String.format("%-10s %-10s %-10s %-10s %-10s %-10s", "ITEM" ,"RATE","QUANTITY","PRICE","DISCOUNT","AMOUNT");
		System.out.println(output);
		System.out.println();
		Double billAmount=0.0;
		for (OrderedItem i:orderedList) {
			String name=i.getItemName();
			String r=i.getRate();
			String q=i.getOrderQty();
			SnackItem sn=ItemData.getItem(name);
			Double price=Double.parseDouble(sn.getRate())*Double.parseDouble(q);
			String dis=i.getDiscountAmount();
			String amount=i.getPayAmount();
			billAmount+= Double.parseDouble(amount);
			String output1=String.format("%-10s %-10s %-10s %-10s %-10s %-10s", name,r,q,price,dis,amount);
			System.out.println(output1);
		}
		String tax=salesTaxCalculation(billAmount);
		Double total=billAmount+Double.parseDouble(tax);
		System.out.println("\n\t\t\t\tBill Amount:"+billAmount);
		System.out.println("\t\t\t\tAdd: Sales Tax:"+tax);
		System.out.println("\t\t\t\tFinal Amount:"+total);
	}

}
