package com.anand.global;

import java.util.ArrayList;
import java.util.List;

import com.anand.model.Product;

public class GlobalData {
	public static List<Product> cart;
	static {
		cart = new ArrayList<Product>();
	}

}
