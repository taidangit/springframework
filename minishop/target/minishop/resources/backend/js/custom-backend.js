$(document).ready(function() {

	$(document).on("click", ".page-item", function() {
		$(".page-item").removeClass("active");
		$(this).addClass("active");
		var currentPage = $(this).text();
		
		var totalItemsPerPage = 8;
		var indexStart = (currentPage-1)*totalItemsPerPage;
		
		$.ajax({
			  url: "/minishop/api/productByPage",
			  type: "GET",
			  data: {
				  indexStart:indexStart
			  },
			  success: function(data) {
				  $("#product-table").find("tbody").empty().append(data);
			}
		});
	});
	
	$("#checkall").change(function() {
		if(this.checked) {
			$(".checkbox input").each(function(){
				$(this).attr("checked", true);
			})
		} else {
			$(".checkbox input").each(function(){
				$(this).attr("checked", false);
			})
		}
	});
	
	
	$("#product-delete").click(function() {
		$(".checkbox input:checked").each(function() {
			var productId = $(this).val();
		
			var self = $(this);
			$.ajax({
				  url: "/minishop/api/removeProduct",
				  type: "GET",
				  data: {
					  productId:productId
				  },
				  success: function(data) {
					  if(data == "REMOVE_SUCCESS") {
						  self.closest("tr").remove(); 
						  $("#result-delete").text("Remove Successfully!!").css({
								"color": "green",
								"font-style": "italic"
								});
					  }
					  
				}
			});
		});
	});
	
	
	var files = [];
	var imageName = "";
	$("#product-image").change(function(e) {
		files = e.target.files;
		imageName = files[0].name;
		var forms = new FormData();
		forms.append("file", files[0]);
		$.ajax({
			  url: "/minishop/api/uploadFile",
			  type: "POST",
			  data: forms,
			  contentType: false,
			  processData: false,
			  enctype: "multipart/form-data",
			  success: function(data) {
				  
			}
		});
	});
	
	$("#btnDetail").click(function() {
		$("#product-detail").clone().appendTo("#product-detail-container");
	});
	
	$("#btnSave").click(function(e) {
		e.preventDefault();
		var formData = $("#product-form").serializeArray();
		
		json = {};
		arrayDetail = [];
		
		$.each(formData, function(i, field){
			json[field.name] = field.value;
	       
	        
	    });
		
		
		$("#product-detail-container > div").each(function() {
			objectDetail = {};
			var size = $(this).find('select[name="size"]').val();
			var quantity = $(this).find('input[name="quantity"]').val();
			
			objectDetail["size"] = size;
			objectDetail["quantity"] = quantity;
			
			arrayDetail.push(objectDetail);
		});
		
		json["productDetail"] = arrayDetail;
		json["image"] = imageName;
		
		console.log(json);
		
		$.ajax({
			  url: "/minishop/api/saveProduct",
			  type: "GET",
			  data: {
				  datajson: JSON.stringify(json)
			  },
			 
			  success: function(data) {
				  if(data == "SAVE_SUCCCESS") {
					  $("#result-save").text("Save Successfully!!").css({
							"color": "green",
							"font-style": "italic"
							});
				  }
			}
		});
	});
	
	var productId = 0;
	
	$(document).on("click", ".btnEdit", function(e) {
		
		productId = $(this).attr("data-product-id");
		
		$.ajax({
			  url: "/minishop/api/showFormForUpdate",
			  type: "GET",
			  data: {
				  productId:productId
			  },
			 
			  success: function(data) {
				  console.log(data);			 
					
				 $("#product-form :text[name='productName']").val(data.productName);
				 $("#product-form :text[name='price']").val(data.price);
				 $("#product-form textarea[name='description']").val(data.description);
			
				 $("#product-form select[name='category']").val(data.category.categoryId);
				 
				 $("#product-detail").find("select[name='size']").val(data.productDetails[0].size.sizeId);
				 $("#product-detail").find("input[name='quantity']").val(data.productDetails[0].quantity);
				 
				 $("#product-detail-container").empty();
				 
				 for(var i = 1; i < data.productDetails.length; i++) {
					 
					 var detailClone =$("#product-detail").clone();
					 detailClone.find("select[name='size']").val(data.productDetails[i].size.sizeId);
					 detailClone.find("input[name='quantity']").val(data.productDetails[i].quantity);
					 
					 $("#product-detail-container").append(detailClone);
					 
				 }
				 
			}
		});
		
	});
	
	$("#btnUpdate").click(function(e) {
		e.preventDefault();
		var formData = $("#product-form").serializeArray();
		
		json = {};
		arrayDetail = [];
		
		$.each(formData, function(i, field){
			json[field.name] = field.value;
	       
	        
	    });
		
		
		$(".product-detail").each(function() {
			objectDetail = {};
			var size = $(this).find('select[name="size"]').val();
			var quantity = $(this).find('input[name="quantity"]').val();
			
			objectDetail["size"] = size;
			objectDetail["quantity"] = quantity;
			
			arrayDetail.push(objectDetail);
		});
		
		json["productDetail"] = arrayDetail;
		json["image"] = imageName;
		json["productId"] = productId;
		
		console.log(json);
		
		$.ajax({
			  url: "/minishop/api/updateProduct",
			  type: "GET",
			  data: {
				  datajson: JSON.stringify(json)
			  },
			 
			  success: function(data) {
				  if(data == "UPDATE_SUCCCESS") {
					  $("#result-update").text("Update Successfully!!").css({
							"color": "green",
							"font-style": "italic"
							});
				  }
			}
		});
	});
	
	$("#btnSearch").click(function(e) {
		e.preventDefault();
		
		var searchName = $(".form-header :text[name='search']").val();
		
		$.ajax({
			  url: "/minishop/api/searchProducts",
			  type: "GET",
			  data: {
				  searchName:searchName
			  },
			 
			  success: function(data) {
				  $("#product-table").find("tbody").empty().append(data);
				  $(".form-header :text[name='search']").val("");
			}
		});
		
	});
});