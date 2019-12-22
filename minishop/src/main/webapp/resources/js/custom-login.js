$(document).ready(function() {
	$("#btnLogin").click(function() {
		
		var username = $("input[name='username']").val();
		var pass = $("input[name='pass']").val();
		
		$.ajax({
			  url: "/minishop/api/ajax-login",
			  type: "GET",
			  data: {
				  username: username,
				  pass: pass
			  },
			  success: function(data) {
				if(data == "USER") {
					path = window.location.href.replace("/login", "/home");
					window.location = path;
			
				} else if(data == "ADMIN") {
					path = window.location.href.replace("/login", "/admin/productList");
					window.location = path;
				} else {
					$("#result-login").text("Username or password is incorrect").css({
						"color": "red",
						"font-style": "italic"
						});
				}
			  }
		});
		
	});
	
});