package com.anand.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.anand.global.GlobalData;
import com.anand.model.Product;
import com.anand.repository.ProductRepository;
import com.anand.service.ProductService;


@Controller
public class CartController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	   @GetMapping("/addToCart/{id}")
	    public String addToCart(@PathVariable int id) {
	        Optional<Product> productOptional = productService.getProductById(id);
	        
	        productOptional.ifPresent(product -> {
	            GlobalData.cart.add(product);
	        });
		return "redirect:/shop";
	}
	   @GetMapping("/cart")
	   public String cartGet(Model model) {
		   model.addAttribute("cartCount",GlobalData.cart.size());
		   model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		   model.addAttribute("cart",GlobalData.cart);
		   
		return "cart";
		   
	   }
	   @GetMapping("/cart/removeItem/{index}")
		public String cartItemRemove(@PathVariable int index) {
			GlobalData.cart.remove(index);
			return "redirect:/cart";
		}
		@GetMapping("/checkout")
		public String checkout(Model model) {
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
			
		}
		@PostMapping("/payNow")
		public String processPayment(Model model) {
		    List<Product> productList = productRepository.findAll();
		    model.addAttribute("product", productList);
		    return "razorpayPaymentPage";
		}	
		

}
