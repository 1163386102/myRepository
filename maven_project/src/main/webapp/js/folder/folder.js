	 
	/**
	 * 请求图书数据
	 */
	function getPage(){
		$.ajax({
			url:"folderAction!folderList.action",
			type:"post",
			data:$("#folder-list-form").serialize(),
			dataType:"text",
			success: function(data){
			 $("#folder-list-div").html(data)
			},
			error : function(){
				alert("系统错误，请联系管理员")
			}
		})
	}
	
	/**
	 * 弹出增加框
	 */
	function toAddBook(){
		var div=getDivByUrl("folderAction!toAddFolder.action");
		//弹出文本框
			var add_from = dialog({
		        title: '新建文件夹',
		        modal:true,
		        content: div,
		        okValue: '确 定',
		        ok: function () {
		            this.title('正在提交..',1);
		            addBook();
		        },
		        cancelValue: '取消',
		        cancel: function () {
		            this.title('已经取消..',1);
		        }
		    });
			add_from.show();
	}
	
	
	/**
	 * 保存图书 
	 */
	function addBook(){
		$.ajax({
			url:"folderAction!addFolder.action",
			data:$("#folder-add-form").serialize(),
			type:"post",
			dataType:"text",
			success: function(data){
				//刷新列表
				getPage()
			},
			error : function(){
				alert("系统错误，请联系管理员！")
			}
		})
	}
	
	/**
	 * 删除 
	 */
	function deleteFileById(fileId){
		var d = dialog({
		    title: '删除图书',
		    content: '确定删除吗？',
		    okValue:'确定',
		    ok: function () {
		        var that = this;
		        this.title('正在提交..');
		        var xhr =new XMLHttpRequest();
				//设置 提交方式   url  是否同步
		        xhr.open("post", "folderAction!deleteFileById.action?folderModel.folId="+fileId, true);
				xhr.send(null);
				xhr.onreadystatechange= function(){
					if(xhr.readyState==4 && xhr.status==200){//成功回调函数
						windowTips("删除成功");
						getPage();
					}else if(xhr.readyState==4 && xhr.status==500){//错误回调函数
						art.dialog.tips("系统错误，联系管理员", 3);
					}
				}
		    },
		    cancelValue:"取消",
		    cancel: function () {
		    }
		});
		d.show();	
	}
	
	XMLHttpRequest
	//提示
	function  windowTips(tit){
		var d = dialog({
		    content: tit
		});
		d.show();
		setTimeout(function () {
		    d.close().remove();
		}, 2000);
		
	}
	var d;
	function buttonTips(obj){
		d = dialog();
		d.content("修改");
		d.show(obj);
	}
	function closeButtonTips(){
		 
		d.close();
	}
	
	
	/*批量删除*/
	function deleteAll(){
		//判断有没有选中的复选框
		var checkboxSize=$("input[name='folderChe']:checked").size();
		if(checkboxSize =='0'){
			alert("请至少选择一条数据")
			return;
		}
		if(confirm("确定要删除吗")){
			var ids="";
			$("input[name='folderChe']:checked").each(function(){
				ids += this.value+","
			})
			location="folderAction!deleteFolderAll.action?ids="+ids.substring(0, ids.length-1);
		}
	}
	
	
	
	/**
	 *分享 
	 */
	function shareFolder(shareId){
		
		var add_from = dialog({
	        title: '分享文件夹',
	        content: "http://192.168.11.162:8080/ssh-file/folderAction!shareFolder.action?folderModel.folId="+shareId,
	        okValue: '确 定',
	        ok: function () {
	            this.title('正在提交..',1);
	            
	            var xhr =new XMLHttpRequest();
				//设置 提交方式   url  是否同步
				xhr.open("post", "folderAction!shareFolder.action", true);
				xhr.send(null);
				xhr.onreadystatechange= function(){
					if(xhr.readyState==4 && xhr.status==200){//成功回调函数
						windowTips("分享成功");
						getPage();
					}else if(xhr.readyState==4 && xhr.status==500){//错误回调函数
						art.dialog.tips("系统错误，联系管理员", 3);
					}
				}
				addBook();
	        },
	        cancelValue: '取消',
	        cancel: function () {
	            this.title('已经取消..',1);
	        }
	    });
		add_from.show();
	}
