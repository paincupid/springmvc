package com.paincupid.springmvc.application.concurrent;
/*package com.paincupid.springmvc;

import java.util.Hashtable;

import org.antlr.stringtemplate.StringTemplate;

public class StringTemplateTest {
	public static void main(String[] args){
		StringTemplate hello = new StringTemplate("SELECT $column;separator=\",\"$ FROM $table$;");
        hello.setAttribute("column", "name");
        hello.setAttribute("column", "email");
        hello.setAttribute("column", "sex");
        hello.setAttribute("table", "User");

      //键值类型替换
      StringTemplate hello = new StringTemplate("对象变量替换姓名:$KeyList.Name$, 年龄:$KeyList.Age$ ");
      Hashtable ht = new Hashtable();
      ht.put ("Name", "111");
      ht.put("Age", "qw");



      //同时显示多个变量
      //StringTemplate hello = new StringTemplate("变量相加$[Temp1,Temp2,Temp3]$");
      //StringTemplate hello = new StringTemplate("变量相加$[Temp1,Temp2,Temp3];separator=\",\"$");
      //hello.setAttribute("Temp1", "变量1");
      //hello.setAttribute("Temp2", "变量2");
      //hello.setAttribute("Temp3", "变量3");
      //模板调用
      //StringTemplateGroup sg = newStringTemplateGroup("GroupTest");
      //sg.defineTemplate("Box","中华人民共和国中华人民共和国,中华人民共和国");
      //StringTemplate hello = sg.defineTemplate("List","下面是我调用Box模板内容:\n$Box()$");

      //给调用模板传参数
      //StringTemplateGroup sg = newStringTemplateGroup("GroupTest");
      //sg.defineTemplate("BoxA", "我不来了()");
      //sg.defineTemplate("Box", "中华人民共和国,中华全国Title: $Title$ !!");
      //StringTemplate hello = sg.defineTemplate("List","下面是我调用Box模板内容:\n$Box(Title=\"标题值\")$");//传变量值
      //StringTemplate hello = sg.defineTemplate("List","下面是我调用Box模板内容:\n$Box(Title={$Titles$})$");//传变量
      //StringTemplate hello = sg.defineTemplate("List","下面是我调用Box模板内容:\n$Box(Title=BoxA())$");//传模板
      //hello.setAttribute("Titles", "标题参数值");



      //值模板
      StringTemplateGroup sg = newStringTemplateGroup("GroupTest");
      sg.defineTemplate("Box", "中华人民共和国,,,,");
      StringTemplate hello = new StringTemplate(sg,"调用值模板:$Mys:Box();separator=\"==\"$------s");
      hello.setAttribute("Mys", "中国");
      hello.setAttribute("Mys", "中国");



      //循环显示
      //StringTemplate hello = newStringTemplate("<table>$Item:{<tr><td>$it$要循环显示的内容</td></tr>}$</table>");
      //for (int i = 0; i < 10; i++)
      //{
      //   hello.setAttribute("Item", i);
      //}

   System.out.println(hello.toString());
	}
}

*/