$(document).ready(function() {
	
	$("#btnCart").click(function(){
		var quantity = $("input[name='quantity']").val();
		var size = $("select[name='size']").find(":selected").text();
		var sizeId =  $("select[name='size']").find(":selected").val();
		var product = $("#product-name").text();
		var productId = $("#product-name").attr("data-product-id");
		var price = $("#product-price").text();
		var image = $("#product-image").attr("data-image");
		
		
		$.ajax({
			  url: "/minishop/api/addToCart",
			  type: "GET",
			  data: {
				  quantity: quantity,
				  size: size,
				  product: product,
				  price: price,
				  image: image
			  },
			  success: function(data) {
				  $("#cart-size").html("<a href='/minishop/cart' class='nav-link'>"+
							"<span class='icon-shopping_cart'></span>["+data+"]</a>");
			}
		});
	});
	
	subTotal();
	
	$(".cart-quantity").change(function() {
		var quantity = $(this).closest("tr").find(".cart-quantity").val();
		var size =  $(this).closest("tr").find(".product-name > p").text();;
		var product = $(this).closest("tr").find(".product-name > h3").text();
	
		subTotal();
		
		$.ajax({
				url: "/minishop/api/updateCart",
				type: "GET",
			
			  data: {
				 quantity: quantity,
				 size: size,
				 product: product
			  },
			  success: function(data) {
			}
		});
	});
	
	
	function subTotal() {
		var subTotal = 0;
		
		$("tbody > tr").each(function() {
			var quantity = $(this).find(".cart-quantity").val();
			var price = $(this).find(".price").attr("data-cart-price").substring(1);
			
			var total = quantity*parseInt(price);
			var totalFormat = total.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();
			$(this).find(".total").text("$"+totalFormat).css("font-style", "italic");
			
			subTotal += total;
		});
		
		var subTotalFormat = subTotal.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();
		$(".cart-totals").text("$"+subTotalFormat).css({"font-weight":"bold", "color": "red"});
	}
	
	$(".product-remove").click(function() {
		var self=$(this);
		var size =  $(this).closest("tr").find(".product-name > p").text();;
		var product = $(this).closest("tr").find(".product-name > h3").text();

		
		$.ajax({
				url: "/minishop/api/removeCart",
				type: "GET",
			
			  data: {
				 size: size,
				 product: product
			  },
			  success: function(data) {
				  self.closest("tr").remove();
				  subTotal();
				  $("#cart-size").html("<a href='/minishop/cart' class='nav-link'>"+
							"<span class='icon-shopping_cart'></span>["+data+"]</a>");
 
			}
		});
	});
	
	
});