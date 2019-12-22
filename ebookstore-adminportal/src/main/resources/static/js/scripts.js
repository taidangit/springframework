/**
 * 
 */

$(document).ready(function() {
	$('.delete-book').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'delete';
	    /*]]>*/
	    
		var bookId = $(this).attr("id");
		var self = $(this);
		bootbox.confirm({
			message: "Are you sure to delete this book? It can not undone.",
			 buttons: {
			        confirm: {
			            label: '<i class="fa fa-check"></i> Confirm'
			        },
			        cancel: {
			            label: '<i class="fa fa-times"></i> Cancel'
			        }
			    },
			    callback: function(confirmed) {
			       if(confirmed) {
			    	   /*$.get(path, {'bookId':id}, function(res) {
				       		location.reload();
				       	});*/
			    	   
			    	   $.ajax({
							type: 'GET',
							url: path,
							data: {
								  bookId: bookId
							  },
							
							success: function(res) {
								console.log(res); 
								if(res == "deleteSuccess") {
									self.closest("tr").remove();  
									  $("#deleteSuccess").text("Deleted Successfully!!").css({
											"color": "green",
											"font-style": "italic"
											});
								}
							},
							error: function(res){
								console.log(res); 
							}
						});
			       }
			    }
		});
	});
	
	var bookIdList=[];
	$('#deleteSelected').attr('disabled', true);
	$('.checkboxBook').change(function() {
		var id=$(this).attr('id');
		if(this.checked){
			$('#deleteSelected').attr('disabled', false);
			bookIdList.push(id);
			console.log(bookIdList);
		} else {
			$('#deleteSelected').attr('disabled', true);
			bookIdList.splice(bookIdList.indexOf(id), 1);
			console.log(bookIdList);
		}
		
	});
	
	
	
	$('#deleteSelected').click(function() {
			/*<![CDATA[*/
		    var path = /*[[@{/}]]*/'deleteAll';
		    /*]]>*/
		    
		    bootbox.confirm({
				message: "Are you sure to delete all selected books? It can't be undone.",
				buttons: {
					cancel: {
						label:'<i class="fa fa-times"></i> Cancel'
					},
					confirm: {
						label:'<i class="fa fa-check"></i> Confirm'
					}
				},
				callback: function(confirmed) {
					if(confirmed) {
						$.ajax({
							type: 'GET',
							url: path,
							data: {
								  bookIdListJson: JSON.stringify(bookIdList)
							  },
							contentType: "application/json",
							success: function(res) {
								console.log(res); 
								if(res == "deleteSuccess") {
									 $("input:checked").each(function() {
										 $(this).closest("tr").remove();  
									 });
									  $("#deleteSuccess").text("Deleted Successfully!!").css({
											"color": "green",
											"font-style": "italic"
											});
								}
							},
							error: function(res){
								console.log(res); 
							}
						});
						
					}
				}
			});
	
	});
	
	$("#selectAllBooks").change(function() {

		if(this.checked) {
			$('#deleteSelected').attr('disabled', false);
			$(".checkboxBook").each(function(){
				$(this).attr("checked", true);
				var id=$(this).attr('id');
				bookIdList.push(id);
			});
			console.log(bookIdList);
		} else {
			$('#deleteSelected').attr('disabled', true);
			$(".checkboxBook").each(function(){
				$(this).attr("checked", false);
			});
			bookIdList.splice(0, bookIdList.length);
			console.log(bookIdList);
		}
	});
	
});