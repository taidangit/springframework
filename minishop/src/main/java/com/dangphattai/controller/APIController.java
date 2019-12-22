package com.dangphattai.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dangphattai.entity.Cart;
import com.dangphattai.entity.Category;
import com.dangphattai.entity.JsonProduct;
import com.dangphattai.entity.Product;
import com.dangphattai.entity.ProductDetail;
import com.dangphattai.entity.Size;
import com.dangphattai.service.ProductService;
import com.dangphattai.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/api")
@SessionAttributes(names = {"username", "carts"})
public class APIController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ServletContext context;
	
	@GetMapping("/ajax-login")
	@ResponseBody
	public String login(@RequestParam String username, @RequestParam String pass, Model model, HttpSession session) {
		
		boolean isUserExists = userService.checkUser(username, pass);
		
		if(isUserExists) {
			int roleId = userService.getRoleId(username, pass);
			if(roleId == 1) {
				session.setAttribute("username", username);
				return "USER";
			} else if(roleId == 2) {
				session.setAttribute("username", username);
				return "ADMIN";
			}	
		} 
		
		return "";
	}
	
	@GetMapping("/addToCart")
	@ResponseBody
	public String addToCart(
			@RequestParam int quantity,
			@RequestParam String size,
			@RequestParam String product,
			@RequestParam String price,
			@RequestParam String image,
			HttpSession session) {
		
		if(session.getAttribute("carts") == null) {
			List<Cart> carts = new ArrayList<>();
			
			Cart cart = new Cart();
			cart.setImage(image);
			cart.setPrice(price);
			cart.setProduct(product);
			cart.setQuantity(quantity);
			cart.setSize(size);
			
			carts.add(cart);
			
			session.setAttribute("carts", carts);
			
			return carts.size()+"";
		} else {
			int position = findProductInCart(session, product, size);
			if(position == -1) {
				List<Cart> carts = (List<Cart>) session.getAttribute("carts");
				Cart cart = new Cart();
				cart.setImage(image);
				cart.setPrice(price);
				cart.setProduct(product);
				cart.setQuantity(quantity);
				cart.setSize(size);
				
				carts.add(cart);
				
				return carts.size()+"";
				
			} else {
				List<Cart> carts = (List<Cart>) session.getAttribute("carts");
				int quantityNew = carts.get(position).getQuantity() + quantity;
				carts.get(position).setQuantity(quantityNew);
				
				return carts.size()+"";
			}
		} 
	}
	
	private int findProductInCart(HttpSession session, String product, String size) {
		if(session.getAttribute("carts") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			for(int i=0; i < carts.size(); i++) {
				if(carts.get(i).getProduct().equals(product) && carts.get(i).getSize().equals(size)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	@GetMapping("/updateCart")
	public void updateCart(
			@RequestParam int quantity,
			@RequestParam String product,
			@RequestParam String size,
			HttpSession session) {
		
		if(session.getAttribute("carts") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			int position = findProductInCart(session, product, size);
			if(position != -1) {
				carts.get(position).setQuantity(quantity);
			}
		}
	}
	
	@GetMapping("/removeCart")
	@ResponseBody
	public String removeCart(
			@RequestParam String product,
			@RequestParam String size,
			HttpSession session) {
		if(session.getAttribute("carts") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			int position = findProductInCart(session, product, size);
			if(position != -1) {
				carts.remove(position);
				return carts.size()+"";
			}
		}
		return "";
	}
	
	
	@GetMapping(path="/productByPage", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getProductByPage(@RequestParam int indexStart) {
		String htmlResult = "";
		List<Product> productsByPage = productService.getProductsLimit(indexStart);
		
		for(Product product : productsByPage) {
			htmlResult += "<tr>";
			htmlResult += "<td class='text-center'><div class='checkbox'><label><input class='checkbox-product' type='checkbox' value='"+product.getId()+"'></label></div></td>";
			htmlResult += "<td class='text-center'>"+product.getName()+"</td>";
			htmlResult += "<td class='text-center'>"+product.getImage()+"</td>";
			htmlResult += "<td class='text-center'>"+product.getPrice()+"</td>";
			htmlResult +=	"<td class='text-center'><button class='btn btn-info btnEdit' data-toggle='modal' data-target='.my-modal' data-product-id="+product.getId()+"><i class='zmdi zmdi-edit'></i>Edit</a></td>";
			htmlResult += "</tr>";
		}
		return htmlResult;
	}
	
	@GetMapping("/removeProduct")
	@ResponseBody
	public String removeProduct(@RequestParam int productId) {
		productService.deleteProduct(productId);
		
		return "REMOVE_SUCCESS";
		
	}
	

	@PostMapping("/uploadFile")
	public void uploadFile(MultipartHttpServletRequest request) {
		String pathFile = context.getRealPath("/resources/frontend/images/");
		Iterator<String> listName = request.getFileNames();
		MultipartFile multipartFile = request.getFile(listName.next());
		String path = pathFile+multipartFile.getOriginalFilename();
		
		System.out.println(pathFile);
		
		File file = new File(path);
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/saveProduct")
	@ResponseBody
	public String saveProduct(@RequestParam String datajson, Model model) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonObject = objectMapper.readTree(datajson);
		
			JsonNode jsonDetail = jsonObject.get("productDetail");
			
			List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
			Product product = new Product();
			
			for(JsonNode objectDetail : jsonDetail) {
				ProductDetail productDetail = new ProductDetail();
				
				Size size = new Size();
				size.setSizeId(objectDetail.get("size").asInt());
				productDetail.setSize(size);
				
				productDetail.setQuantity(objectDetail.get("quantity").asInt());
				
				productDetail.setProduct(product);
				
				productDetails.add(productDetail);
			}
			
			product.setProductDetails(productDetails);
			
			product.setName(jsonObject.get("productName").asText());
			product.setImage(jsonObject.get("image").asText());
			product.setPrice(jsonObject.get("price").asText());
			product.setDescription(jsonObject.get("description").asText());
			
			Category category = new Category();
			category.setCategoryId(jsonObject.get("category").asInt());
			product.setCategory(category);
			
			if(productService.saveProduct(product) != null) {
				return "SAVE_SUCCCESS";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@GetMapping("/showFormForUpdate")
	@ResponseBody
	public JsonProduct showFromForUpdate(@RequestParam int productId) {
		JsonProduct jsonProduct = new JsonProduct();
		
		Product product = productService.getProduct(productId);
		
		jsonProduct.setId(product.getId());
		jsonProduct.setProductName(product.getName());
		jsonProduct.setImage(product.getImage());
		jsonProduct.setPrice(product.getPrice());
		jsonProduct.setDescription(product.getDescription());
		
		Category category = new Category();
		category.setCategoryId(product.getCategory().getCategoryId());
		category.setName(product.getCategory().getName());
	
		jsonProduct.setCategory(category);
		
		List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
		
		for(ProductDetail detail : product.getProductDetails()) {
			ProductDetail productDetail = new ProductDetail();
		
			Size size = new Size();
			size.setSizeId(detail.getSize().getSizeId());
			size.setName(detail.getSize().getName());
			
			productDetail.setSize(size);
			
			productDetail.setQuantity(detail.getQuantity());
			
			productDetails.add(productDetail);
		}
		
		jsonProduct.setProductDetails(productDetails);
		
		return jsonProduct;
	}
	
	@GetMapping("/updateProduct")
	@ResponseBody
	public String updateProduct(@RequestParam String datajson, Model model) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonObject = objectMapper.readTree(datajson);
		
			JsonNode jsonDetail = jsonObject.get("productDetail");
			
			List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
			Product product = new Product();
			product.setId(jsonObject.get("productId").asInt());
			
			for(JsonNode objectDetail : jsonDetail) {
				ProductDetail productDetail = new ProductDetail();
				
				Size size = new Size();
				size.setSizeId(objectDetail.get("size").asInt());
				productDetail.setSize(size);
				
				productDetail.setQuantity(objectDetail.get("quantity").asInt());
				
				productDetail.setProduct(product);
				
				productDetails.add(productDetail);
			}
			
			product.setProductDetails(productDetails);
			
			product.setName(jsonObject.get("productName").asText());
			product.setImage(jsonObject.get("image").asText());
			product.setPrice(jsonObject.get("price").asText());
			product.setDescription(jsonObject.get("description").asText());
			
			Category category = new Category();
			category.setCategoryId(jsonObject.get("category").asInt());
			product.setCategory(category);
			
			if(productService.updateProduct(product) != null) {
				return "UPDATE_SUCCCESS";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@GetMapping("/searchProducts")
	@ResponseBody
	public String searchProducts(@RequestParam String searchName) {
		String htmlResult="";
		List<Product> productsBySearch = productService.searchProducts(searchName);
		
		for(Product product : productsBySearch) {
			htmlResult += "<tr>";
			htmlResult += "<td class='text-center'><div class='checkbox'><label><input class='checkbox-product' type='checkbox' value='"+product.getId()+"'></label></div></td>";
			htmlResult += "<td class='text-center'>"+product.getName()+"</td>";
			htmlResult += "<td class='text-center'>"+product.getImage()+"</td>";
			htmlResult += "<td class='text-center'>"+product.getPrice()+"</td>";
			htmlResult +=	"<td class='text-center'><button class='btn btn-info btnEdit' data-toggle='modal' data-target='.my-modal' data-product-id="+product.getId()+"><i class='zmdi zmdi-edit'></i>Edit</a></td>";
			htmlResult += "</tr>";
		}
		return htmlResult;
	}
}

