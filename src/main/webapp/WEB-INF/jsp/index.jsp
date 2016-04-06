<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello React</title>
    <script type="text/javascript" src="angular/react/react.min.js"></script>
    <script type="text/javascript" src="angular/react/react-dom.min.js"></script>
    <script type="text/javascript" src="angular/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="angular/showdown.min.js"></script>
    <script type="text/javascript" src="App.js"></script>
</head>
<body>
<div id="content">${content}</div>
<script type="text/javascript" src="commentBox.js"></script>
<script type="text/javascript">
    $(function () {
        renderClient(${data});
    });
</script>
</body>
</html>