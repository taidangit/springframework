/**
 * 
 */

$(document).ready(function() {
	$(".cartItemQty").on("change", function() {
		var id = $(this).attr('id');
		$("#update-item-"+id).css("display", "block");
	});
	
	$("#theSameAsShippingAddress").on("change", function() {
		if(this.checked) {
			$(".billingAddress").attr("disabled", true);
		} else {
			$(".billingAddress").attr("disabled", false);
		}
	});
	
	$('#txtNewPassword, #txtConfirmPassword').on('keyup', function () {
		if ($('#txtNewPassword').val() == $('#txtConfirmPassword').val()) {
			$('#message').html('Password match').css('color', 'green');
			$('#btnUpdateUserInfo').attr('disabled', false);
		} else { 
			$('#message').html('Password do not match!').css('color', 'red');
			$('#btnUpdateUserInfo').attr('disabled', true);
		}
	});
	
	$('.list-group-item').on('click', function(e) {
		
		$('.list-group-item').removeClass('active');
		$(this).addClass('active');
		
		var categoryId = $(this).attr('id');
		
		$.ajax({
			  url: "/book/searchByCategory",
			  type: "GET",
			  data: {
				  categoryId:categoryId
			  },
			 
			  success: function(data) {
				  $("#bookList").find("tbody").empty().append(data);
				  $("#emptyList").text("").removeClass("alert alert-warning");
				  if(data == "emptyList") {
					  $("#emptyList").text("Oops, no result is found. Try something else or try again later.")
					  	.css({"font-style": "italic"})
					  	.addClass("alert alert-warning");
				  }
			}
		});
		
	});
	
	$('#btnSearch').on('click', function(e) {
		e.preventDefault();
		var keyword = $("input[name='keyword']").val();
		
		$.ajax({
			  url: "/book/searchBook",
			  type: "GET",
			  data: {
				  keyword:keyword
			  },
			 
			  success: function(data) {
				  $("#bookList").find("tbody").empty().append(data);
				  $("#emptyList").text("").removeClass("alert alert-warning");
				  if(data == "emptyList") {
					  $("#emptyList").text("Oops, no result is found. Try something else or try again later.")
					  	.css({"font-style": "italic"})
					  	.addClass("alert alert-warning");
				  }
			}
		});
	});
	
	
	$('#txtEmail').on('keyup', function () {
		var email = /^([\w\.])+@([a-zA-Z0-9\-])+\.([a-zA-Z]{2,4})(\.[a-zA-Z]{2,4})?$/;
		if($(this).val().match(email)) {
			$('#messageEmail').html('Email valid').css('color', 'green');
			$('#btnSubmit').attr('disabled', false);
		} else {
			$('#messageEmail').html('Email invalid').css('color', 'red');
			$('#btnSubmit').attr('disabled', true);
		}
	});
	
});