<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
  <title><decorator:title/></title>
  <decorator:head />
  </head>
  <body>
    <div id="header">
      <p id="logo">Cozmos</p>
      <p id="motto">Paul Hammant's lil' diki</p>
      <hr />
    </div>
    
    <decorator:body />

    <div id="footer">
      <p>Copyright (c) 2006, ThoughtWorks, Inc.</p>
    </div>
  </body>
</html>
