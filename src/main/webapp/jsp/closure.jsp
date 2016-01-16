<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function f1() {
		n = 999;
		function f2() {
			alert(n);
		}
		return f2;
	}
	var result = f1();
	//result(); // 999

	var name = "The Window";
	var object = {
		name : "My Object",
		getNameFunc : function() {
			return function() {
				return this.name;
			};
		}
	};
	//alert(object.getNameFunc()());
	
	function hello(thing) {  
		alert(this + " says hello " + thing);
	}
	person = { name: "Brendan Eich" }  
	person.hello = hello;
	person.hello("world") // still desugars to person.hello.call(person, "world")
	hello("world") //


</script>

<body>

</body>
</html>