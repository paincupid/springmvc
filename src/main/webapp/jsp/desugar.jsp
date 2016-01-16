<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>desugar</title>
</head>
<script type="text/javascript">
	function hello(thing) {
		alert(this + " says hello " + thing);
	}
	//1.
	//hello.call("Yehuda", "world") //=> Yehuda says hello world

	var person = {
		name : "Brendan Eich",
		hello : function(thing) {
			alert(this + " says hello " + thing);
		}
	}
	//2.
	// this:  
	//person.hello("world")  
	// desugars to this:  
	//person.hello.call(person, "world");  

	//3.
	person = {
		name : "Brendan Eich"
	}
	person.hello = hello;

	//person.hello("world") // still desugars to person.hello.call(person, "world")

	//hello("world") // "[object DOMWindow]world"

	//4.
	var person = {
		name : "Brendan Eich",
		hello : function(thing) {
			alert(this.name + " says hello " + thing);
		}
	}

	var boundHello = function(thing) {
		return person.hello.call(person, thing);
	}

	//boundHello("world");

	//5.
	var bind = function(func, thisValue) {
		return function() {
			return func.apply(thisValue, arguments);
		}
	}

	var boundHello = bind(person.hello, person);
	//boundHello("world") // "Brendan Eich says hello world"

	//6.
	var person = {
		name : "Alex Russell",
		hello : function() {
			alert(this.name + " says hello world");
		}
	}

	//$("#some-div").click(person.hello.bind(person));
	// when the div is clicked, "Alex Russell says hello world" is printed
	
	
</script>

<body>
</body>
</html>