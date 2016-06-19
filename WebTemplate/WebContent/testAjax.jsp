<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="js/jquery-3.0.0.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<link href="css/jquery-ui.css" rel="stylesheet">
	
<style>
* {
	padding : 0px;
	margin : 0px;
}

#wrapper {	margin : 0 auto;
	text-align : center;
}
</style>
</head>
<body>

<script type="text/javascript">
$(document).ready(function(){
	$("#testAjaxBtn").button({
		icons: {
	        primary: "ui-icon-refresh"
	      }
	}).on("click", function(event){
		testAjaxFunction('myName!!');
	})

	
	
	function testAjaxFunction(myName){
		$.ajax({
		  url: "testAjaxAction.do",
		  data: {
    		    name: myName,
		    },
		  success: function( result ) {
			  //alert(result);
			  $("#testAjaxResult").text(result);
		  }
		});
	}
})

</script>

<div id="wrapper">
	<p>See JQueryTestController.java for listening controller.</p>
	<button id="testAjaxBtn">testAjaxButton</button><br>
	<span id="testAjaxResult">result?</span>
</div>
</body>
</html>