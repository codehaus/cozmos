<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
  <title><decorator:title/></title>
  <decorator:head />
  <link rel="stylesheet" type="text/css" href="/decorators/style.css" />

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
