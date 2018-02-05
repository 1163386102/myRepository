

	function getDivByUrl(urls){
		var div="";
		$.ajax({
			url:urls,
			type:"post",
			async:false,
			dataType:"text",
			success: function(data){
				div=data;	
			},
			error : function(){
				alert("系统错误，请联系王景明")
			}
		})
		return div;
	}