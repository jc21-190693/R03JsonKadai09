<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Optional"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
pageEncoding="UTF-8"%>





<%
Optional<List<String[]>>optList =
Optional.ofNullable((List<String[]>)request.getAttribute("list"));
List<String[]> list=new ArrayList<>();
if(optList.isPresent()){
list = optList.get();
}
%>
<% for (String[] s : list){
String b = s[0];
String c = s[1];
String d = s[2];
%>

{"チケットID":"<%= b %>","チケット名":"<%= c %>","ポイント":"<%= d %>"}

<% } %>