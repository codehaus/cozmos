<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title><decorator:title/></title>
  <decorator:head/>
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/style/style.css" />
  </head>
<body>
<div id="header">
<h1>Cozmos</h1>
</div>
<ul id="nav">
<li><a href="index.html" id="current">Link One</a></li>
<li><a href="index.html">Link Two</a></li>
<li><a href="index.html">Link Three</a></li>
<li><a href="index.html">Link Four</a></li>
<li><a href="index.html">Link Five</a></li>
</ul>
<div id="container">
<div id="content">
<decorator:body/>
</div>
<div id="footer">
<p>Copyright&copy; 2006 Your Company <br />
|&nbsp;XHTML template by <a href="http://www.karenblundell.com" target="blank">arwen54</a>&nbsp;|&nbsp;<a href="http://validator.w3.org/check?uri=referer" target="_blank">Valid XHTML</a>&nbsp;|&nbsp;<a href="http://jigsaw.w3.org/css-validator/" target="_blank">Valid CSS</a>&nbsp;|</p>
</div>
</div>
</body>
</html>
