<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>desugar</title>
</head>
<script type="text/javascript">
	//第1种写法  
	function Circle(r) {
		this.r = r;
	}
	Circle.PI = 3.14159;
	Circle.prototype.area = function() {
		return Circle.PI * this.r * this.r;
	}

	var c = new Circle(10.0);
	//alert(c.area());

	//第2种写法  有问题
	var Circle = function() {
		var obj = new Object();
		obj.PI = 3.14159;

		obj.area = function(r) {
			return this.PI * r * r;
		}
		return obj;
	}
	var c = new Circle(2.0);
	//alert(c.area());

	//第3种写法  
	var Circle = new Object();
	Circle.PI = 3.14159;
	Circle.Area = function(r) {
		return this.PI * r * r;
	}

	//alert(Circle.Area(3.0));

	//第4种写法  
	var Circle = {
		"PI" : 3.14159,
		"area" : function(r) {
			return this.PI * r * r;
		}
	};
	//alert(Circle.area(4.0));

	//第5种写法  
	var Circle = new Function(
			"this.PI = 3.14159;this.area = function( r ) {return r*r*this.PI;}");
	//alert((new Circle()).area(5.0));
	
	///----------------------------
	
	var html = {
        Name:'Object',
        Success:function(){
            this.Say = function(){
                    alert("Hello,world");
            };
            alert("Obj Success");
        }
    };
</script>

<body>
</body>
</html>